package dev.andante.onastick.screen

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.Item
import net.minecraft.screen.AnvilScreenHandler
import net.minecraft.screen.ScreenHandlerContext

class LocalAnvilScreenHandler(
    syncId: Int,
    inventory: PlayerInventory,
    context: ScreenHandlerContext,
    vararg items: Item
) : AnvilScreenHandler(syncId, inventory, context) {
    private val set = setOf(*items)

    override fun canUse(player: PlayerEntity): Boolean {
        return player.inventory.containsAny(set)
    }
}
