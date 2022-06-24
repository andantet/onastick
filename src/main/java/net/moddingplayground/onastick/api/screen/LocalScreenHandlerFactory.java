package net.moddingplayground.onastick.api.screen;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerFactory;

import java.util.function.Supplier;

@FunctionalInterface
public interface LocalScreenHandlerFactory {
    ScreenHandler create(int syncId, PlayerInventory inventory, ScreenHandlerContext context, Item item);

    static ScreenHandlerFactory build(LocalScreenHandlerFactory factory, Supplier<Item> item) {
        return (syncId, inventory, player) -> factory.create(syncId, inventory, ScreenHandlerContext.create(player.getWorld(), player.getBlockPos()), item.get());
    }
}
