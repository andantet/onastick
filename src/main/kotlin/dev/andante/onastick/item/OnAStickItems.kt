package dev.andante.onastick.item

import dev.andante.onastick.OnAStick
import dev.andante.onastick.screen.LocalAnvilScreenHandler
import dev.andante.onastick.screen.LocalCartographyTableScreenHandler
import dev.andante.onastick.screen.LocalCraftingScreenHandler
import dev.andante.onastick.screen.LocalGrindstoneScreenHandler
import dev.andante.onastick.screen.LocalLoomScreenHandler
import dev.andante.onastick.screen.LocalScreenHandlerFactory
import dev.andante.onastick.screen.LocalSmithingScreenHandler
import dev.andante.onastick.screen.LocalStonecutterScreenHandler
import net.minecraft.block.AnvilBlock
import net.minecraft.block.CartographyTableBlock
import net.minecraft.block.CraftingTableBlock
import net.minecraft.block.GrindstoneBlock
import net.minecraft.block.LoomBlock
import net.minecraft.block.SmithingTableBlock
import net.minecraft.block.StonecutterBlock
import net.minecraft.item.Item
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry


/**
 * Represents On A Stick items.
 */
object OnAStickItems {
    val CRAFTING_TABLE_ON_A_STICK = registerStick("crafting_table_on_a_stick", CraftingTableBlock.TITLE, ::LocalCraftingScreenHandler)
    val SMITHING_TABLE_ON_A_STICK = registerStick("smithing_table_on_a_stick", SmithingTableBlock.SCREEN_TITLE, ::LocalSmithingScreenHandler)
    val LOOM_ON_A_STICK = registerStick("loom_on_a_stick", LoomBlock.TITLE, ::LocalLoomScreenHandler)
    val CARTOGRAPHY_TABLE_ON_A_STICK = registerStick("cartography_table_on_a_stick", CartographyTableBlock.TITLE, ::LocalCartographyTableScreenHandler)
    val GRINDSTONE_ON_A_STICK = registerStick("grindstone_on_a_stick", GrindstoneBlock.TITLE, ::LocalGrindstoneScreenHandler)
    val ANVIL_ON_A_STICK = registerStick("anvil_on_a_stick", AnvilBlock.TITLE, ::LocalAnvilScreenHandler)
    val STONECUTTER_ON_A_STICK = registerStick("stonecutter_on_a_stick", StonecutterBlock.TITLE, ::LocalStonecutterScreenHandler)

    private fun registerStick(id: String, title: Text, localScreenHandlerFactory: LocalScreenHandlerFactory): Item {
        return register(id) { settings -> OnAStickItem(settings.maxCount(1), localScreenHandlerFactory, title) }
    }

    private fun register(id: String, item: Item): Item {
        return Registry.register(Registry.ITEM, Identifier(OnAStick.MOD_ID, id), item)
    }

    private fun register(id: String, item: (Item.Settings) -> Item): Item {
        return register(id, item(Item.Settings().group(OnAStickItemGroups.ALL)))
    }
}
