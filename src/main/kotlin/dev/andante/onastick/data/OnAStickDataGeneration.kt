package dev.andante.onastick.data

import dev.andante.onastick.data.client.OnAStickLanguageProvider
import dev.andante.onastick.data.client.OnAStickModelProvider
import dev.andante.onastick.data.server.OnAStickRecipeProvider
import dev.andante.onastick.data.server.tag.OnAStickItemTagProvider
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

/**
 * Generates On A Stick assets and data.
 */
object OnAStickDataGeneration : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(generator: FabricDataGenerator) {
        val pack: FabricDataGenerator.Pack = generator.createPack()

        pack.addProvider(::OnAStickModelProvider)
        pack.addProvider(::OnAStickLanguageProvider)

        pack.addProvider(::OnAStickRecipeProvider)
        pack.addProvider(::OnAStickItemTagProvider)
    }
}
