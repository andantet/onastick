package net.moddingplayground.onastick.mixin;

import net.minecraft.block.LoomBlock;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LoomBlock.class)
public interface LoomBlockAccessor {
    @Accessor static Text getTITLE() { throw new AssertionError(); }
}
