package dev.andante.onastick.tag

import dev.andante.onastick.OnAStick
import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier

object OnAStickItemTags {
    /**
     * Represents a list of items that are wooden sticks.
     */
    val WOODEN_STICKS = register("wooden_sticks")

    /**
     * Represents a list of items that are classified as 'on a stick'.
     */
    val ON_A_STICK_ITEMS = register("on_a_stick_items")

    var CRAFTS_CRAFTING_TABLE_ON_A_STICK = register("crafts/crafting_table_on_a_stick")
    var CRAFTS_SMITHING_TABLE_ON_A_STICK = register("crafts/smithing_table_on_a_stick")
    var CRAFTS_LOOM_ON_A_STICK = register("crafts/loom_on_a_stick")
    var CRAFTS_CARTOGRAPHY_TABLE_ON_A_STICK = register("crafts/cartography_table_on_a_stick")
    var CRAFTS_GRINDSTONE_ON_A_STICK = register("crafts/grindstone_on_a_stick")
    var CRAFTS_ANVIL_ON_A_STICK = register("crafts/anvil_on_a_stick")
    var CRAFTS_STONECUTTER_ON_A_STICK = register("crafts/stonecutter_on_a_stick")

    private fun register(id: String): TagKey<Item> {
        return TagKey.of(RegistryKeys.ITEM, Identifier(OnAStick.MOD_ID, id))
    }
}
