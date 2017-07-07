package pe.ironbit.android.baking.contract;

public final class BakingContract {
    /**
     * Unique private constructor.
     */
    private BakingContract() {
    }

    /**
     * Name key for transfer information between intents.
     * Recipe activity to recipe activity.
     * Integer information.
     */
    public static final String BUNDLE_RECIPE_STEP_KEY = "BUNDLE_RECIPE_STEP_KEY";

    /**
     * Name key for transfer information between intents.
     * Baking activity to recipe activity.
     * RecipeData information.
     */
    public static final String BUNDLE_RECIPE_DATA_KEY = "BUNDLE_RECIPE_DATA_KEY";
}
