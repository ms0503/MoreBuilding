package ms.sora.mod.morebuilding.quilt.datagen;

import ms.sora.mod.morebuilding.common.MBBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.model.BlockStateModelGenerator;

final class MBModels extends FabricModelProvider {
    MBModels(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        generator.registerSimpleCubeAll(MBBlocks.COARSE_BRICKS.get());
        generator.registerSimpleCubeAll(MBBlocks.DIAMOND_BRICKS.get());
        generator.registerSimpleCubeAll(MBBlocks.EMERALD_BRICKS.get());
        generator.registerSimpleCubeAll(MBBlocks.FINE_SAND_BRICKS.get());
        generator.registerSimpleCubeAll(MBBlocks.FINE_STONE_BRICKS.get());
        generator.registerSimpleCubeAll(MBBlocks.GOLD_BRICKS.get());
        generator.registerSimpleCubeAll(MBBlocks.IRON_BRICKS.get());
        generator.registerSimpleCubeAll(MBBlocks.SAND_BRICKS.get());
    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
    }
}
