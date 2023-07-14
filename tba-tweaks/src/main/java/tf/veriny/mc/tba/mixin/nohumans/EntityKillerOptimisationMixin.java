package tf.veriny.mc.tba.mixin.nohumans;

import net.minecraft.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import tf.veriny.mc.tba.EntityKillerOptimisation;

@Mixin(EntityType.class)
final class EntityKillerOptimisationMixin implements EntityKillerOptimisation {
    @Unique
    private boolean tba$shouldKill = false;

    @Unique
    private boolean tba$isChecked = false;

    @Override
    public boolean tba$checkIfShouldKill() {
        return tba$shouldKill;
    }

    @Override
    public boolean tba$hasBeenChecked() {
        return tba$isChecked;
    }

    @Override
    public void tba$markChecked(boolean value) {
        tba$isChecked = value;
    }

    @Override
    public void tba$markIfShouldKill(boolean value) {
        tba$shouldKill = value;
    }
}
