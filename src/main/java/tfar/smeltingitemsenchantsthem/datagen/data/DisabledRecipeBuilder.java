package tfar.smeltingitemsenchantsthem.datagen.data;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.criterion.ImpossibleTrigger;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tfar.smeltingitemsenchantsthem.crafting.DisabledRecipe;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class DisabledRecipeBuilder {

    private static final Logger LOGGER = LogManager.getLogger();
    private final ResourceLocation path;
    private final Advancement.Builder advancementBuilder = Advancement.Builder.builder();

    public DisabledRecipeBuilder(ResourceLocation path) {
        this.path = path;
    }

    /**
     * Creates a new builder for a shapeless recipe.
     */
    public static DisabledRecipeBuilder disableRecipe(IItemProvider resultIn) {
        return disableRecipe(resultIn.asItem().getRegistryName());
    }

    /**
     * Creates a new builder for a shapeless recipe.
     */
    public static DisabledRecipeBuilder disableRecipe(String path) {
        return disableRecipe(new ResourceLocation(path));
    }

    /**
     * Creates a new builder for a shapeless recipe.
     */
    public static DisabledRecipeBuilder disableRecipe(ResourceLocation path) {
        return new DisabledRecipeBuilder(path);
    }

    /**
     * Builds this recipe into an {@link IFinishedRecipe}.
     */
    public void build(Consumer<IFinishedRecipe> consumerIn) {
        build(consumerIn,path);
    }

    /**
     * Builds this recipe into an {@link IFinishedRecipe}.
     */
    public void build(Consumer<IFinishedRecipe> consumerIn, ResourceLocation recipeId) {
        this.advancementBuilder.withCriterion("impossible", new ImpossibleTrigger.Instance());
        consumerIn.accept(new DisabledRecipeBuilder.Result(recipeId,advancementBuilder, new ResourceLocation(recipeId.getNamespace(), "recipes/" + this.path.getPath() + "/" + recipeId.getPath())));
    }

    public static class Result implements IFinishedRecipe {
        private final ResourceLocation id;
        private final Advancement.Builder advancementBuilder;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation idIn, Advancement.Builder advancementBuilderIn, ResourceLocation advancementIdIn) {
            this.id = idIn;
            this.advancementBuilder = advancementBuilderIn;
            this.advancementId = advancementIdIn;
        }

        public void serialize(JsonObject json) {
        }

        public IRecipeSerializer<?> getSerializer() {
            return DisabledRecipe.DISABLED;
        }

        /**
         * Gets the ID for the recipe.
         */
        public ResourceLocation getID() {
            return this.id;
        }

        /**
         * Gets the JSON for the advancement that unlocks this recipe. Null if there is no advancement.
         */
        @Nullable
        public JsonObject getAdvancementJson() {
            return this.advancementBuilder.serialize();
        }

        /**
         * Gets the ID for the advancement associated with this recipe. Should not be null if {@link #getAdvancementJson}
         * is non-null.
         */
        @Nullable
        public ResourceLocation getAdvancementID() {
            return this.advancementId;
        }
    }
}
