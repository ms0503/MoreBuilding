package ms.sora.mod.morebuilding;

import dev.architectury.platform.forge.EventBuses;
import ms.sora.mod.morebuilding.common.MoreBuildingInfo;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * More Building Entrypoint for Forge
 */
@Mod(MoreBuildingInfo.MOD_ID)
public class MoreBuildingForge {
    /**
     * Initializes this mod.
     */
    public MoreBuildingForge() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(MoreBuildingInfo.MOD_ID, bus);
        MoreBuildingCore.init();
    }
}
