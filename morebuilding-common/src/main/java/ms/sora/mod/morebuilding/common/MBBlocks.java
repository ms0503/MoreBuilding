package ms.sora.mod.morebuilding.common;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import java.util.function.Supplier;
import ms.sora.mod.morebuilding.common.block.BackpackBlock;
import ms.sora.mod.morebuilding.common.blockentity.BackpackBlockEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;

/**
 * MBBlocks is a manager class of blocks.
 * <p>
 * This class contains blocks and registers of them.
 *
 * @since 0.1.0-beta0
 */
@SuppressWarnings("unused")
public final class MBBlocks {
    /**
     * A block register
     */
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MBInfo.MOD_ID, RegistryKeys.BLOCK);

    /**
     * The Backpack
     *
     * @since 0.1.0
     */
    public static final RegistrySupplier<BackpackBlock> BACKPACK = register("backpack", () ->
        new BackpackBlock(AbstractBlock.Settings.copy(Blocks.CHEST)
            .solid()
            .nonOpaque()
            .suffocates((state, world, pos) -> !(world.getBlockEntity(pos) instanceof BackpackBlockEntity))
            .blockVision((state, world, pos) -> !(world.getBlockEntity(pos) instanceof BackpackBlockEntity))
            .pistonBehavior(PistonBehavior.DESTROY)
            .solidBlock((state, world, pos) -> true)
        ), new Item.Settings().maxCount(1)
    );

    /**
     * The Coarse Bricks
     */
    public static final RegistrySupplier<Block> COARSE_BRICKS = register("coarse_bricks", Blocks.BRICKS);

    /**
     * The Diamond Bricks
     *
     * @since 0.1.0-beta1
     */
    public static final RegistrySupplier<Block> DIAMOND_BRICKS = register("diamond_bricks", Blocks.DIAMOND_BLOCK);

    /**
     * The Emerald Bricks
     *
     * @since 0.1.0-beta1
     */
    public static final RegistrySupplier<Block> EMERALD_BRICKS = register("emerald_bricks", Blocks.EMERALD_BLOCK);

    /**
     * The Fine Sand Bricks
     */
    public static final RegistrySupplier<Block> FINE_SAND_BRICKS = register("fine_sand_bricks", Blocks.SANDSTONE);

    /**
     * The Fine Stone Bricks
     */
    public static final RegistrySupplier<Block> FINE_STONE_BRICKS = register("fine_stone_bricks", Blocks.STONE_BRICKS);

    /**
     * The Gold Bricks
     *
     * @since 0.1.0-beta1
     */
    public static final RegistrySupplier<Block> GOLD_BRICKS = register("gold_bricks", Blocks.GOLD_BLOCK);

    /**
     * The Iron Bricks
     *
     * @since 0.1.0-beta1
     */
    public static final RegistrySupplier<Block> IRON_BRICKS = register("iron_bricks", Blocks.IRON_BLOCK);

    /**
     * The Sand Bricks
     */
    public static final RegistrySupplier<Block> SAND_BRICKS = register("sand_bricks", Blocks.SANDSTONE);

    @SuppressWarnings("UnstableApiUsage")
    private static <T extends Block> RegistrySupplier<T> register(String name, Supplier<T> sup, Item.Settings settings) {
        RegistrySupplier<T> block = BLOCKS.register(name, sup);
        MBItems.ITEMS.register(name, () -> new BlockItem(block.get(), settings.arch$tab(MBCore.MORE_BUILDING_TAB)));
        return block;
    }

    private static <T extends Block> RegistrySupplier<T> register(String name, Supplier<T> sup) {
        return register(name, sup, new Item.Settings());
    }

    private static RegistrySupplier<Block> register(String name, Block block) {
        return register(name, () -> new Block(AbstractBlock.Settings.copy(block)));
    }

    private static RegistrySupplier<Block> register(String name) {
        return register(name, () -> new Block(AbstractBlock.Settings.create()));
    }
}
