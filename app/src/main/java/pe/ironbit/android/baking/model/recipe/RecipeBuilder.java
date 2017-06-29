package pe.ironbit.android.baking.model.recipe;

import java.util.ArrayList;
import java.util.List;

import pe.ironbit.android.baking.model.ingredient.IngredientData;
import pe.ironbit.android.baking.model.step.StepData;

/**
 * Builder class for RecipeData
 */
public class RecipeBuilder {
    /**
     * The recipe id.
     */
    private int id;

    /**
     * The recipe name.
     */
    private String name;

    /**
     * The quantity of person who can enjoy the recipe.
     */
    private int servings;

    /**
     * The referential image of the recipe.
     */
    private String image;

    /**
     * List of steps in order to obtain the food.
     */
    private List<StepData> steps;

    /**
     * List of ingredients required for the recipe.
     */
    private List<IngredientData> ingredients;

    /**
     * Static factory for one line builder statement.
     *
     * @return RecipeBuilder
     */
    public static RecipeBuilder builder() {
        return new RecipeBuilder();
    }

    /**
     * Unique constructor.
     */
    public RecipeBuilder() {
        clear();
    }

    /**
     * Set all fields to initial values.
     *
     * @return RecipeBuilder
     */
    public RecipeBuilder clear() {
        id = 0;
        name = "";
        servings = 0;
        image = "";
        steps = new ArrayList<>();
        ingredients = new ArrayList<>();

        return this;
    }

    /**
     * Creates a RecipeData object using the factory.
     *
     * @return RecipeData
     */
    public RecipeData build() {
        return RecipeFactory.createRecipeData(id, name, servings, image, steps, ingredients);
    }

    /**
     * Set recipe id.
     *
     * @param id {@link RecipeData#id}
     * @return RecipeBuilder
     */
    public RecipeBuilder setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Set recipe name.
     *
     * @param name {@link RecipeData#name}
     * @return RecipeBuilder
     */
    public RecipeBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Set recipe servings.
     *
     * @param servings {@link RecipeData#servings}
     * @return RecipeBuilder
     */
    public RecipeBuilder setServings(int servings) {
        this.servings = servings;
        return this;
    }

    /**
     * Set recipe image.
     *
     * @param image {@link RecipeData#image}
     * @return RecipeBuilder
     */
    public RecipeBuilder setImage(String image) {
        this.image = image;
        return this;
    }

    /**
     * Add recipe step.
     *
     * @param step
     * @return RecipeBuilder
     */
    public RecipeBuilder addStep(StepData step) {
        steps.add(step);
        return this;
    }

    /**
     * Set recipe steps list.
     *
     * @param steps
     * @return RecipeBuilder
     */
    public RecipeBuilder setSteps(List<StepData> steps) {
        this.steps = steps;
        return this;
    }

    /**
     * Add recipe ingredient.
     *
     * @param ingredient
     * @return RecipeBuilder
     */
    public RecipeBuilder addIngredient(IngredientData ingredient) {
        ingredients.add(ingredient);
        return this;
    }

    /**
     * Set recipe ingredients list.
     *
     * @param ingredients
     * @return RecipeBuilder
     */
    public RecipeBuilder setIngredients(List<IngredientData> ingredients) {
        this.ingredients = ingredients;
        return this;
    }
}
