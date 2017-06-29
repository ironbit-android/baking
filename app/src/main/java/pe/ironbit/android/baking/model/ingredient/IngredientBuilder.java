package pe.ironbit.android.baking.model.ingredient;

/**
 * Builder class for the IngredientData.
 */
public final class IngredientBuilder {
    /**
     * {@link IngredientData#quantity}
     */
    private int quantity;

    /**
     * {@link IngredientData#measure}
     */
    private String measure;

    /**
     * {@link IngredientData#name}
     */
    private String name;

    /**
     * Static factory for one line builder statement.
     *
     * @return IngredientBuilder
     */
    public static IngredientBuilder builder() {
        return new IngredientBuilder();
    }

    /**
     * Unique constructor
     */
    public IngredientBuilder() {
        clear();
    }

    /**
     * Set all fields to initial values.
     *
     * @return IngredientBuilder
     */
    public IngredientBuilder clear() {
        quantity = 0;
        measure = "";
        name = "";

        return this;
    }

    /**
     * Creates a IngredientData object using the factory.
     *
     * @return IngredientData
     */
    public IngredientData build() {
        return IngredientFactory.createIngredientData(quantity, measure, name);
    }

    /**
     * Set the ingredient quantity.
     *
     * @param quantity {@link IngredientData#quantity}
     * @return IngredientBuilder
     */
    public IngredientBuilder setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    /**
     * Set the ingredient unit.
     *
     * @param measure {@link IngredientData#measure}
     * @return IngredientBuilder
     */
    public IngredientBuilder setMeasure(String measure) {
        this.measure = measure;
        return this;
    }

    /**
     * Set the ingredient name.
     *
     * @param name {@link IngredientData#name}
     * @return IngredientBuilder
     */
    public IngredientBuilder setName(String name) {
        this.name = name;
        return this;
    }
}
