package dev.andante.onastick.data.server

import dev.andante.onastick.OnAStick
import dev.andante.onastick.item.OnAStickItems
import dev.andante.onastick.tag.OnAStickItemTags
import java.util.function.Consumer
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder
import net.minecraft.item.Item
import net.minecraft.recipe.book.RecipeCategory
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier

/**
 * Generates On A Stick recipes.
 */
class OnAStickRecipeProvider(out: FabricDataOutput) : FabricRecipeProvider(out) {
    override fun generate(exporter: Consumer<RecipeJsonProvider>) {
        registerStick(OnAStickItems.CRAFTING_TABLE_ON_A_STICK, OnAStickItemTags.CRAFTS_CRAFTING_TABLE_ON_A_STICK, exporter)
        registerStick(OnAStickItems.SMITHING_TABLE_ON_A_STICK, OnAStickItemTags.CRAFTS_SMITHING_TABLE_ON_A_STICK, exporter)
        registerStick(OnAStickItems.LOOM_ON_A_STICK, OnAStickItemTags.CRAFTS_LOOM_ON_A_STICK, exporter)
        registerStick(OnAStickItems.CARTOGRAPHY_TABLE_ON_A_STICK, OnAStickItemTags.CRAFTS_CARTOGRAPHY_TABLE_ON_A_STICK, exporter)
        registerStick(OnAStickItems.GRINDSTONE_ON_A_STICK, OnAStickItemTags.CRAFTS_GRINDSTONE_ON_A_STICK, exporter)
        registerStick(OnAStickItems.ANVIL_ON_A_STICK, OnAStickItemTags.CRAFTS_ANVIL_ON_A_STICK, exporter)
        registerStick(OnAStickItems.STONECUTTER_ON_A_STICK, OnAStickItemTags.CRAFTS_STONECUTTER_ON_A_STICK, exporter)
    }

    private fun registerStick(item: Item, tagKey: TagKey<Item>, exporter: Consumer<RecipeJsonProvider>) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.TOOLS, item)
            .criterion("has_base", conditionsFromTag(tagKey))
            .group(CRAFTING_GROUP)
            .input(OnAStickItemTags.WOODEN_STICKS)
            .input(tagKey)
            .offerTo(exporter)
    }

    companion object {
        val CRAFTING_GROUP = Identifier(OnAStick.MOD_ID, "sticks").toString()
    }
}
