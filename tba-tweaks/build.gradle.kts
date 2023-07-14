version = "1902.0.0"

dependencies {
    implementation(include(project(":simple-fujcking-config", configuration = "namedElements"))!!)
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])

        pom {
            name.set(project.name)
            description.set("Tweaks for my modpack")
            url.set("https://github.com/Fuyukai/Lura-Mods")

            licenses {
                license {
                    name.set("MPL-2.0")
                    url.set("https://www.mozilla.org/en-US/MPL/2.0/")
                }
            }

            developers {
                developer {
                    id.set("Fuyukai")
                    name.set("Lura Skye")
                    url.set("https://veriny.tf")
                }
            }
        }
    }
}