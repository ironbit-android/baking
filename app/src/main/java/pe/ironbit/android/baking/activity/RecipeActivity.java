package pe.ironbit.android.baking.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import pe.ironbit.android.baking.R;
import pe.ironbit.android.baking.contract.BakingContract;
import pe.ironbit.android.baking.controller.RecipeController;
import pe.ironbit.android.baking.fragment.RecipeDetailFragment;
import pe.ironbit.android.baking.fragment.RecipeStepFragment;
import pe.ironbit.android.baking.model.recipe.RecipeParcelable;

public class RecipeActivity extends AppCompatActivity {
    private RecipeController controller;

    Map<String, Fragment> fragmentMapper;

    private int recipeStepIndex = 0;

    private RecipeParcelable recipeParcelable = null;

    private boolean isRecipeStepFragmentActive = false;

    private static final String RECIPE_INDEX_KEY = "RECIPE_INDEX_KEY";

    private static final String FRAGMENT_ACTIVE_KEY = "FRAGMENT_ACTIVE_KEY";

    private static final String RECIPE_PARCELABLE_KEY = "RECIPE_PARCELABLE_KEY";

    public static final String FRAGMENT_RECIPE_STEPS_TAG = "FRAGMENT_RECIPE_STEPS_TAG";

    public static final String FRAGMENT_RECIPE_DETAIL_TAG = "FRAGMENT_RECIPE_DETAIL_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // create view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        // load saved instances
        loadInstanceState(savedInstanceState);

        // read model
        RecipeParcelable aux = getIntent().getParcelableExtra(BakingContract.BUNDLE_RECIPE_DATA_KEY);
        if (aux != null) {
            recipeParcelable = aux;
        }

        // create mapper
        fragmentMapper = new HashMap<>();

        // create controller
        controller = new RecipeController(this);
        controller.executeInitLogic();
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt(RECIPE_INDEX_KEY, recipeStepIndex);
        bundle.putParcelable(RECIPE_PARCELABLE_KEY, recipeParcelable);
        bundle.putBoolean(FRAGMENT_ACTIVE_KEY, isRecipeStepFragmentActive);
        super.onSaveInstanceState(bundle);
    }

    @Override
    public void onBackPressed() {
        if (controller.executeLogicBack()) {
            super.onBackPressed();
        }
    }

    private void loadInstanceState(Bundle bundle) {
        if (bundle != null) {
            recipeStepIndex = bundle.getInt(RECIPE_INDEX_KEY);
            recipeParcelable = bundle.getParcelable(RECIPE_PARCELABLE_KEY);
            isRecipeStepFragmentActive = bundle.getBoolean(FRAGMENT_ACTIVE_KEY);
        }
    }

    public int getRecipeStepIndex() {
        return recipeStepIndex;
    }

    public RecipeController getRecipeController() {
        return controller;
    }

    public boolean getIsRecipeStepFragmentActive() {
        return isRecipeStepFragmentActive;
    }

    public void setIsRecipeStepFragmentActive(boolean value) {
        isRecipeStepFragmentActive = value;
    }

    public void updateIndexRecipeStepFragment(int index) {
        recipeStepIndex = index;
        RecipeStepFragment fragment = (RecipeStepFragment) fragmentMapper.get(FRAGMENT_RECIPE_STEPS_TAG);
        fragment.updateRecipeStepIndex(index);
    }

    public void initPhoneView() {
        ButterKnife.findById(this,R.id.activity_recipe_division).setVisibility(View.GONE);
        ButterKnife.findById(this, R.id.activity_recipe_framelayout_two).setVisibility(View.GONE);
    }

    public void createRecipeDetailFragment(int resource) {
        // query fragment
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentByTag(FRAGMENT_RECIPE_DETAIL_TAG);
        if (fragment != null) {
            fragmentMapper.put(FRAGMENT_RECIPE_DETAIL_TAG, fragment);
            return;
        }

        // create fragment
        RecipeDetailFragment recipeDetailFragment = RecipeDetailFragment.newInstance(recipeParcelable);

        // register fragment
        manager.beginTransaction()
               .add(resource, recipeDetailFragment, FRAGMENT_RECIPE_DETAIL_TAG)
               .commitNow();

        fragmentMapper.put(FRAGMENT_RECIPE_DETAIL_TAG, recipeDetailFragment);
    }

    public void createRecipeStepFragment(int resource) {
        // query fragment
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentByTag(FRAGMENT_RECIPE_STEPS_TAG);
        if (fragment != null) {
            fragmentMapper.put(FRAGMENT_RECIPE_STEPS_TAG, fragment);
            return;
        }

        // create fragment
        RecipeStepFragment recipeStepFragment = RecipeStepFragment.newInstance(recipeParcelable);

        // register fragment
        manager.beginTransaction()
               .add(resource, recipeStepFragment, FRAGMENT_RECIPE_STEPS_TAG)
               .commitNow();

        fragmentMapper.put(FRAGMENT_RECIPE_STEPS_TAG, recipeStepFragment);
    }

    public void hideFragment(String tag) {
        Fragment fragment = fragmentMapper.get(tag);
        if (fragment != null) {
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .hide(fragment)
                    .commit();
        }
    }

    public void showFragment(String tag) {
        Fragment fragment = fragmentMapper.get(tag);
        if (fragment != null) {
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .show(fragment)
                    .commit();
        }
    }
}
