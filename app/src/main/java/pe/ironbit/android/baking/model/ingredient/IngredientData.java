package pe.ironbit.android.baking.model.ingredient;

/**
 * POJO class that contains information about the ingredient.
 */
public class IngredientData {
    /**
     * The quantity of the ingredient.
     */
    private int quantity;

    /**
     * The unit of the ingredient.
     */
    private String measure;

    /**
     * The name of the ingredient.
     */
    private String name;

    /**
     * Unique constructor and set all parameters.
     *
     * @param quantity {@see #quantity}
     * @param measure  {@see #measure}
     * @param name     {@see #name}
     */
    public IngredientData(int quantity, String measure, String name) {
        this.quantity = quantity;
        this.measure = measure;
        this.name = name;
    }

    /**
     * Get ingredient quantity.
     *
     * @return int
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Get ingredient unit.
     *
     * @return String
     */
    public String getMeasure() {
        return measure;
    }

    /**
     * Get ingredient name.
     *
     * @return String
     */
    public String getName() {
        return name;
    }
}
