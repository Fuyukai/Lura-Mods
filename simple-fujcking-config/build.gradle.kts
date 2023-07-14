version = "0.7.0"

dependencies {
    api(include("cc.ekblad:4koma:1.2.0")!!)
    runtimeOnly(include("cc.ekblad.konbini:konbini-jvm:0.1.3")!!)
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])

        pom {
            name.set(project.name)
            description.set("I hate config mods! I hate config mods!")
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