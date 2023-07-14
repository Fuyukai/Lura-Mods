package tf.veriny.mc.tweaks

import net.minecraft.util.Identifier

@JvmRecord
public data class TweakConfig(
    /** The subconfig for entity spawning restrictions. */
    public val entitySpawnConfig: EntitySpawnConfig = EntitySpawnConfig(),
) {
    /**
     * The configuration for blacklisting entity spawns.
     */
    @JvmRecord
    public data class EntitySpawnConfig(
        /**
         * If true, then ticking entities of the provided type will be instantly killed.
         * Useful to cleanse your world of currently spawned entities or villagers that spawn
         * inside villages.
         */
        public val ohkoEnemies: Boolean = true,

        /**
         * If true, then Golems won't form. This prevents them from spawning and instantly
         * dying.
         */
        public val preventGolemFormation: Boolean = true,

        /**
         * The list of entities that are blacklisted from spawning.
         */
        public val blacklistedEntities: Set<Identifier> = emptySet()
    )
}