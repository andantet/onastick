package net.moddingplayground.onastick.impl;

import com.google.common.reflect.Reflection;
import net.fabricmc.api.ModInitializer;
import net.moddingplayground.frame.api.util.InitializationLogger;
import net.moddingplayground.onastick.api.OnAStick;
import net.moddingplayground.onastick.api.item.OnAStickItems;

public final class OnAStickImpl implements OnAStick, ModInitializer {
    private final InitializationLogger initializer;

    public OnAStickImpl() {
        this.initializer = new InitializationLogger(LOGGER, MOD_NAME);
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    public void onInitialize() {
        this.initializer.start();
        Reflection.initialize(OnAStickItems.class);
        this.initializer.finish();
    }
}
