package tf.veriny.mc.tba.mixin.nohumans;

import net.minecraft.entity.Entity;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tf.veriny.mc.tweaks.MakeUp;

@Mixin(ServerWorld.class)
public abstract class PreventSpawningMixin extends World {
    private PreventSpawningMixin() {
        super(null, null, null, null, null, false, false, 0, 0);
    }

    @Inject(method = "addEntity", at = @At("HEAD"), cancellable = true)
    void hijackAddEntity(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        var config = MakeUp.INSTANCE.getConfig();
        var entityId = Registries.ENTITY_TYPE.getId(entity.getType());

        if (config.entitySpawnConfig().blacklistedEntities().contains(entityId)) {
            entity.remove(Entity.RemovalReason.DISCARDED);
            cir.setReturnValue(false);
        }
    }
}
