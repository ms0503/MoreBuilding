package ms.sora.mod.morebuilding.fabric;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

/**
 * More Building Blocks
 */
public class MBBlocks {
    /**
     * Big Bricks
     */
    public static final Block BIG_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.BRICKS));

    /**
     * Small Stone Bricks
     */
    public static final Block SMALL_STONE_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS));
}
