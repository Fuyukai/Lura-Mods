package tf.veriny.mc.tba.mixin.nohumans;

import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tf.veriny.mc.tweaks.MakeUp;

@Mixin(CarvedPumpkinBlock.class)
abstract class PreventGolemFormation {
    private PreventGolemFormation() {}

    @Inject(method = "trySpawnEntity", at = @At("HEAD"), cancellable = true)
    void killGolems(World world, BlockPos pos, CallbackInfo ci) {
        if (MakeUp.INSTANCE.getConfig().entitySpawnConfig().preventGolemFormation()) {
            ci.cancel();
        }
    }
}
