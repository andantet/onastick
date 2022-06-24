package net.moddingplayground.onastick.mixin;

import net.minecraft.block.AnvilBlock;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AnvilBlock.class)
public interface AnvilBlockAccessor {
    @Accessor static Text getTITLE() { throw new AssertionError(); }
}
