package ms.sora.mod.morebuilding.neoforge;

import dev.architectury.platform.forge.EventBuses;
import ms.sora.mod.morebuilding.common.MBBlockEntities;
import ms.sora.mod.morebuilding.common.MBCore;
import ms.sora.mod.morebuilding.common.MBInfo;
import ms.sora.mod.morebuilding.common.screen.BackpackScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * MBNeoForge is a NeoForge-dependent part of entrypoint of this mod.
 * <p>
 * This class contains mod initializer.
 *
 * @since 0.1.0
 */
@Mod(MBInfo.MOD_ID)
@Mod.EventBusSubscriber(modid = MBInfo.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MBNeoForge {
    /**
     * Constructs this mod.
     */
    public MBNeoForge() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(MBInfo.MOD_ID, bus);
        MBCore.init();
    }

    /**
     * MBNeoForge$Client is a NeoForge-dependent part of client entrypoint of this mod.
     * <p>
     * This class contains mod initializer on client side.
     *
     * @since 0.1.0
     */
    @Mod.EventBusSubscriber(modid = MBInfo.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    @OnlyIn(Dist.CLIENT)
    public static final class Client {
        /**
         * Initializes this mod on client side.
         *
         * @param event the event
         */
        @SubscribeEvent
        @OnlyIn(Dist.CLIENT)
        public static void init(FMLClientSetupEvent event) {
            MBCore.LOG.info("Initializing More Building for NeoForge on client side...");
            MBCore.Client.init();
            event.enqueueWork(() -> HandledScreens.register(MBBlockEntities.BACKPACK_SCREEN_HANDLER.get(), BackpackScreen::new));
            MBCore.LOG.debug("Queued registering screens.");
            MBCore.LOG.info("Finished initializing on client side!");
        }
    }
}
