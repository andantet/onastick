package dev.andante.onastick

import dev.andante.onastick.item.OnAStickItemGroups
import dev.andante.onastick.item.OnAStickItems
import dev.andante.onastick.tag.OnAStickItemTags
import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object OnAStick : ModInitializer {
    const val MOD_ID = "onastick"
    const val MOD_NAME = "On A Stick"
    val LOGGER: Logger = LogManager.getLogger(MOD_NAME)

    override fun onInitialize() {
        LOGGER.info("Initializing $MOD_NAME")

        OnAStickItemTags
        OnAStickItemGroups
        OnAStickItems
    }
}
