package ms.sora.mod.morebuilding;

import ms.sora.mod.morebuilding.common.MoreBuildingIds;
import ms.sora.mod.morebuilding.common.MoreBuildingInfo;
import ms.sora.mod.morebuilding.quilt.MBBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * More Building Entrypoint for Quilt
 */
public class MoreBuildingQuilt implements ModInitializer {
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
     *
     * @param mod the mod which is initialized.
     */
    @Override
    public void onInitialize(ModContainer mod) {
        LOGGER.info("Starting initializing More Building...");
        Registry.register(Registries.BLOCK, new Identifier(mod.metadata().id(), MoreBuildingIds.BIG_BRICKS_ID), MBBlocks.BIG_BRICKS);
        Registry.register(Registries.BLOCK, new Identifier(mod.metadata().id(), MoreBuildingIds.SMALL_STONE_BRICKS_ID), MBBlocks.SMALL_STONE_BRICKS);
        Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), MoreBuildingIds.BIG_BRICKS_ID), new BlockItem(MBBlocks.BIG_BRICKS, new QuiltItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), MoreBuildingIds.SMALL_STONE_BRICKS_ID), new BlockItem(MBBlocks.SMALL_STONE_BRICKS, new QuiltItemSettings()));
        Registry.register(Registries.ITEM_GROUP, new Identifier(mod.metadata().id(), MoreBuildingIds.MORE_BUILDING_TAB_ID), MORE_BUILDING_TAB);
        LOGGER.info("Finished initializing!\nWelcome to More Building!");
    }
}
