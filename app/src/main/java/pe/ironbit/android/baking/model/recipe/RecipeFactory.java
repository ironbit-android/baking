package pe.ironbit.android.baking.model.recipe;

import java.util.ArrayList;
import java.util.List;

import pe.ironbit.android.baking.model.ingredient.IngredientData;
import pe.ironbit.android.baking.model.step.StepData;

/**
 * Factory for RecipeData.
 */
public class RecipeFactory {
    /**
     * Private unique constructor.
     */
    private RecipeFactory() {
    }

    /**
     * Create RecipeData object from the parameters.
     *
     * @param id          {@link RecipeData#id}
     * @param name        {@link RecipeData#name}
     * @param servings    {@link RecipeData#servings}
     * @param image       {@link RecipeData#image}
     * @param steps       {@link RecipeData#steps}
     * @param ingredients {@link RecipeData#ingredients}
     * @return RecipeData object
     */
    public static RecipeData createRecipeData(int id,
                                              String name,
                                              int servings,
                                              String image,
                                              List<StepData> steps,
                                              List<IngredientData> ingredients) {
        return new RecipeData(id, name, servings, image, new ArrayList<>(steps), new ArrayList<>(ingredients));
    }
}
