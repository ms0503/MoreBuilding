package ms.sora.mod.morebuilding.common;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import java.util.function.Supplier;
import ms.sora.mod.morebuilding.MoreBuildingCore;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;

/**
 * More Building Blocks
 */
@SuppressWarnings("unused")
public class MBBlocks {
    /**
     * Block registry
     */
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MoreBuildingInfo.MOD_ID, RegistryKeys.BLOCK);

    /**
     * Block item registry
     */
    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(MoreBuildingInfo.MOD_ID, RegistryKeys.ITEM);

    /**
     * Big Bricks
     */
    public static final RegistrySupplier<Block> BIG_BRICKS = register("big_bricks", Blocks.BRICKS);

    /**
     * Sand Bricks
     */
    public static final RegistrySupplier<Block> SAND_BRICKS = register("sand_bricks", Blocks.SANDSTONE);

    /**
     * Small Sand Bricks
     */
    public static final RegistrySupplier<Block> SMALL_SAND_BRICKS = register("small_sand_bricks", Blocks.SANDSTONE);

    /**
     * Small Stone Bricks
     */
    public static final RegistrySupplier<Block> SMALL_STONE_BRICKS = register("small_stone_bricks", Blocks.STONE_BRICKS);

    private static RegistrySupplier<Block> register(String name, Supplier<? extends Block> sup, Item.Settings settings) {
        RegistrySupplier<Block> block = BLOCKS.register(name, sup);
        BLOCK_ITEMS.register(name, () -> new BlockItem(block.get(), settings.arch$tab(MoreBuildingCore.MORE_BUILDING_TAB)));
        return block;
    }

    private static RegistrySupplier<Block> register(String name, Supplier<? extends Block> sup) {
        return register(name, sup, new Item.Settings());
    }

    private static RegistrySupplier<Block> register(String name, AbstractBlock.Settings settings) {
        return register(name, () -> new Block(settings));
    }

    private static RegistrySupplier<Block> register(String name, Block block) {
        return register(name, AbstractBlock.Settings.copy(block));
    }
}
