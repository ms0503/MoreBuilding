package ms.sora.mod.morebuilding;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

/**
 * More Building Entrypoint for Quilt
 */
public class MoreBuildingQuilt implements ModInitializer {
    /**
     * Initializes this mod.
     *
     * @param mod the mod which is initialized.
     */
    @Override
    public void onInitialize(ModContainer mod) {
        MoreBuildingCore.init();
    }
}
