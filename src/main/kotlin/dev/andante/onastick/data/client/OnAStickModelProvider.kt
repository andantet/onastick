package dev.andante.onastick.data.client

import dev.andante.onastick.item.OnAStickItem
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.Models
import net.minecraft.registry.Registries

/**
 * Generates On A Stick item models.
 */
class OnAStickModelProvider(out: FabricDataOutput) : FabricModelProvider(out) {
    override fun generateBlockStateModels(generator: BlockStateModelGenerator) {
    }

    override fun generateItemModels(generator: ItemModelGenerator) {
        generator.run {
            Registries.ITEM
                .filterIsInstance<OnAStickItem>()
                .forEach { register(it, Models.HANDHELD) }
        }
    }
}
