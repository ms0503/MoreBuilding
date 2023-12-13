package ms.sora.mod.morebuilding.quilt.datagen;

import ms.sora.mod.morebuilding.common.MBBlockEntities;
import ms.sora.mod.morebuilding.common.MBBlocks;
import ms.sora.mod.morebuilding.common.block.BackpackBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.DynamicEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.CopyNameLootFunction;
import net.minecraft.loot.function.CopyNbtLootFunction;
import net.minecraft.loot.function.SetContentsLootFunction;
import net.minecraft.loot.provider.nbt.ContextLootNbtProvider;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;

@SuppressWarnings("unused")
final class MBBlockLootTables extends FabricBlockLootTableProvider {
    MBBlockLootTables(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate() {
        this.add(MBBlocks.BACKPACK.get(), block ->
            LootTable.builder().pool(
                this.applySurvivesExplosionCondition(block, LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1.0f))
                    .with(ItemEntry.builder(block)
                        .apply(CopyNameLootFunction.builder(CopyNameLootFunction.Source.BLOCK_ENTITY))
                        .apply(CopyNbtLootFunction.builder(ContextLootNbtProvider.BLOCK_ENTITY)
                            .withOperation("Lock", "BlockEntityTag.Lock")
                            .withOperation("LootTable", "BlockEntityTag.LootTable")
                            .withOperation("LootTableSeed", "BlockEntityTag.LootTableSeed")
                        ).apply(SetContentsLootFunction.builder(MBBlockEntities.BACKPACK.get())
                            .withEntry(DynamicEntry.builder(BackpackBlock.CONTENTS_DYNAMIC_DROP_ID))
                        )
                    )
                )
            )
        );
        this.addDrop(MBBlocks.COARSE_BRICKS.get());
        this.addDrop(MBBlocks.DIAMOND_BRICKS.get());
        this.addDrop(MBBlocks.EMERALD_BRICKS.get());
        this.addDrop(MBBlocks.GOLD_BRICKS.get());
        this.addDrop(MBBlocks.IRON_BRICKS.get());
        this.addDrop(MBBlocks.SAND_BRICKS.get());
        this.addDrop(MBBlocks.FINE_SAND_BRICKS.get());
        this.addDrop(MBBlocks.FINE_STONE_BRICKS.get());
    }
}
