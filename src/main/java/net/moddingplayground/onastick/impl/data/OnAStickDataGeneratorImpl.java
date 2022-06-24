package net.moddingplayground.onastick.impl.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.impl.tag.convention.TagRegistration;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.onastick.api.OnAStick;
import net.moddingplayground.onastick.api.item.OnAStickItem;
import net.moddingplayground.onastick.api.tag.OnAStickItemTags;

import java.util.function.Consumer;
import java.util.stream.Stream;

public final class OnAStickDataGeneratorImpl implements OnAStick, DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator gen) {
        gen.addProvider(ModelProvider::new);
        gen.addProvider(ItemTagProvider::new);
        gen.addProvider(RecipeProvider::new);
    }

    private static Stream<Item> getOnAStickItems() {
        return Registry.ITEM.stream().filter(OnAStickDataGeneratorImpl::isOnAStick);
    }

    private static boolean isOnAStick(Item item) {
        Identifier id = Registry.ITEM.getId(item);
        return id.getNamespace().equals(OnAStick.MOD_ID);
    }

    /* Models */

    private static class ModelProvider extends FabricModelProvider {
        public ModelProvider(FabricDataGenerator gen) {
            super(gen);
        }

        @Override public void generateBlockStateModels(BlockStateModelGenerator gen) {}

        @Override
        public void generateItemModels(ItemModelGenerator gen) {
            getOnAStickItems().forEach(item -> gen.register(item, Models.HANDHELD));
        }
    }

    /* Tags */

    private static class ItemTagProvider extends FabricTagProvider.ItemTagProvider {
        public ItemTagProvider(FabricDataGenerator gen) {
            super(gen);
        }

        @Override
        protected void generateTags() {
            this.createTagWithItemAndConventional(OnAStickItemTags.STICKS, Items.STICK, "sticks");
            this.createTagWithItemAndConventional(OnAStickItemTags.CRAFTS_CRAFTING_TABLE_ON_A_STICK, Items.CRAFTING_TABLE, "crafting_tables");
            this.createTagWithItemAndConventional(OnAStickItemTags.CRAFTS_SMITHING_TABLE_ON_A_STICK, Items.SMITHING_TABLE, "smithing_tables");
            this.createTagWithItemAndConventional(OnAStickItemTags.CRAFTS_LOOM_ON_A_STICK, Items.LOOM, "looms");
            this.createTagWithItemAndConventional(OnAStickItemTags.CRAFTS_CARTOGRAPHY_TABLE_ON_A_STICK, Items.CARTOGRAPHY_TABLE, "cartography_tables");
            this.createTagWithItemAndConventional(OnAStickItemTags.CRAFTS_GRINDSTONE_ON_A_STICK, Items.GRINDSTONE, "grindstones");
            this.createTagWithItemAndConventional(OnAStickItemTags.CRAFTS_ANVIL_ON_A_STICK, Items.ANVIL, "anvils", ItemTags.ANVIL);
            this.createTagWithItemAndConventional(OnAStickItemTags.CRAFTS_STONECUTTER_ON_A_STICK, Items.STONECUTTER, "stonecutters");
        }

        @SafeVarargs
        protected final void createTagWithItemAndConventional(TagKey<Item> tag, Item item, String conventional, TagKey<Item>... extras) {
            FabricTagBuilder<Item> builder = this.getOrCreateTagBuilder(tag)
                                                 .add(item)
                                                 .addOptionalTag(TagRegistration.ITEM_TAG_REGISTRATION.registerCommon(conventional));

            for (TagKey<Item> extra : extras) builder.forceAddTag(extra);
        }
    }

    /* Recipes */

    private static class RecipeProvider extends FabricRecipeProvider {
        public RecipeProvider(FabricDataGenerator gen) {
            super(gen);
        }

        @Override
        protected void generateRecipes(Consumer<RecipeJsonProvider> gen) {
            getOnAStickItems().filter(OnAStickItem.class::isInstance)
                              .map(OnAStickItem.class::cast)
                              .forEach(item -> {
                                  TagKey<Item> tag = item.getTag();
                                  ShapelessRecipeJsonBuilder.create(item)
                                      .input(OnAStickItemTags.STICKS)
                                      .input(tag)
                                      .criterion("has_base", conditionsFromTag(tag))
                                      .group(OnAStick.MOD_ID + ":sticks")
                                      .offerTo(gen);
                              });
        }
    }
}
