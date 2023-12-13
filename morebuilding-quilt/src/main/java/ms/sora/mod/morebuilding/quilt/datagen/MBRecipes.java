package ms.sora.mod.morebuilding.quilt.datagen;

import java.util.List;
import java.util.function.Consumer;
import ms.sora.mod.morebuilding.common.MBBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.RecipesProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

final class MBRecipes extends FabricRecipeProvider {
    private Consumer<RecipeJsonProvider> exporter;

    MBRecipes(FabricDataOutput output) {
        super(output);
    }

    private static String hasTag(TagKey<Item> tag) {
        return "has_%s".formatted(tag.id().getPath());
    }

    @Override
    public void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
        this.exporter = exporter;
        generateShapedRecipe();
        generateStoneCuttingRecipe();
    }

    private void generateShapedRecipe() {
        ShapedRecipeJsonFactory.create(RecipeCategory.TOOLS, MBBlocks.BACKPACK.get())
            .pattern("LPL")
            .pattern("LCL")
            .pattern("SPS")
            .ingredient('C', Blocks.CHEST)
            .ingredient('L', Items.LEATHER)
            .ingredient('P', ItemTags.PLANKS)
            .ingredient('S', Items.STRING)
            .criterion(FabricRecipeProvider.hasItem(Blocks.CHEST), FabricRecipeProvider.conditionsFromItem(Blocks.CHEST))
            .criterion(FabricRecipeProvider.hasItem(Items.LEATHER), FabricRecipeProvider.conditionsFromItem(Items.LEATHER))
            .criterion(hasTag(ItemTags.PLANKS), FabricRecipeProvider.conditionsFromItemTag(ItemTags.PLANKS))
            .criterion(FabricRecipeProvider.hasItem(Items.STRING), FabricRecipeProvider.conditionsFromItem(Items.STRING))
            .offerTo(this.exporter);
        offerBricksRecipe(Blocks.BRICKS, MBBlocks.COARSE_BRICKS.get());
        offerBricksRecipe(Blocks.DIAMOND_BLOCK, MBBlocks.DIAMOND_BRICKS.get());
        offerBricksRecipe(Blocks.EMERALD_BLOCK, MBBlocks.EMERALD_BRICKS.get());
        offerBricksRecipe(MBBlocks.SAND_BRICKS.get(), MBBlocks.FINE_SAND_BRICKS.get());
        offerBricksRecipe(Blocks.STONE_BRICKS, MBBlocks.FINE_STONE_BRICKS.get());
        offerBricksRecipe(Blocks.GOLD_BLOCK, MBBlocks.GOLD_BRICKS.get());
        offerBricksRecipe(Blocks.IRON_BLOCK, MBBlocks.IRON_BRICKS.get());
        offerBricksRecipe(Blocks.CUT_SANDSTONE, MBBlocks.SAND_BRICKS.get());
    }

    private void generateStoneCuttingRecipe() {
        List.of(
            Blocks.CHISELED_SANDSTONE,
            Blocks.CUT_SANDSTONE,
            Blocks.SANDSTONE,
            Blocks.SMOOTH_SANDSTONE,
            MBBlocks.FINE_SAND_BRICKS.get()
        ).forEach(block ->
            RecipesProvider.offerStonecuttingRecipe(this.exporter, RecipeCategory.BUILDING_BLOCKS, block, MBBlocks.SAND_BRICKS.get())
        );
        List.of(
            Blocks.CHISELED_SANDSTONE,
            Blocks.CUT_SANDSTONE,
            Blocks.SANDSTONE,
            Blocks.SMOOTH_SANDSTONE,
            MBBlocks.SAND_BRICKS.get()
        ).forEach(block ->
            RecipesProvider.offerStonecuttingRecipe(this.exporter, RecipeCategory.BUILDING_BLOCKS, block, MBBlocks.FINE_SAND_BRICKS.get())
        );
        RecipesProvider.offerStonecuttingRecipe(this.exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.STONE, MBBlocks.FINE_STONE_BRICKS.get());
    }

    private void offerBricksRecipe(ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonFactory.create(RecipeCategory.BUILDING_BLOCKS, output, 4)
            .pattern("##")
            .pattern("##")
            .ingredient('#', input)
            .criterion(FabricRecipeProvider.hasItem(input), FabricRecipeProvider.conditionsFromItem(input))
            .offerTo(this.exporter);
    }
}
