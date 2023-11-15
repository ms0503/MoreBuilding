package ms.sora.mod.morebuilding;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoreBuildingQuilt implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("More Building");

    public static final Block BIG_BRICKS = new Block(QuiltBlockSettings.copyOf(Blocks.BRICKS));
    public static final String BIG_BRICKS_ID = "big_bricks";

    public static final ItemGroup MORE_BUILDING_TAB = FabricItemGroup.builder().icon(() -> new ItemStack(BIG_BRICKS.asItem())).name(Text.translatable("itemGroup.morebuilding.tab")).entries((ctx, ents) -> ents.addItem(BIG_BRICKS)).build();
    public static final String MORE_BUILDING_TAB_ID = "tab";

    @Override
    public void onInitialize(ModContainer mod) {
        LOGGER.info("Start initializing More Building...");
        Registry.register(Registries.BLOCK, new Identifier(mod.metadata().id(), BIG_BRICKS_ID), BIG_BRICKS);
        Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), BIG_BRICKS_ID), new BlockItem(BIG_BRICKS, new QuiltItemSettings()));
        Registry.register(Registries.ITEM_GROUP, new Identifier(mod.metadata().id(), MORE_BUILDING_TAB_ID), MORE_BUILDING_TAB);
        LOGGER.info("Finish initializing!\nWelcome to More Building!");
    }
}
