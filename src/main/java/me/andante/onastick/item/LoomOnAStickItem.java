package me.andante.onastick.item;

import me.andante.onastick.mixin.LoomBlockAccessor;
import me.andante.onastick.screen.LoomOnAStickScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;

public class LoomOnAStickItem extends AbstractOnAStickItem {
    public LoomOnAStickItem(Settings settings) {
        super(settings);
    }

    @Override
    public ScreenHandler createScreenHandler(int syncId, PlayerInventory inventory, PlayerEntity player) {
        return new LoomOnAStickScreenHandler(syncId, inventory, ScreenHandlerContext.create(player.world, player.getBlockPos()), this);
    }

    @Override
    public Text getScreenTitle() {
        return LoomBlockAccessor.getTITLE();
    }
}
