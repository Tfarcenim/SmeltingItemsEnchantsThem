package tfar.smeltingitemsenchantsthem.datagen.data;

import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.registry.Registry;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        replaceRecyclingRecipes(consumer);
        equipmentRecipes(consumer);
    }



    protected void replaceRecyclingRecipes(Consumer<IFinishedRecipe> consumer) {
        DisabledRecipeBuilder.disableRecipe("iron_nugget_from_smelting").build(consumer);
        DisabledRecipeBuilder.disableRecipe("gold_nugget_from_smelting").build(consumer);
    }

    protected void equipmentRecipes(Consumer<IFinishedRecipe> consumer) {
        List<Item> tools = Registry.ITEM.stream().filter(item -> item.getMaxDamage() > 0).collect(Collectors.toList());
        for (Item item : tools) {
            CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(item), item, 0, 200).addCriterion("has_tool", hasItem(item)).build(consumer);
        }
    }
}
