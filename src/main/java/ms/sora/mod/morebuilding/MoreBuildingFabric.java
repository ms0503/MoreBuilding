package ms.sora.mod.morebuilding;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoreBuildingFabric implements ModInitializer {
    public static final String MOD_ID = "morebuilding";

    public static final Logger LOGGER = LoggerFactory.getLogger("More Building");

    public static final Block BIG_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.BRICKS));
    public static final String BIG_BRICKS_ID = "big_bricks";

    public static final ItemGroup MORE_BUILDING_TAB = FabricItemGroup.builder().icon(() -> new ItemStack(BIG_BRICKS.asItem())).name(Text.translatable("itemGroup.morebuilding.tab")).entries((ctx, ents) -> ents.addItem(BIG_BRICKS)).build();
    public static final String MORE_BUILDING_TAB_ID = "tab";

    @Override
    public void onInitialize() {
        LOGGER.info("Start initializing More Building...");
        Registry.register(Registries.BLOCK, new Identifier(MOD_ID, BIG_BRICKS_ID), BIG_BRICKS);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, BIG_BRICKS_ID), new BlockItem(BIG_BRICKS, new FabricItemSettings()));
        Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, MORE_BUILDING_TAB_ID), MORE_BUILDING_TAB);
        LOGGER.info("Finish initializing!\nWelcome to More Building!");
    }
}
