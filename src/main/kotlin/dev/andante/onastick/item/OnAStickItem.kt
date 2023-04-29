package dev.andante.onastick.item

import dev.andante.onastick.screen.LocalScreenHandlerFactory
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.screen.SimpleNamedScreenHandlerFactory
import net.minecraft.text.Text
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

/**
 * Represents an item classified as 'On A Stick'.
 */
class OnAStickItem(
    settings: Settings,

    /**
     * The base screen handler factory.
     */
    baseFactory: LocalScreenHandlerFactory,

    /**
     * The title of the stick's opened screen.
     */
    private val title: Text
) : Item(settings) {
    private val baseFactory = LocalScreenHandlerFactory.build(baseFactory) { this }

    /**
     * Creates a screen handler factory for this stick.
     */
    private val screenHandlerFactory get() =
        SimpleNamedScreenHandlerFactory(baseFactory, title)

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        user.openHandledScreen(screenHandlerFactory)
        return TypedActionResult.success(user.getStackInHand(hand))
    }
}
