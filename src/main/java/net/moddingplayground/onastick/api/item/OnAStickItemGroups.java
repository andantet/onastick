package net.moddingplayground.onastick.api.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.moddingplayground.onastick.api.OnAStick;

public interface OnAStickItemGroups {
    ItemGroup ALL = FabricItemGroupBuilder.build(new Identifier(OnAStick.MOD_ID, "all"), () -> new ItemStack(OnAStickItems.CRAFTING_TABLE_ON_A_STICK));
}
