package net.moddingplayground.onastick.api.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.screen.ScreenHandlerFactory;
import net.minecraft.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moddingplayground.onastick.api.OnAStick;
import net.moddingplayground.onastick.api.screen.LocalAnvilScreenHandler;
import net.moddingplayground.onastick.api.screen.LocalCartographyTableScreenHandler;
import net.moddingplayground.onastick.api.screen.LocalCraftingScreenHandler;
import net.moddingplayground.onastick.api.screen.LocalGrindstoneScreenHandler;
import net.moddingplayground.onastick.api.screen.LocalLoomScreenHandler;
import net.moddingplayground.onastick.api.screen.LocalScreenHandlerFactory;
import net.moddingplayground.onastick.api.screen.LocalSmithingScreenHandler;
import net.moddingplayground.onastick.api.screen.LocalStonecutterScreenHandler;
import net.moddingplayground.onastick.api.tag.OnAStickItemTags;
import net.moddingplayground.onastick.mixin.AnvilBlockAccessor;
import net.moddingplayground.onastick.mixin.CartographyTableBlockAccessor;
import net.moddingplayground.onastick.mixin.CraftingTableBlockAccessor;
import net.moddingplayground.onastick.mixin.GrindstoneBlockAccessor;
import net.moddingplayground.onastick.mixin.LoomBlockAccessor;
import net.moddingplayground.onastick.mixin.SmithingTableBlockAccessor;
import net.moddingplayground.onastick.mixin.StonecutterBlockAccessor;

import java.util.function.Function;

public interface OnAStickItems {
    Item CRAFTING_TABLE_ON_A_STICK = stick("crafting_table_on_a_stick",
        CraftingTableBlockAccessor.getTITLE(), OnAStickItemTags.CRAFTS_CRAFTING_TABLE_ON_A_STICK,
        LocalScreenHandlerFactory.build(LocalCraftingScreenHandler::new, () -> OnAStickItems.CRAFTING_TABLE_ON_A_STICK)
    );

    Item SMITHING_TABLE_ON_A_STICK = stick("smithing_table_on_a_stick",
        SmithingTableBlockAccessor.getSCREEN_TITLE(), OnAStickItemTags.CRAFTS_SMITHING_TABLE_ON_A_STICK,
        LocalScreenHandlerFactory.build(LocalSmithingScreenHandler::new, () -> OnAStickItems.SMITHING_TABLE_ON_A_STICK)
    );

    Item LOOM_ON_A_STICK = stick("loom_on_a_stick",
        LoomBlockAccessor.getTITLE(), OnAStickItemTags.CRAFTS_LOOM_ON_A_STICK,
        LocalScreenHandlerFactory.build(LocalLoomScreenHandler::new, () -> OnAStickItems.LOOM_ON_A_STICK)
    );

    Item CARTOGRAPHY_TABLE_ON_A_STICK = stick("cartography_table_on_a_stick",
        CartographyTableBlockAccessor.getTITLE(), OnAStickItemTags.CRAFTS_CARTOGRAPHY_TABLE_ON_A_STICK,
        LocalScreenHandlerFactory.build(LocalCartographyTableScreenHandler::new, () -> OnAStickItems.CARTOGRAPHY_TABLE_ON_A_STICK)
    );

    Item GRINDSTONE_ON_A_STICK = stick("grindstone_on_a_stick",
        GrindstoneBlockAccessor.getTITLE(), OnAStickItemTags.CRAFTS_GRINDSTONE_ON_A_STICK,
        LocalScreenHandlerFactory.build(LocalGrindstoneScreenHandler::new, () -> OnAStickItems.GRINDSTONE_ON_A_STICK)
    );

    Item ANVIL_ON_A_STICK = stick("anvil_on_a_stick",
        AnvilBlockAccessor.getTITLE(), OnAStickItemTags.CRAFTS_ANVIL_ON_A_STICK,
        LocalScreenHandlerFactory.build(LocalAnvilScreenHandler::new, () -> OnAStickItems.ANVIL_ON_A_STICK)
    );

    Item STONECUTTER_ON_A_STICK = stick("stonecutter_on_a_stick",
        StonecutterBlockAccessor.getTITLE(), OnAStickItemTags.CRAFTS_STONECUTTER_ON_A_STICK,
        LocalScreenHandlerFactory.build(LocalStonecutterScreenHandler::new, () -> OnAStickItems.STONECUTTER_ON_A_STICK)
    );

    private static Item register(String id, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(OnAStick.MOD_ID, id), item);
    }

    private static Item register(String id, Function<Item.Settings, Item> factory) {
        return register(id, factory.apply(new FabricItemSettings().group(OnAStickItemGroups.ALL)));
    }

    private static Item stick(String id, Text title, TagKey<Item> tag, ScreenHandlerFactory screenHandlerFactory) {
        return register(id, settings -> new OnAStickItem(settings.maxCount(1), screenHandlerFactory, title, tag));
    }
}
