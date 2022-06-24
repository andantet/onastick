package net.moddingplayground.onastick.mixin;

import net.minecraft.block.CraftingTableBlock;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CraftingTableBlock.class)
public interface CraftingTableBlockAccessor {
    @Accessor static Text getTITLE() { throw new AssertionError(); }
}
