package tf.veriny.mc.tba;

// is checked: if false, check the config
// is safe: if true, kill the entity.

/**
 * Used to avoid querying the config set every single time.
 */
public interface EntityKillerOptimisation {
    default boolean tba$hasBeenChecked() {
        return true;
    }

    default void tba$markChecked(boolean value) {

    }

    default boolean tba$checkIfShouldKill() {
        return true;
    };

    default void tba$markIfShouldKill(boolean value) {

    }
}
