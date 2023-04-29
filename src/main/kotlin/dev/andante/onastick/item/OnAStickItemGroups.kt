package dev.andante.onastick.item

import dev.andante.onastick.OnAStick
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.ItemGroups
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier

@Suppress("UnstableApiUsage")
object OnAStickItemGroups {
    val ALL = FabricItemGroup.builder(Identifier(OnAStick.MOD_ID, "all"))
        .entries { _, entries ->
            Registries.ITEM
                .filterIsInstance<OnAStickItem>()
                .map(::ItemStack)
                .forEach(entries::add)
        }
        .icon { ItemStack(OnAStickItems.CRAFTING_TABLE_ON_A_STICK) }
        .build()!!

    init {
        // add on a stick items to tools item group after fishing rod
        ItemGroupEvents.MODIFY_ENTRIES_ALL.register { group, entries ->
            if (group == ItemGroups.TOOLS) {
                Registries.ITEM
                    .filterIsInstance<OnAStickItem>()
                    .reversed()
                    .forEach { item -> entries.addAfter(Items.FISHING_ROD, item) }
            }
        }
    }
}
