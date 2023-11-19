package ms.sora.mod.morebuilding;

import ms.sora.mod.morebuilding.common.MoreBuildingIds;
import ms.sora.mod.morebuilding.common.MoreBuildingInfo;
import ms.sora.mod.morebuilding.fabric.MBBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * More Building Entrypoint for Fabric
 */
public class MoreBuildingFabric implements ModInitializer {
    /**
     * Logger
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(MoreBuildingInfo.MOD_NAME);

    /**
     * Creative tab
     */
    public static final ItemGroup MORE_BUILDING_TAB = FabricItemGroup.builder().icon(() -> new ItemStack(MBBlocks.BIG_BRICKS.asItem())).name(Text.translatable("itemGroup." + MoreBuildingInfo.MOD_ID + "." + MoreBuildingIds.MORE_BUILDING_TAB_ID)).entries((ctx, ents) -> {
        ents.addItem(MBBlocks.BIG_BRICKS);
        ents.addItem(MBBlocks.SMALL_STONE_BRICKS);
    }).build();

    /**
     * Initializes this mod.
     */
    @Override
    public void onInitialize() {
        LOGGER.info("Starting initializing More Building...");
        Registry.register(Registries.BLOCK, new Identifier(MoreBuildingInfo.MOD_ID, MoreBuildingIds.BIG_BRICKS_ID), MBBlocks.BIG_BRICKS);
        Registry.register(Registries.BLOCK, new Identifier(MoreBuildingInfo.MOD_ID, MoreBuildingIds.SMALL_STONE_BRICKS_ID), MBBlocks.SMALL_STONE_BRICKS);
        Registry.register(Registries.ITEM, new Identifier(MoreBuildingInfo.MOD_ID, MoreBuildingIds.BIG_BRICKS_ID), new BlockItem(MBBlocks.BIG_BRICKS, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(MoreBuildingInfo.MOD_ID, MoreBuildingIds.SMALL_STONE_BRICKS_ID), new BlockItem(MBBlocks.SMALL_STONE_BRICKS, new FabricItemSettings()));
        Registry.register(Registries.ITEM_GROUP, new Identifier(MoreBuildingInfo.MOD_ID, MoreBuildingIds.MORE_BUILDING_TAB_ID), MORE_BUILDING_TAB);
        LOGGER.info("Finished initializing!\nWelcome to More Building!");
    }
}
