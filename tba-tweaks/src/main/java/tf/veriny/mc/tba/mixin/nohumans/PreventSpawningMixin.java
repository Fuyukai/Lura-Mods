package tf.veriny.mc.tba.mixin.nohumans;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tf.veriny.mc.tweaks.MakeUp;

import java.util.Set;

@Mixin(ServerWorld.class)
public abstract class PreventSpawningMixin extends World {
    private PreventSpawningMixin() {
        super(null, null, null, null, false, false, 0, 0);
    }

    @Inject(method = "addEntity", at = @At("HEAD"), cancellable = true)
    void hijackAddEntity(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        var config = MakeUp.INSTANCE.getConfig();
        var entityId = Registry.ENTITY_TYPE.getId(entity.getType());

        if (config.entitySpawnConfig().blacklistedEntities().contains(entityId)) {
            entity.remove(Entity.RemovalReason.DISCARDED);
            cir.setReturnValue(false);
        }
    }
}
