package ms.sora.mod.morebuilding.fabric;

import ms.sora.mod.morebuilding.common.MBBlockEntities;
import ms.sora.mod.morebuilding.common.MBCore;
import ms.sora.mod.morebuilding.common.screen.BackpackScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

/**
 * MBFabric is a Fabric-dependent part of entrypoint of this mod.
 * <p>
 * This class contains mod initializer.
 *
 * @since 0.1.0-alpha0
 */
@SuppressWarnings("unused")
public final class MBFabric implements ModInitializer {
    /**
     * Initializes this mod.
     */
    @Override
    public void onInitialize() {
        MBCore.LOG.info("Initializing More Building for Fabric...");
        MBCore.init();
        MBCore.LOG.info("Finished initializing!");
    }

    /**
     * MBFabric$Client is a Fabric-dependent part of client entrypoint of this mod.
     * <p>
     * This class contains mod initializer on client side.
     *
     * @since 0.1.0
     */
    @Environment(EnvType.CLIENT)
    public static final class Client implements ClientModInitializer {
        /**
         * Initializes this mod on client side.
         */
        @Override
        @Environment(EnvType.CLIENT)
        public void onInitializeClient() {
            MBCore.LOG.info("Initializing More Building for Fabric on client side...");
            MBCore.Client.init();
            HandledScreens.register(MBBlockEntities.BACKPACK_SCREEN_HANDLER.get(), BackpackScreen::new);
            MBCore.LOG.debug("Registered screens.");
            MBCore.LOG.info("Finished initializing on client side!");
        }
    }
}
