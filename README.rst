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

Simple Fujcking Config
----------------------

Tiny, embed-only, both-sides config library. Because I hate that there's a million libs that use
fucking annotations everywhere. No config GUI for you!

Tag View
--------

EMI is very stingy about showing tags so this just adds a list of them to the tooltip.

TBA Tweaks
----------

Various miscellaneous tweaks for my upcoming modpack, packaged into a single mod.

- Makes cactus and sugar cane grow higher. Yay!
- Allows disabling mob spawns (configurable). Stops them from spawning and OHKOs any mobs in the
  world to maximise effectiveness. This replaces the badly licensed and non-functional You Shall
  Not Spawn mod.