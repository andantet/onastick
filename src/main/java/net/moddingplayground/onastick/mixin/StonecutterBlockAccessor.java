package net.moddingplayground.onastick.mixin;

import net.minecraft.block.StonecutterBlock;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(StonecutterBlock.class)
public interface StonecutterBlockAccessor {
    @Accessor static Text getTITLE() { throw new AssertionError(); }
}
