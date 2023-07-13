Lura Mods
=========

This is a small handful of quick-n-dirty Fabric mods that I made for things I needed. Just
build them locally and bundle them directly into your pack's mrpack.

All of these are available on my Maven:

.. code-block:: kotlin

    allprojects {
        repositories {
            maven("https://maven.veriny.tf/releases") {
                mavenContent {
                    includeGroupAndSubgroups("tf.veriny")
                }
            }

            // may be needed for some dependencies
            maven("https://maven.veriny.tf/mirror")
        }
    }

Tag View
--------

EMI is very stingy about showing tags so this just adds a list of them to the tooltip.