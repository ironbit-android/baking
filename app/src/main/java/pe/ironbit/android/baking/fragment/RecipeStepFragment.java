package pe.ironbit.android.baking.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.ironbit.android.baking.R;
import pe.ironbit.android.baking.activity.RecipeActivity;
import pe.ironbit.android.baking.model.recipe.RecipeData;
import pe.ironbit.android.baking.model.recipe.RecipeParcelable;

public class RecipeStepFragment extends Fragment {
    private int index;

    private RecipeData recipeData;

    private static final String RECIPE_DATA_KEY = "RECIPE_DATA_KEY";

    @BindView(R.id.fragment_recipe_step_description)
    TextView stepDescription;

    public RecipeStepFragment() {
    }

    public static RecipeStepFragment newInstance(RecipeParcelable recipeParcelable) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(RECIPE_DATA_KEY, recipeParcelable);

        RecipeStepFragment fragment = new RecipeStepFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            recipeData = ((RecipeParcelable)bundle.getParcelable(RECIPE_DATA_KEY)).getRecipeData();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_step, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    public void updateRecipeStepIndex(int index) {
        this.index = index;
        updateView();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        index = ((RecipeActivity)getActivity()).getRecipeStepIndex();
        updateView();
    }

    private void updateView() {
        View view = getView();
        if (view != null) {
            stepDescription.setText(recipeData.getSteps().get(index).getDescription());
        }
    }
}
