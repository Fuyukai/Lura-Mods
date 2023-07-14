package tf.veriny.mc.tweaks

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.EndDataPackReload
import net.minecraft.resource.LifecycledResourceManager
import net.minecraft.server.MinecraftServer
import net.minecraft.util.registry.Registry
import tf.veriny.mc.sfc.SimpleConfig
import tf.veriny.mc.tba.EntityKillerOptimisation

/**
 * Main entrypoint.
 */
public object MakeUp : ModInitializer, EndDataPackReload {
    public var config: TweakConfig = TweakConfig()
        private set

    private fun reloadConfig() {
        val loadedConfig = SimpleConfig.load(
            "tba",
            TweakConfig::class,
            defaultConfig = config,
        )

        // save most recent config back to disk, or the default one for new installs
        if (loadedConfig == null) {
            SimpleConfig.save("tba", config)
        } else {
            this.config = loadedConfig
        }

        // go through every entity type and unmark safe
        for ((k, v) in Registry.ENTITY_TYPE.entrySet) {
            if (v !is EntityKillerOptimisation) continue
            v.`tba$markChecked`(false)
        }
    }

    override fun endDataPackReload(
        server: MinecraftServer,
        resourceManager: LifecycledResourceManager,
        success: Boolean
    ) {
        reloadConfig()
    }

    override fun onInitialize() {
        reloadConfig()
        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register(this)
    }
}