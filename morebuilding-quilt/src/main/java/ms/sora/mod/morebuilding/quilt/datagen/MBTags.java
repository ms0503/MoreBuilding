package ms.sora.mod.morebuilding.quilt.datagen;

import java.util.concurrent.CompletableFuture;
import ms.sora.mod.morebuilding.common.MBBlocks;
import ms.sora.mod.morebuilding.common.MBInfo;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.HolderLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

/**
 * MBTags is a manager class of tags.
 * <p>
 * This class contains tags.
 *
 * @since 0.1.0
 */
public final class MBTags {
    /**
     * A tag of bricks
     */
    public static final TagKey<Block> BRICKS = TagKey.of(RegistryKeys.BLOCK, new Identifier(MBInfo.MOD_ID, "bricks"));

    private static final TagKey<Block> COMPAT_SANDSTONE_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier("c", "sandstone_blocks"));

    private static final TagKey<Block> COMPAT_UNCOLORED_SANDSTONE_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier("c", "uncolored_sandstone_blocks"));

    private static final TagKey<Block> FORGE_SANDSTONE = TagKey.of(RegistryKeys.BLOCK, new Identifier("forge", "sandstone"));

    private static final TagKey<Block> MINECRAFT_FEATURES_CANNOT_REPLACE = TagKey.of(RegistryKeys.BLOCK, new Identifier("features_cannot_replace"));

    private static final TagKey<Block> MINECRAFT_GUARDED_BY_PIGLINS = TagKey.of(RegistryKeys.BLOCK, new Identifier("guarded_by_piglins"));

    private static final TagKey<Block> MINECRAFT_LAVA_POOL_STONE_CANNOT_REPLACE = TagKey.of(RegistryKeys.BLOCK, new Identifier("lava_pool_stone_cannot_replace"));

    private static final TagKey<Block> MINECRAFT_MINEABLE_AXE = TagKey.of(RegistryKeys.BLOCK, new Identifier("mineable/axe"));

    private static final TagKey<Block> MINECRAFT_MINEABLE_PICKAXE = TagKey.of(RegistryKeys.BLOCK, new Identifier("mineable/pickaxe"));

    private static final TagKey<Block> MINECRAFT_NEEDS_IRON_TOOL = TagKey.of(RegistryKeys.BLOCK, new Identifier("needs_iron_tool"));

    private static final TagKey<Block> MINECRAFT_NEEDS_STONE_TOOL = TagKey.of(RegistryKeys.BLOCK, new Identifier("needs_stone_tool"));

    private static final TagKey<Block> MINECRAFT_PIGLIN_LOVED = TagKey.of(RegistryKeys.BLOCK, new Identifier("piglin_loved"));

    private static final TagKey<Block> MINECRAFT_STONE_BRICKS = TagKey.of(RegistryKeys.BLOCK, new Identifier("stone_bricks"));

    static final class BlockTags extends FabricTagProvider.BlockTagProvider {
        BlockTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
            super(output, completableFuture);
        }

        @Override
        public void configure(HolderLookup.Provider arg) {
            this.getOrCreateTagBuilder(BRICKS)
                .add(Blocks.BRICKS)
                .add(Blocks.STONE_BRICKS)
                .add(MBBlocks.COARSE_BRICKS.get())
                .add(MBBlocks.DIAMOND_BRICKS.get())
                .add(MBBlocks.EMERALD_BRICKS.get())
                .add(MBBlocks.GOLD_BRICKS.get())
                .add(MBBlocks.IRON_BRICKS.get())
                .add(MBBlocks.SAND_BRICKS.get())
                .add(MBBlocks.FINE_SAND_BRICKS.get())
                .add(MBBlocks.FINE_STONE_BRICKS.get());
            this.getOrCreateTagBuilder(COMPAT_SANDSTONE_BLOCKS)
                .add(MBBlocks.SAND_BRICKS.get())
                .add(MBBlocks.FINE_SAND_BRICKS.get());
            this.getOrCreateTagBuilder(COMPAT_UNCOLORED_SANDSTONE_BLOCKS)
                .add(MBBlocks.SAND_BRICKS.get())
                .add(MBBlocks.FINE_SAND_BRICKS.get());
            this.getOrCreateTagBuilder(FORGE_SANDSTONE)
                .add(MBBlocks.SAND_BRICKS.get())
                .add(MBBlocks.FINE_SAND_BRICKS.get());
            this.getOrCreateTagBuilder(MINECRAFT_FEATURES_CANNOT_REPLACE)
                .add(MBBlocks.BACKPACK.get());
            this.getOrCreateTagBuilder(MINECRAFT_GUARDED_BY_PIGLINS)
                .add(MBBlocks.GOLD_BRICKS.get());
            this.getOrCreateTagBuilder(MINECRAFT_LAVA_POOL_STONE_CANNOT_REPLACE)
                .add(MBBlocks.BACKPACK.get());
            this.getOrCreateTagBuilder(MINECRAFT_MINEABLE_AXE)
                .add(MBBlocks.BACKPACK.get());
            this.getOrCreateTagBuilder(MINECRAFT_MINEABLE_PICKAXE)
                .add(MBBlocks.COARSE_BRICKS.get())
                .add(MBBlocks.DIAMOND_BRICKS.get())
                .add(MBBlocks.EMERALD_BRICKS.get())
                .add(MBBlocks.GOLD_BRICKS.get())
                .add(MBBlocks.IRON_BRICKS.get())
                .add(MBBlocks.SAND_BRICKS.get())
                .add(MBBlocks.FINE_SAND_BRICKS.get())
                .add(MBBlocks.FINE_STONE_BRICKS.get());
            this.getOrCreateTagBuilder(MINECRAFT_NEEDS_IRON_TOOL)
                .add(MBBlocks.DIAMOND_BRICKS.get())
                .add(MBBlocks.EMERALD_BRICKS.get())
                .add(MBBlocks.GOLD_BRICKS.get());
            this.getOrCreateTagBuilder(MINECRAFT_NEEDS_STONE_TOOL)
                .add(MBBlocks.IRON_BRICKS.get());
            this.getOrCreateTagBuilder(MINECRAFT_PIGLIN_LOVED)
                .add(MBBlocks.GOLD_BRICKS.get());
            this.getOrCreateTagBuilder(MINECRAFT_STONE_BRICKS)
                .add(MBBlocks.FINE_STONE_BRICKS.get());
        }
    }
}
