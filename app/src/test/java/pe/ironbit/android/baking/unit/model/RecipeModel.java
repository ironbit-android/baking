package pe.ironbit.android.baking.unit.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import pe.ironbit.android.baking.model.ingredient.IngredientData;
import pe.ironbit.android.baking.model.recipe.RecipeBuilder;
import pe.ironbit.android.baking.model.recipe.RecipeData;
import pe.ironbit.android.baking.model.recipe.RecipeFactory;
import pe.ironbit.android.baking.model.step.StepData;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class RecipeModel {
    private static final int ID = 1234;

    private static final String NAME = "Sample Recipe";

    private static final int SERVINGS = 15;

    private static final String IMAGE = "some image url";

    private static final List<StepData> STEPS = new ArrayList<StepData>() {
        {
            add(new StepData(1, "description 1", "short description 1", "video url 1", "image url 1"));
            add(new StepData(2, "description 2", "short description 2", "video url 2", "image url 2"));
        }
    };

    private static final List<IngredientData> INGREDIENTS = new ArrayList<IngredientData>() {
        {
            add(new IngredientData(10, "unit 1", "name 1"));
            add(new IngredientData(20, "unit 2", "name 2"));
            add(new IngredientData(30, "unit 3", "name 3"));
        }
    };

    @Test
    public void recipeFactory_Functionality() {
        RecipeData data = RecipeFactory.createRecipeData(ID, NAME, SERVINGS, IMAGE, STEPS, INGREDIENTS);

        assertThat(data.getId(), is(ID));
        assertThat(data.getName(), is(NAME));
        assertThat(data.getServings(), is(SERVINGS));
        assertThat(data.getImage(), is(IMAGE));
        assertEquals(data.getSteps(), STEPS);
        assertEquals(data.getIngredients(), INGREDIENTS);
    }

    @Test
    public void recipeBuilder_InitialValues() {
        RecipeData data = RecipeBuilder.builder().build();

        assertThat(data.getId(), is(0));
        assertThat(data.getName(), is(""));
        assertThat(data.getServings(), is(0));
        assertThat(data.getImage(), is(""));
        assertThat(data.getSteps().size(), is(0));
        assertThat(data.getIngredients().size(), is(0));
    }

    @Test
    public void recipeBuilder_UsingConstructor() {
        RecipeBuilder builder = new RecipeBuilder();

        builder.setId(ID)
               .setName(NAME)
               .setServings(SERVINGS)
               .setImage(IMAGE)
               .setSteps(STEPS)
               .setIngredients(INGREDIENTS);

        RecipeData data = builder.build();

        assertThat(data.getId(), is(ID));
        assertThat(data.getName(), is(NAME));
        assertThat(data.getServings(), is(SERVINGS));
        assertThat(data.getImage(), is(IMAGE));
        assertEquals(data.getSteps(), STEPS);
        assertEquals(data.getIngredients(), INGREDIENTS);
    }

    @Test
    public void recipeBuilder_UsingStaticFunction() {
        RecipeData data = RecipeBuilder.builder()
                .setId(ID)
                .setName(NAME)
                .setServings(SERVINGS)
                .setImage(IMAGE)
                .setSteps(STEPS)
                .setIngredients(INGREDIENTS)
                .build();

        assertThat(data.getId(), is(ID));
        assertThat(data.getName(), is(NAME));
        assertThat(data.getServings(), is(SERVINGS));
        assertThat(data.getImage(), is(IMAGE));
        assertEquals(data.getSteps(), STEPS);
        assertEquals(data.getIngredients(), INGREDIENTS);
    }

    @Test
    public void recipeBuilder_verifyStep() {
        List<StepData> newSteps = new ArrayList<StepData>() {
            {
                add(new StepData(3, "description 3", "short description 3", "video url 3", "image url 3"));
                add(new StepData(4, "description 4", "short description 4", "video url 4", "image url 4"));
            }
        };

        List<StepData> completeSteps = new ArrayList<>(STEPS);
        completeSteps.addAll(newSteps);

        RecipeBuilder builder = new RecipeBuilder();

        builder.setId(ID)
                .setName(NAME)
                .setServings(SERVINGS)
                .setImage(IMAGE)
                .setIngredients(INGREDIENTS);

        builder.setSteps(STEPS);
        builder.addStep(newSteps.get(0));
        builder.addStep(newSteps.get(1));

        RecipeData data = builder.build();

        assertThat(data.getId(), is(ID));
        assertThat(data.getName(), is(NAME));
        assertThat(data.getServings(), is(SERVINGS));
        assertThat(data.getImage(), is(IMAGE));
        assertEquals(data.getSteps(), completeSteps);
        assertEquals(data.getIngredients(), INGREDIENTS);
    }

    @Test
    public void recipeBuilder_verifyIngredients() {
        List<IngredientData> newIngredients = new ArrayList<IngredientData>() {
            {
                add(new IngredientData(40, "unit 4", "name 4"));
                add(new IngredientData(50, "unit 5", "name 5"));
            }
        };

        List<IngredientData> completeIngredients = new ArrayList<>(INGREDIENTS);
        completeIngredients.addAll(newIngredients);

        RecipeBuilder builder = new RecipeBuilder();

        builder.setId(ID)
                .setName(NAME)
                .setServings(SERVINGS)
                .setImage(IMAGE)
                .setSteps(STEPS);

        builder.setIngredients(INGREDIENTS);
        builder.addIngredient(newIngredients.get(0));
        builder.addIngredient(newIngredients.get(1));

        RecipeData data = builder.build();

        assertThat(data.getId(), is(ID));
        assertThat(data.getName(), is(NAME));
        assertThat(data.getServings(), is(SERVINGS));
        assertThat(data.getImage(), is(IMAGE));
        assertEquals(data.getSteps(), STEPS);
        assertEquals(data.getIngredients(), completeIngredients);
    }
}
