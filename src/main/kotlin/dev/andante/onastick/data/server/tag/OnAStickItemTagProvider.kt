package dev.andante.onastick.data.server.tag

import dev.andante.onastick.item.OnAStickItem
import dev.andante.onastick.tag.OnAStickItemTags
import java.util.concurrent.CompletableFuture
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.fabricmc.fabric.impl.tag.convention.TagRegistration
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.ItemTags
import net.minecraft.util.Identifier

/**
 * Generates On A Stick item tags.
 */
@Suppress("UnstableApiUsage")
class OnAStickItemTagProvider(out: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricTagProvider.ItemTagProvider(out, registriesFuture) {
    override fun configure(arg: RegistryWrapper.WrapperLookup) {
        // wooden sticks
        getOrCreateTagBuilder(OnAStickItemTags.WOODEN_STICKS)
            .add(Items.STICK)
            .addOptionalTag(Identifier("sticks"))
            .addOptionalTag(TagRegistration.ITEM_TAG_REGISTRATION.registerCommon("sticks"))

        // on a stick items
        val onAStickItems = getOrCreateTagBuilder(OnAStickItemTags.ON_A_STICK_ITEMS)
        Registries.ITEM
            .filterIsInstance<OnAStickItem>()
            .forEach(onAStickItems::add)

        // crafts
        getOrCreateTagBuilder(OnAStickItemTags.CRAFTS_CRAFTING_TABLE_ON_A_STICK)
            .add(Items.CRAFTING_TABLE)

        getOrCreateTagBuilder(OnAStickItemTags.CRAFTS_SMITHING_TABLE_ON_A_STICK)
            .add(Items.SMITHING_TABLE)

        getOrCreateTagBuilder(OnAStickItemTags.CRAFTS_LOOM_ON_A_STICK)
            .add(Items.LOOM)

        getOrCreateTagBuilder(OnAStickItemTags.CRAFTS_CARTOGRAPHY_TABLE_ON_A_STICK)
            .add(Items.CARTOGRAPHY_TABLE)

        getOrCreateTagBuilder(OnAStickItemTags.CRAFTS_GRINDSTONE_ON_A_STICK)
            .add(Items.GRINDSTONE)

        getOrCreateTagBuilder(OnAStickItemTags.CRAFTS_ANVIL_ON_A_STICK)
            .forceAddTag(ItemTags.ANVIL)

        getOrCreateTagBuilder(OnAStickItemTags.CRAFTS_STONECUTTER_ON_A_STICK)
            .add(Items.STONECUTTER)
    }
}
