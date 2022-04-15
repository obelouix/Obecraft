package fr.obelouix.obecraft.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;

public class Recipes extends RecipeProvider {

    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

   /* @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ModBlocks.FIRSTBLOCK)
                .patternLine("xxx")
                .patternLine("x#x")
                .patternLine("xxx")
                .key('x', Blocks.COBBLESTONE)
                .key('#', Tags.Items.DYES_RED)
                .setGroup("mytutorial")
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                .build(consumer);
    }*/
}