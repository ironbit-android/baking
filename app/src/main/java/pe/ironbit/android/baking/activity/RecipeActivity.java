package pe.ironbit.android.baking.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import pe.ironbit.android.baking.R;
import pe.ironbit.android.baking.contract.BakingContract;
import pe.ironbit.android.baking.fragment.RecipeDetailFragment;
import pe.ironbit.android.baking.model.recipe.RecipeParcelable;
import pe.ironbit.android.baking.util.ConfigUtil;

public class RecipeActivity extends AppCompatActivity {
    private RecipeParcelable recipeParcelable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        loadRecipeParcelable();

        if (ConfigUtil.isDeviceTablet(this)) {
            initTabletRecipes();
        } else {
            if (savedInstanceState != null) {
                initPhoneRecipeSteps();
            } else {
                initPhoneRecipeDetails();
            }
        }
    }

    private void loadRecipeParcelable() {
        recipeParcelable = getIntent().getParcelableExtra(BakingContract.BAKING_TO_RECIPE);
    }

    private void initPhoneRecipeDetails() {
        // disable views
        ButterKnife.findById(this,R.id.activity_recipe_division).setVisibility(View.GONE);
        ButterKnife.findById(this, R.id.activity_recipe_framelayout_two).setVisibility(View.GONE);

        // create fragment
        RecipeDetailFragment fragment = RecipeDetailFragment.newInstance(recipeParcelable);

        // register fragment
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
               .add(R.id.activity_recipe_framelayout_one, fragment)
               .commit();
    }

    private void initPhoneRecipeSteps() {
//        // disable views
//        ButterKnife.findById(this,R.id.activity_recipe_division).setVisibility(View.GONE);
//        ButterKnife.findById(this, R.id.activity_recipe_framelayout_two).setVisibility(View.GONE);
//
//        // create fragment
//        RecipeDetailFragment fragment = RecipeDetailFragment.newInstance(recipeParcelable);
//
//        // register fragment
//        FragmentManager manager = getSupportFragmentManager();
//        manager.beginTransaction()
//                .add(R.id.activity_recipe_framelayout_one, fragment)
//                .commit();
    }

    private void initTabletRecipes() {
    }
}
