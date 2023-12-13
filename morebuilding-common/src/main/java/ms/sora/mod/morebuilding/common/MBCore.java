package ms.sora.mod.morebuilding.common;

import dev.architectury.platform.Platform;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.architectury.utils.Env;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MBCore is a platform-independent part of entrypoint of this mod.
 * <p>
 * This class contains logger, mod initializer, registry manager and creative tabs.
 *
 * @since 0.1.0-beta0
 */
public final class MBCore {
    /**
     * A logger
     */
    public static final Logger LOG = LoggerFactory.getLogger(MBInfo.MOD_NAME);
    /**
     * A register of creative tab
     */
    public static final DeferredRegister<ItemGroup> TABS = DeferredRegister.create(MBInfo.MOD_ID, RegistryKeys.ITEM_GROUP);
    /**
     * The More Building Tab
     */
    public static final RegistrySupplier<ItemGroup> MORE_BUILDING_TAB = TABS.register(new Identifier(MBInfo.MOD_ID, "tab"), () -> CreativeTabRegistry.create(Text.translatable("itemGroup.%s.tab".formatted(MBInfo.MOD_ID)), () -> new ItemStack(MBBlocks.BACKPACK.get())));

    private MBCore() {
    }

    /**
     * Initializes this mod.
     */
    public static void init() {
        LOG.info("Initializing More Building Core...");
        MBConfig.init();
        LOG.info("Initialized config.");
        TABS.register();
        LOG.info("Registered creative tabs.");
        MBBlocks.BLOCKS.register();
        LOG.info("Registered blocks.");
        MBItems.ITEMS.register();
        LOG.info("Registered items.");
        MBBlockEntities.BLOCK_ENTITIES.register();
        LOG.info("Registered block entities.");
        MBBlockEntities.SCREEN_HANDLERS.register();
        LOG.info("Registered screen handlers.");
        LOG.info("Finished initializing Core!");
    }

    /**
     * MBCore$Client is a platform-independent part of client entrypoint of this mod.
     * <p>
     * This class contains mod initializer on client side.
     *
     * @since 0.1.0
     */
    public static final class Client {
        /**
         * Initializes this mod on client side.
         */
        public static void init() {
            if(Platform.getEnvironment() != Env.CLIENT)
                throw new IllegalStateException("Client initialization was not called on client side!");
            LOG.info("Initializing More Building Core on client side...");
            LOG.info("Finished initializing Core on client side!");
        }
    }
}
