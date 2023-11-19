package ms.sora.mod.morebuilding.forge;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

/**
 * More Building Blocks
 */
public class MBBlocks {
    /**
     * Big Bricks
     */
    public static final Block BIG_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.BRICKS));

    /**
     * Small Stone Bricks
     */
    public static final Block SMALL_STONE_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS));
}
