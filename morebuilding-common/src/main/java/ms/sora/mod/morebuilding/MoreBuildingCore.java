package ms.sora.mod.morebuilding;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import ms.sora.mod.morebuilding.common.MBBlocks;
import ms.sora.mod.morebuilding.common.MBItems;
import ms.sora.mod.morebuilding.common.MoreBuildingInfo;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * More Building main class
 */
public class MoreBuildingCore {
    /**
     * Logger
     */
    public static final Logger LOG = LoggerFactory.getLogger(MoreBuildingInfo.MOD_NAME);

    /**
     * Creative tab registry
     */
    public static final DeferredRegister<ItemGroup> TABS = DeferredRegister.create(MoreBuildingInfo.MOD_ID, RegistryKeys.ITEM_GROUP);

    /**
     * Creative tab
     */
    public static final RegistrySupplier<ItemGroup> MORE_BUILDING_TAB = TABS.register("tab", () -> CreativeTabRegistry.create(Text.translatable("itemGroup." + MoreBuildingInfo.MOD_ID + ".tab"), () -> new ItemStack(MBBlocks.BIG_BRICKS.get())));

    /**
     * Initializes this mod.
     */
    public static void init() {
        LOG.info("Initializing More Building...");
        MBBlocks.BLOCKS.register();
        LOG.debug("Registered blocks.");
        MBBlocks.BLOCK_ITEMS.register();
        LOG.debug("Registered block items.");
        MBItems.ITEMS.register();
        LOG.debug("Registered items.");
        TABS.register();
        LOG.debug("Registered creative tabs.");
        LOG.info("Finished initializing!\nWelcome to More Building!");
    }
}
