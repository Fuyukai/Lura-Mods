package tf.veriny.mc.tags

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
import net.minecraft.client.MinecraftClient
import net.minecraft.client.item.TooltipContext
import net.minecraft.client.util.InputUtil
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemStack
import net.minecraft.registry.tag.TagKey
import net.minecraft.text.Text
import net.minecraft.text.TextColor
import net.minecraft.util.Formatting

/**
 * Main entrypoint.
 */
public object MakeUp : ClientModInitializer, ItemTooltipCallback {
    // no customisation for you. fuck you!
    private const val GLFW_KEY_LEFT_SHIFT = 340

    override fun onInitializeClient() {
        ItemTooltipCallback.EVENT.register(this)
    }

    /**
     * Adds the IDs of [tags] to the provided list of text. [headerKey] is the lang key for the header
     * text to be appended.
     */
    private fun <T> addTagLines(
        headerKey: String,
        lines: MutableList<Text>,
        tags: List<TagKey<T>>
    ) {
        lines.add(
            Text.translatable(headerKey)
                .styled {
                    it.withItalic(true).withColor(Formatting.WHITE)
                }
        )

        for (tag in tags) {
            val id = tag.id
            val text = Text.literal(id.toString())
                .formatted(Formatting.DARK_GRAY)
            lines.add(text)
        }
    }

    override fun getTooltip(
        stack: ItemStack,
        context: TooltipContext,
        lines: MutableList<Text>
    ) {
        if (!context.isAdvanced) return
        // eeh?
        val winHandle = MinecraftClient.getInstance()?.window?.handle ?: return

        // load both item and block tags first, that way we can short circuit and not show
        // anything if theres no tags at all.
        val itemTags = stack.registryEntry.streamTags().toList()
        val item = stack.item
        val blockTags = if (item !is BlockItem) {
            emptyList()
        } else {
            // not really deprecated. it's not like doing `block.defaultState.registryEntry` is
            // any better.
            @Suppress("DEPRECATION")
            item.block.registryEntry.streamTags().toList()
        }

        if (itemTags.isEmpty() && blockTags.isEmpty()) return

        // only show with shift cos it looks kinda bad otherwise
        // man the text stuff really kinda fucking sucks?
        if (!InputUtil.isKeyPressed(winHandle, GLFW_KEY_LEFT_SHIFT)) {
            lines.add(
                Text.translatable("tagview.press")
                    .styled {
                        it.withColor(
                            TextColor.fromFormatting(Formatting.DARK_GRAY)
                        ).withItalic(true)
                    }
            )
            return
        }

        if (itemTags.isNotEmpty()) {
            addTagLines("tagview.header.items", lines, itemTags)
        }

        if (blockTags.isNotEmpty()) {
            addTagLines("tagview.header.blocks", lines, blockTags)
        }
    }
}