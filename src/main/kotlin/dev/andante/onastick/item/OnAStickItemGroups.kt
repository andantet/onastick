package dev.andante.onastick.item

import dev.andante.onastick.OnAStick
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

object OnAStickItemGroups {
    val ALL: ItemGroup = FabricItemGroupBuilder.build(Identifier(OnAStick.MOD_ID, "all")) { ItemStack(OnAStickItems.CRAFTING_TABLE_ON_A_STICK) }
}
