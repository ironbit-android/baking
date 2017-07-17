package pe.ironbit.android.baking.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import pe.ironbit.android.baking.R;
import pe.ironbit.android.baking.activity.RecipeActivity;
import pe.ironbit.android.baking.controller.RecipeController;
import pe.ironbit.android.baking.model.recipe.RecipeParcelable;
import pe.ironbit.android.baking.view.ingredient.RecipeIngredientAdapter;
import pe.ironbit.android.baking.view.step.RecipeStepAdapter;

public class RecipeDetailFragment extends Fragment {
    private RecipeParcelable recipeParcelable;

    private RecyclerView recyclerViewRecipeSteps;

    private RecyclerView recyclerViewRecipeIngredients;

    private static final String RECIPE_DATA_KEY = "RECIPE_DATA_KEY";

    private static final String RECYCLER_VIEW_RECIPE_STEPS_KEY = "RECYCLER_VIEW_RECIPE_STEPS_KEY";

    private static final String RECYCLER_VIEW_RECIPE_INGREDIENTS_KEY = "RECYCLER_VIEW_RECIPE_INGREDIENTS_KEY";

    public RecipeDetailFragment() {
    }

    public static RecipeDetailFragment newInstance(RecipeParcelable recipeParcelable) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(RECIPE_DATA_KEY, recipeParcelable);

        RecipeDetailFragment fragment = new RecipeDetailFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            recipeParcelable = bundle.getParcelable(RECIPE_DATA_KEY);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Parcelable stepsParcelable = recyclerViewRecipeSteps.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(RECYCLER_VIEW_RECIPE_STEPS_KEY, stepsParcelable);

        Parcelable ingredientsParcelable = recyclerViewRecipeIngredients.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(RECYCLER_VIEW_RECIPE_INGREDIENTS_KEY, ingredientsParcelable);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        initRecyclerViewRecipeIngredients(view);
        initRecyclerViewRecipeSteps(view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            Parcelable stepsParcelable = savedInstanceState.getParcelable(RECYCLER_VIEW_RECIPE_STEPS_KEY);
            recyclerViewRecipeSteps.getLayoutManager().onRestoreInstanceState(stepsParcelable);

            Parcelable ingredientsParcelable = savedInstanceState.getParcelable(RECYCLER_VIEW_RECIPE_INGREDIENTS_KEY);
            recyclerViewRecipeIngredients.getLayoutManager().onRestoreInstanceState(ingredientsParcelable);
        }
    }

    private void initRecyclerViewRecipeSteps(View view) {
        recyclerViewRecipeSteps = ButterKnife.findById(view, R.id.fragment_recipe_detail_steps);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerViewRecipeSteps.setLayoutManager(layoutManager);

        RecipeController controller = ((RecipeActivity)getActivity()).getRecipeController();

        RecipeStepAdapter adapter = new RecipeStepAdapter(controller);
        adapter.setList(recipeParcelable.getRecipeData().getSteps());

        recyclerViewRecipeSteps.setAdapter(adapter);
    }

    private void initRecyclerViewRecipeIngredients(View view) {
        recyclerViewRecipeIngredients = ButterKnife.findById(view, R.id.fragment_recipe_detail_ingredients);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerViewRecipeIngredients.setLayoutManager(layoutManager);

        RecipeIngredientAdapter adapter = new RecipeIngredientAdapter();
        adapter.setList(recipeParcelable.getRecipeData().getIngredients());

        recyclerViewRecipeIngredients.setAdapter(adapter);
    }
}
