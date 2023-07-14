package tf.veriny.mc.tba.mixin.heightlimit;

import net.minecraft.block.CactusBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(CactusBlock.class)
public abstract class HigherCactusGrowthLimit {
    private HigherCactusGrowthLimit() {}

    @ModifyConstant(method = "randomTick", constant = @Constant(intValue = 3))
    int fn(int constant) {
        // 512 should be good enough for anybody!
        return 512;
    }
}
