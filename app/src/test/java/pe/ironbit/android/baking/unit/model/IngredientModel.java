package pe.ironbit.android.baking.unit.model;

import org.junit.Test;

import pe.ironbit.android.baking.model.ingredient.IngredientBuilder;
import pe.ironbit.android.baking.model.ingredient.IngredientData;
import pe.ironbit.android.baking.model.ingredient.IngredientFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class IngredientModel {
    private static final int QUANTITY = 12;

    private static final String MEASURE = "Units";

    private static final String NAME = "EGGS";

    @Test
    public void ingredientFactory_Functionality() {
        IngredientData data = IngredientFactory.createIngredientData(QUANTITY, MEASURE, NAME);

        assertThat(data.getQuantity(), is(QUANTITY));
        assertThat(data.getMeasure(), is(MEASURE));
        assertThat(data.getName(), is(NAME));
    }

    @Test
    public void ingredientsBuilder_InitialValues() {
        IngredientData data = IngredientBuilder.builder().build();

        assertThat(data.getQuantity(), is(0));
        assertThat(data.getMeasure(), is(""));
        assertThat(data.getName(), is(""));
    }

    @Test
    public void ingredientsBuilder_UsingConstructor() {
        IngredientBuilder builder = new IngredientBuilder();

        builder.setQuantity(QUANTITY)
               .setMeasure(MEASURE)
               .setName(NAME);

        IngredientData data = builder.build();

        assertThat(data.getQuantity(), is(QUANTITY));
        assertThat(data.getMeasure(), is(MEASURE));
        assertThat(data.getName(), is(NAME));
    }

    @Test
    public void ingredientsBuilder_UsingStaticFunction() {
        IngredientData data = IngredientBuilder.builder()
                .setQuantity(QUANTITY)
                .setMeasure(MEASURE)
                .setName(NAME)
                .build();

        assertThat(data.getQuantity(), is(QUANTITY));
        assertThat(data.getMeasure(), is(MEASURE));
        assertThat(data.getName(), is(NAME));
    }
}
