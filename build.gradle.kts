import com.matthewprenger.cursegradle.CurseArtifact
import com.matthewprenger.cursegradle.CurseProject
import com.matthewprenger.cursegradle.CurseRelation
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.kohsuke.github.GitHub
import org.kohsuke.github.GHReleaseBuilder

buildscript {
    dependencies {
        classpath("org.kohsuke", "github-api", "1.+")
    }
}

plugins {
    kotlin("jvm").version(System.getProperty("kotlin_version"))

    id("fabric-loom")

    id("com.matthewprenger.cursegradle").version("1.+")
    id("com.modrinth.minotaur").version("2.+")
}

base { archivesName.set(extra["archives_base_name"] as String) }

val versionMinecraft = extra["minecraft_version"] as String

version = "${extra["mod_version"]}+$versionMinecraft"
group = extra["maven_group"] as String

val modId = extra["mod_id"] as String
val versionYarn = extra["yarn_build"] as String
val versionJava = extra["java_version"] as String
val versionLoader = extra["loader_version"] as String
val versionFabricApi = extra["fabric_version"] as String
val versionFabricKotlin = extra["fabric_language_kotlin_version"] as String

dependencies {
    minecraft("com.mojang", "minecraft", versionMinecraft)
    mappings("net.fabricmc", "yarn", "$versionMinecraft+build.$versionYarn", null, "v2")
    modImplementation("net.fabricmc", "fabric-loader", versionLoader)
    modImplementation("net.fabricmc.fabric-api", "fabric-api", versionFabricApi)
    include(modImplementation("net.fabricmc", "fabric-language-kotlin", versionFabricKotlin))
}

tasks {
    val javaVersion = JavaVersion.toVersion(versionJava.toInt())
    val javaVersionString = javaVersion.toString()

    withType<JavaCompile> {
        options.encoding = "UTF-8"
        sourceCompatibility = javaVersionString
        targetCompatibility = javaVersionString
        options.release.set(javaVersionString.toInt())
    }

    withType<KotlinCompile> { kotlinOptions { jvmTarget = javaVersionString } }

    java {
        toolchain { languageVersion.set(JavaLanguageVersion.of(javaVersionString)) }
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
        withSourcesJar()
    }

    jar { from("LICENSE") { rename { "${it}_${base.archivesName.get()}" } } }
    processResources { filesMatching("fabric.mod.json") { expand(mapOf("version" to version)) } }
}

/* Data Generation */

val generatedResourcesDir = "src/main/generated"

loom {
    accessWidenerPath.set(file("src/main/resources/$modId.accesswidener"))

    runs {
        create("Data Generation") {
            server()

            vmArg("-Dfabric-api.datagen")
            vmArg("-Dfabric-api.datagen.output-dir=${file(generatedResourcesDir)}")
            vmArg("-Dfabric-api.datagen.modid=$modId")

            runDir("build/datagen")
        }
    }
}

sourceSets.main {
    resources {
        srcDir(generatedResourcesDir)
    }
}

/* Releasing */

val env: Map<String, String> = System.getenv()!!
val versionNameText = "[${extra["major_version"]}] ${extra["mod_name"]} ${extra["mod_version"]}"
val changelogText = File("./gradle", "CHANGELOG.md").readText()
val supportedVersions = (extra["supported_versions"] as String).split(',')
val rawReleaseType = extra["release_type"] as String
val githubRepository = extra["github_repository"] as String
val githubBranch = extra["github_branch"] as String
val modrinthId = extra["modrinth_id"] as String
val curseforgeId = extra["curseforge_id"] as String

env["GITHUB_TOKEN"]?.run {
    tasks.register("github") {
        doLast {
            val github = GitHub.connectUsingOAuth(this@run)
            val repository = github.getRepository(githubRepository)

            val builder = GHReleaseBuilder(repository, version as String)
            builder.name(versionNameText)
            builder.body(changelogText)
            builder.commitish(githubBranch)
            builder.prerelease(rawReleaseType == "beta")
            builder.create().uploadAsset(file("${project.buildDir}/libs/${base.archivesName.get()}-${version}.jar"), "application/java-archive")
        }
    }
}

env["MODRINTH_TOKEN"]?.run {
    modrinth {
        token.set(this@run)
        projectId.set(modrinthId)
        versionNumber.set(version as String)
        versionName.set(versionNameText)
        versionType.set(rawReleaseType)
        changelog.set(changelogText)
        uploadFile.set(tasks.getByPath("remapJar"))
        gameVersions.set(supportedVersions)
        dependencies {
            required.project("fabric-api")
            embedded.project("fabric-language-kotlin")
        }
    }
}

env["CURSEFORGE_API_KEY"]?.run {
    curseforge {
        apiKey = this@run

        project(closureOf<CurseProject> {
            id = curseforgeId

            addGameVersion("Fabric")
            supportedVersions.forEach(::addGameVersion)

            changelog = changelogText
            changelogType = "markdown"
            releaseType = rawReleaseType

            mainArtifact(tasks.getByPath("remapJar"), closureOf<CurseArtifact> {
                displayName = versionNameText
                relations(closureOf<CurseRelation> {
                    requiredDependency("fabric-api")
                    embeddedLibrary("fabric-language-kotlin")
                })
            })
        })
    }
}

tasks.register("releaseVersion").configure {
    dependsOn("build", "modrinth", "github", "curseforge")
}
