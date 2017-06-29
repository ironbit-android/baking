package pe.ironbit.android.baking.model.ingredient;

/**
 * Factory for IngredientData.
 */
public final class IngredientFactory {
    /**
     * Private unique constructor.
     */
    private IngredientFactory() {
    }

    /**
     * Create IngredientData object from the parameters.
     *
     * @param quantity {@see IngredientData#quantity}
     * @param measure  {@see IngredientData#measure}
     * @param name     {@see IngredientData#name}
     * @return IngredientData
     */
    public static IngredientData createIngredientData(int quantity, String measure, String name) {
        return new IngredientData(quantity, measure, name);
    }
}
