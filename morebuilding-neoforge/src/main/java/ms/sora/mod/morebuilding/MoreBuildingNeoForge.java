package ms.sora.mod.morebuilding;

import ms.sora.mod.morebuilding.common.MoreBuildingIds;
import ms.sora.mod.morebuilding.common.MoreBuildingInfo;
import ms.sora.mod.morebuilding.neoforge.MBBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * More Building Entrypoint for NeoForge
 */
@Mod(MoreBuildingInfo.MOD_ID)
public class MoreBuildingNeoForge {
    /**
     * Logger
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(MoreBuildingInfo.MOD_NAME);

    private static final DeferredRegister<ItemGroup> ITEM_GROUPS = DeferredRegister.create(Registries.ITEM_GROUP.getKey(), MoreBuildingInfo.MOD_ID);

    /**
     * Creative tab
     */
    public static final ItemGroup MORE_BUILDING_TAB = ITEM_GROUPS.register(MoreBuildingIds.MORE_BUILDING_TAB_ID, () -> ItemGroup.builder().icon(() -> new ItemStack(MBBlocks.BIG_BRICKS.asItem())).name(Text.translatable("itemGroup." + MoreBuildingInfo.MOD_ID + "." + MoreBuildingIds.MORE_BUILDING_TAB_ID)).entries((ctx, ents) -> {
        ents.addItem(MBBlocks.BIG_BRICKS);
        ents.addItem(MBBlocks.SMALL_STONE_BRICKS);
    }).build()).get();

    /**
     * Initializes this mod.
     */
    public MoreBuildingNeoForge() {
        LOGGER.info("Starting initializing More Building...");
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEM_GROUPS.register(bus);
        bus.addListener(this::register);
        LOGGER.info("Finished initializing!\nWelcome to More Building!");
    }

    /**
     * Registers what must be registered.
     *
     * @param event RegisterEvent
     */
    @SubscribeEvent
    public void register(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.BLOCKS, reg -> {
            reg.register(new Identifier(MoreBuildingInfo.MOD_ID, MoreBuildingIds.BIG_BRICKS_ID), MBBlocks.BIG_BRICKS);
            reg.register(new Identifier(MoreBuildingInfo.MOD_ID, MoreBuildingIds.SMALL_STONE_BRICKS_ID), MBBlocks.SMALL_STONE_BRICKS);
        });
        event.register(ForgeRegistries.Keys.ITEMS, reg -> {
            reg.register(new Identifier(MoreBuildingInfo.MOD_ID, MoreBuildingIds.BIG_BRICKS_ID), new BlockItem(MBBlocks.BIG_BRICKS, new Item.Settings()));
            reg.register(new Identifier(MoreBuildingInfo.MOD_ID, MoreBuildingIds.SMALL_STONE_BRICKS_ID), new BlockItem(MBBlocks.SMALL_STONE_BRICKS, new Item.Settings()));
        });
    }
}
