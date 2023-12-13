package ms.sora.mod.morebuilding.quilt;

import ms.sora.mod.morebuilding.common.MBBlockEntities;
import ms.sora.mod.morebuilding.common.MBCore;
import ms.sora.mod.morebuilding.common.screen.BackpackScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

/**
 * MBQuilt is a Quilt-dependent part of entrypoint of this mod.
 * <p>
 * This class contains mod initializer.
 *
 * @since 0.1.0-alpha0
 */
@SuppressWarnings("unused")
public class MBQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        MBCore.LOG.info("Initializing More Building for Quilt...");
        MBCore.init();
        MBCore.LOG.info("Finished initializing!");
    }

    /**
     * MBQuilt$Client is a Quilt-dependent part of client entrypoint of this mod.
     * <p>
     * This class contains mod initializer on client side.
     *
     * @since 0.1.0
     */
    @ClientOnly
    public static final class Client implements ClientModInitializer {
        @Override
        @ClientOnly
        public void onInitializeClient(ModContainer mod) {
            MBCore.LOG.info("Initializing More Building for Fabric on client side...");
            MBCore.Client.init();
            HandledScreens.register(MBBlockEntities.BACKPACK_SCREEN_HANDLER.get(), BackpackScreen::new);
            MBCore.LOG.debug("Registered screens.");
            MBCore.LOG.info("Finished initializing on client side!");
        }
    }
}
