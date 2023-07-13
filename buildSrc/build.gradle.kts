import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-gradle-plugin`
    kotlin("jvm").version("1.9.0")
}

repositories {
    gradlePluginPortal()
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.19.0")
    implementation("org.gradle.toolchains:foojay-resolver:0.5.0")
}

kotlin {
    explicitApi = ExplicitApiMode.Strict
}

gradlePlugin {
    plugins {
        create("base-plugin") {
            id = "base-plugin"
            implementationClass = "tf.veriny.mc.gradle.BasePlugin"
        }
    }
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "20"
        }
    }

    withType<JavaCompile> {
        targetCompatibility = "20"
    }
}
