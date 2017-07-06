package pe.ironbit.android.baking.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import pe.ironbit.android.baking.R;
import pe.ironbit.android.baking.model.recipe.RecipeData;
import pe.ironbit.android.baking.model.recipe.RecipeParcelable;
import pe.ironbit.android.baking.view.ingredient.RecipeIngredientAdapter;
import pe.ironbit.android.baking.view.step.RecipeStepAdapter;

public class RecipeDetailFragment extends Fragment {
    private RecipeData recipeData;

    private static final String RECIPE_DATA_KEY = RecipeDetailFragment.class.getSimpleName();

    public RecipeDetailFragment() {
    }

    public static RecipeDetailFragment newInstance(RecipeParcelable recipeParcelable) {
        RecipeDetailFragment fragment = new RecipeDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(RECIPE_DATA_KEY, recipeParcelable);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            recipeData = ((RecipeParcelable)getArguments().getParcelable(RECIPE_DATA_KEY)).getRecipeData();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        initRecyclerViewRecipeIngredients(view);
        initRecyclerViewRecipeSteps(view);

        return view;
    }

    private void initRecyclerViewRecipeSteps(View view) {
        RecyclerView recyclerView = ButterKnife.findById(view, R.id.fragment_recipe_detail_steps);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        RecipeStepAdapter adapter = new RecipeStepAdapter(RecipeStepAdapter.Selector.SHORT_DESCRIPTION);
        adapter.update(recipeData.getSteps());

        recyclerView.setAdapter(adapter);
    }

    private void initRecyclerViewRecipeIngredients(View view) {
        RecyclerView recyclerView = ButterKnife.findById(view, R.id.fragment_recipe_detail_ingredients);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        RecipeIngredientAdapter adapter = new RecipeIngredientAdapter();
        adapter.update(recipeData.getIngredients());

        recyclerView.setAdapter(adapter);
    }
}
