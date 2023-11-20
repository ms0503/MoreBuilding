package ms.sora.mod.morebuilding;

import net.fabricmc.api.ModInitializer;

/**
 * More Building Entrypoint for Fabric
 */
public class MoreBuildingFabric implements ModInitializer {
    /**
     * Initializes this mod.
     */
    @Override
    public void onInitialize() {
        MoreBuildingCore.init();
    }
}
