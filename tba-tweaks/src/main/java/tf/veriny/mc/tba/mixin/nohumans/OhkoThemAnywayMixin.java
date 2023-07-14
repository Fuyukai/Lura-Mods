package tf.veriny.mc.tba.mixin.nohumans;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tf.veriny.mc.tba.EntityKillerOptimisation;
import tf.veriny.mc.tweaks.MakeUp;

@Mixin(MobEntity.class)
abstract class OhkoThemAnywayMixin extends LivingEntity {
    @SuppressWarnings("DataFlowIssue")
    private OhkoThemAnywayMixin() {
        super(null, null);
    }

    @Inject(method = "tickNewAi", at = @At("HEAD"), cancellable = true)
    void dieMobs(CallbackInfo ci) {
        var type = (EntityKillerOptimisation)getType();
        if (!type.tba$hasBeenChecked()) {
            var isInList = MakeUp.INSTANCE.getConfig()
                    .entitySpawnConfig()
                    .blacklistedEntities()
                    .contains(EntityType.getId(getType()));

            type.tba$markChecked(true);
            type.tba$markIfShouldKill(isInList);
        }

        if (type.tba$checkIfShouldKill() && MakeUp.INSTANCE.getConfig().entitySpawnConfig().ohkoEnemies()) {
            this.kill();
            ci.cancel();
        }
    }
}
