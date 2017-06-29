package pe.ironbit.android.baking.model.recipe;

import java.util.List;

import pe.ironbit.android.baking.model.ingredient.IngredientData;
import pe.ironbit.android.baking.model.step.StepData;

/**
 * POJO class that contains information about recipes.
 */
public class RecipeData {
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
     * Unique constructor.
     *
     * @param id          {@link #id}
     * @param name        {@link #name}
     * @param servings    {@link #servings}
     * @param image       {@link #image}
     * @param steps       {@link #steps}
     * @param ingredients {@link #ingredients}
     */
    public RecipeData(int id, String name, int servings, String image, List<StepData> steps, List<IngredientData> ingredients) {
        this.id = id;
        this.name = name;
        this.servings = servings;
        this.image = image;
        this.steps = steps;
        this.ingredients = ingredients;
    }

    /**
     * Get recipe id.
     *
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Get recipe name.
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Get recipe servings.
     *
     * @return int
     */
    public int getServings() {
        return servings;
    }

    /**
     * Get referential image.
     *
     * @return String
     */
    public String getImage() {
        return image;
    }

    /**
     * Get list of steps.
     *
     * @return List
     */
    public List<StepData> getSteps() {
        return steps;
    }

    /**
     * Get list of ingredients.
     *
     * @return List
     */
    public List<IngredientData> getIngredients() {
        return ingredients;
    }
}
