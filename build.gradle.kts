
import juuxel.vineflowerforloom.api.VineflowerExtension
import net.fabricmc.loom.api.LoomGradleExtensionAPI

/*
 * This file was generated by the Gradle 'init' task.
 *
 * This is a general purpose Gradle build.
 * Learn more about Gradle by exploring our samples at https://docs.gradle.org/8.1.1/samples
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    id("fabric-loom").version("1.3.8").apply(false)
    id("io.github.juuxel.loom-vineflower").version("1.11.0").apply(false)
}

allprojects {
    group = "tf.veriny.mc"
}


subprojects {
    apply(plugin = "base-plugin")
    apply(plugin = "fabric-loom")
    apply(plugin = "io.github.juuxel.loom-vineflower")

    val qf = the<VineflowerExtension>()
    qf.fromProjectRepositories()

    val loom = the<LoomGradleExtensionAPI>()


    dependencies {
        "minecraft"("com.mojang:minecraft:1.19.2")

        "mappings"(loom.layered {
            mappings("net.fabricmc:yarn:1.19.2+build.28:v2")
        })

        "modImplementation"("net.fabricmc:fabric-loader:0.14.21")
        "modImplementation"("net.fabricmc.fabric-api:fabric-api:0.76.0+1.19.2")
        "modImplementation"("net.fabricmc:fabric-language-kotlin:1.10.0+kotlin.1.9.0")
    }
}