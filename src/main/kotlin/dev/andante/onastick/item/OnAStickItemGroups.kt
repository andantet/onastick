package dev.andante.onastick.item

import dev.andante.onastick.OnAStick
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemGroups
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Identifier

@Suppress("UnstableApiUsage")
object OnAStickItemGroups {
    const val ALL_TRANSLATION_KEY = "itemGroup.${OnAStick.MOD_ID}.all"

    val ALL = register("all", FabricItemGroup.builder()
        .displayName(Text.translatable(ALL_TRANSLATION_KEY))
        .entries { _, entries ->
            Registries.ITEM
                .filterIsInstance<OnAStickItem>()
                .map(::ItemStack)
                .forEach(entries::add)
        }
        .icon { ItemStack(OnAStickItems.CRAFTING_TABLE_ON_A_STICK) }
        .build()
    )

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

    private fun register(id: String, group: ItemGroup): ItemGroup {
        return Registry.register(Registries.ITEM_GROUP, Identifier(OnAStick.MOD_ID, id), group)
    }
}
