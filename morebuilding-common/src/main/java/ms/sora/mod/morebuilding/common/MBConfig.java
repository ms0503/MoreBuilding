package ms.sora.mod.morebuilding.common;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraft.client.gui.screen.Screen;

/**
 * MBConfig is a manager class of config.
 * <p>
 * This class contains configurations and utilities to manage config.
 *
 * @since 0.1.0
 */
public final class MBConfig {
    /**
     * A config data
     */
    public static Data config;

    private MBConfig() {
    }

    /**
     * Initializes config.
     */
    public static void init() {
        AutoConfig.register(Data.class, JanksonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(Data.class).getConfig();
    }

    /**
     * Gives config screen by Cloth Config.
     *
     * @param parent the parent screen
     *
     * @return the config screen
     */
    public static Screen getConfigScreenByCloth(Screen parent) {
        return AutoConfig.getConfigScreen(Data.class, parent).get();
    }

    /**
     * MBConfig$Data is a data structure of More Building config.
     * <p>
     * This class defines a structure of config data.
     *
     * @since 0.1.0
     */
    @Config(name = MBInfo.MOD_ID)
    public static class Data implements ConfigData {
    }
}
