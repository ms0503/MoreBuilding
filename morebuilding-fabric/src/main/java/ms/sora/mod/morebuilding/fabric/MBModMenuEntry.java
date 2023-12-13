package ms.sora.mod.morebuilding.fabric;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import ms.sora.mod.morebuilding.common.MBConfig;

/**
 * MBModMenuEntry is a Mod Menu entrypoint.
 *
 * @since 0.1.0
 */
public class MBModMenuEntry implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return MBConfig::getConfigScreenByCloth;
    }
}
