package pe.ironbit.android.baking.data.v1;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pe.ironbit.android.baking.data.base.DataParser;
import pe.ironbit.android.baking.model.ingredient.IngredientBuilder;
import pe.ironbit.android.baking.model.ingredient.IngredientData;
import pe.ironbit.android.baking.model.recipe.RecipeBuilder;
import pe.ironbit.android.baking.model.recipe.RecipeData;
import pe.ironbit.android.baking.model.step.StepBuilder;
import pe.ironbit.android.baking.model.step.StepData;

/**
 * Parse JSON information according to:
 * https://go.udacity.com/android-baking-app-json
 */
public class DataParserV1 implements DataParser {
    @Override
    public List parse(String input) throws JSONException {
        if (input == null || TextUtils.isEmpty(input)) {
            return null;
        }

        RecipeBuilder recipeBuilder = new RecipeBuilder();

        List<RecipeData> recipes = new ArrayList<>();
        List<StepData> steps = new ArrayList<>();
        List<IngredientData> ingredients = new ArrayList<>();

        JSONArray document = new JSONArray(input);
        for (int index = 0; index < document.length(); ++index) {
            recipeBuilder.clear();

            JSONObject recipeTag = document.getJSONObject(index);

            // read recipe data
            recipeBuilder.setId(recipeTag.getInt("id"))
                         .setName(recipeTag.getString("name"))
                         .setServings(recipeTag.getInt("servings"))
                         .setImage(recipeTag.getString("image"));

            // read step list
            {
                JSONArray array = recipeTag.getJSONArray("steps");
                addSteps(array, steps);
                recipeBuilder.setSteps(steps);
            }

            // read ingredient list
            {
                JSONArray array = recipeTag.getJSONArray("ingredients");
                addIngredients(array, ingredients);
                recipeBuilder.setIngredients(ingredients);
            }

            recipes.add(recipeBuilder.build());
        }

        return recipes;
    }

    private void addSteps(JSONArray array, List steps) throws JSONException {
        steps.clear();
        StepBuilder stepBuilder = new StepBuilder();
        for (int index = 0; index < array.length(); ++index) {
            stepBuilder.clear();

            JSONObject object = array.getJSONObject(index);
            stepBuilder.setId(object.getInt("id"))
                       .setDescription(object.getString("description"))
                       .setShortDescription(object.getString("shortDescription"))
                       .setVideoURL(object.getString("videoURL"))
                       .setThumbnailURL(object.getString("thumbnailURL"));

            steps.add(stepBuilder.build());
        }
    }

    private void addIngredients(JSONArray array, List ingredients) throws JSONException {
        ingredients.clear();
        IngredientBuilder ingredientBuilder = new IngredientBuilder();
        for (int index = 0; index < array.length(); ++index) {
            ingredientBuilder.clear();

            JSONObject object = array.getJSONObject(index);
            ingredientBuilder.setQuantity(object.getInt("quantity"))
                             .setMeasure(object.getString("measure"))
                             .setName(object.getString("ingredient"));

            ingredients.add(ingredientBuilder.build());
        }
    }
}
