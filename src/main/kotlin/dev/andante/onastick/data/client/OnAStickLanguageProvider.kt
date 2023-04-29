package dev.andante.onastick.data.client

import dev.andante.onastick.OnAStick
import dev.andante.onastick.item.OnAStickItemGroups
import dev.andante.onastick.item.OnAStickItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider

/**
 * Generates On A Stick language files.
 */
class OnAStickLanguageProvider(generator: FabricDataGenerator) : FabricLanguageProvider(generator) {
    override fun generateTranslations(builder: TranslationBuilder) {
        builder.add(OnAStickItemGroups.ALL, OnAStick.MOD_NAME)

        builder.add(OnAStickItems.CRAFTING_TABLE_ON_A_STICK, "Crafting Table On A Stick")
        builder.add(OnAStickItems.SMITHING_TABLE_ON_A_STICK, "Smithing Table On A Stick")
        builder.add(OnAStickItems.LOOM_ON_A_STICK, "Loom On A Stick")
        builder.add(OnAStickItems.CARTOGRAPHY_TABLE_ON_A_STICK, "Cartography Table On A Stick")
        builder.add(OnAStickItems.GRINDSTONE_ON_A_STICK, "Grindstone On A Stick")
        builder.add(OnAStickItems.ANVIL_ON_A_STICK, "Anvil On A Stick")
        builder.add(OnAStickItems.STONECUTTER_ON_A_STICK, "Stonecutter On A Stick")
    }
}
