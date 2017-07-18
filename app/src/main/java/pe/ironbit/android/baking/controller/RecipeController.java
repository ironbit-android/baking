package pe.ironbit.android.baking.controller;

import pe.ironbit.android.baking.R;
import pe.ironbit.android.baking.activity.RecipeActivity;
import pe.ironbit.android.baking.event.base.BaseListener;
import pe.ironbit.android.baking.util.ConfigUtil;

public class RecipeController implements BaseListener {
    private boolean isTablet;

    private RecipeActivity activity;

    public RecipeController(RecipeActivity activity) {
        this.activity = activity;
        isTablet = ConfigUtil.isDeviceTablet(activity.getApplicationContext());
    }

    public void executeInitLogic() {
        if (isTablet) {
            activity.createRecipeDetailFragment(R.id.activity_recipe_framelayout_one);
            activity.createRecipeStepFragment(R.id.activity_recipe_framelayout_two, false);
        } else {
            activity.initPhoneView();
            activity.createRecipeDetailFragment(R.id.activity_recipe_framelayout_one);
            activity.createRecipeStepFragment(R.id.activity_recipe_framelayout_one, true);
            if (activity.getIsRecipeStepFragmentActive()) {
                activity.showFragment(RecipeActivity.FRAGMENT_RECIPE_STEPS_TAG);
                activity.hideFragment(RecipeActivity.FRAGMENT_RECIPE_DETAIL_TAG);
            } else {
                activity.hideFragment(RecipeActivity.FRAGMENT_RECIPE_STEPS_TAG);
                activity.showFragment(RecipeActivity.FRAGMENT_RECIPE_DETAIL_TAG);
            }
        }
    }

    private void executeStepLogic(int index) {
        activity.updateIndexRecipeStepFragment(index);

        if (!isTablet) {
            activity.setIsRecipeStepFragmentActive(true);
            activity.showFragment(RecipeActivity.FRAGMENT_RECIPE_STEPS_TAG);
            activity.hideFragment(RecipeActivity.FRAGMENT_RECIPE_DETAIL_TAG);
        }
    }

    public boolean executeLogicBack() {
        if (activity.getIsRecipeStepFragmentActive()) {
            activity.setIsRecipeStepFragmentActive(false);
            activity.hideFragment(RecipeActivity.FRAGMENT_RECIPE_STEPS_TAG);
            activity.showFragment(RecipeActivity.FRAGMENT_RECIPE_DETAIL_TAG);
            return false;
        }
        activity.destroyAllFragments();
        return true;
    }

    @Override
    public void update(Object object) {
        executeStepLogic((int)object);
    }
}
