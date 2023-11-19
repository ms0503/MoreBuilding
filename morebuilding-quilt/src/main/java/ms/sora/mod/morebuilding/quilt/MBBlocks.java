package ms.sora.mod.morebuilding.quilt;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;

/**
 * More Building Blocks
 */
public class MBBlocks {
    /**
     * Big Bricks
     */
    public static final Block BIG_BRICKS = new Block(QuiltBlockSettings.copyOf(Blocks.BRICKS));

    /**
     * Small Stone Bricks
     */
    public static final Block SMALL_STONE_BRICKS = new Block(QuiltBlockSettings.copyOf(Blocks.STONE_BRICKS));
}
