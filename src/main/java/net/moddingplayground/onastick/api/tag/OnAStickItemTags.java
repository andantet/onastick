package net.moddingplayground.onastick.api.tag;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.onastick.api.OnAStick;

public interface OnAStickItemTags {
    TagKey<Item> STICKS = create("sticks");

    TagKey<Item> CRAFTS_CRAFTING_TABLE_ON_A_STICK = create("crafts/crafting_table_on_a_stick");
    TagKey<Item> CRAFTS_SMITHING_TABLE_ON_A_STICK = create("crafts/smithing_table_on_a_stick");
    TagKey<Item> CRAFTS_LOOM_ON_A_STICK = create("crafts/loom_on_a_stick");
    TagKey<Item> CRAFTS_CARTOGRAPHY_TABLE_ON_A_STICK = create("crafts/cartography_table_on_a_stick");
    TagKey<Item> CRAFTS_GRINDSTONE_ON_A_STICK = create("crafts/grindstone_on_a_stick");
    TagKey<Item> CRAFTS_ANVIL_ON_A_STICK = create("crafts/anvil_on_a_stick");
    TagKey<Item> CRAFTS_STONECUTTER_ON_A_STICK = create("crafts/stonecutter_on_a_stick");

    private static TagKey<Item> create(String id) {
        return TagKey.of(Registry.ITEM_KEY, new Identifier(OnAStick.MOD_ID, id));
    }
}
