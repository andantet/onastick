package net.moddingplayground.onastick.api.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerFactory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class OnAStickItem extends Item {
    private final ScreenHandlerFactory baseFactory;
    private final Text title;
    private final TagKey<Item> tag;

    public OnAStickItem(Settings settings, ScreenHandlerFactory baseFactory, Text title, TagKey<Item> tag) {
        super(settings);
        this.baseFactory = baseFactory;
        this.title = title;
        this.tag = tag;
    }

    public TagKey<Item> getTag() {
        return this.tag;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.openHandledScreen(this.createScreenHandlerFactory());
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    public NamedScreenHandlerFactory createScreenHandlerFactory() {
        return new SimpleNamedScreenHandlerFactory(this.baseFactory, this.title);
    }
}
