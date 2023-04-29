package dev.andante.onastick.screen

import java.util.function.Supplier
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.Item
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerContext
import net.minecraft.screen.ScreenHandlerFactory

/**
 * A screen factory for a local handled screen.
 */
fun interface LocalScreenHandlerFactory {
    fun create(syncId: Int, inventory: PlayerInventory, context: ScreenHandlerContext, item: Item): ScreenHandler

    companion object {
        fun build(factory: LocalScreenHandlerFactory, item: Supplier<Item>): ScreenHandlerFactory {
            return ScreenHandlerFactory { syncId, inventory, player ->
                factory.create(
                    syncId,
                    inventory,
                    ScreenHandlerContext.create(player.world, player.blockPos),
                    item.get()
                )
            }
        }
    }
}
