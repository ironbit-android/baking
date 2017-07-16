package pe.ironbit.android.baking.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
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
import pe.ironbit.android.baking.player.VideoPlayer;
import pe.ironbit.android.baking.util.ConfigUtil;

public class RecipeStepFragment extends Fragment {
    private int index = 0;

    private int totalSteps = 0;

    private RecipeData recipeData;

    private VideoPlayer videoPlayer;

    private boolean navigabilityEnabled = true;

    private static final String RECIPE_DATA_KEY = "RECIPE_DATA_KEY";

    private static final String NAVIGABILITY_ENABLED_KEY = "NAVIGABILITY_ENABLED_KEY";

    @BindView(R.id.fragment_recipe_step_player)
    View exoPlayerView;

    @BindView(R.id.fragment_recipe_step_description)
    TextView stepDescription;

    @BindView(R.id.fragment_recipe_step_navigation_init)
    View viewNavigationInit;

    @BindView(R.id.fragment_recipe_step_navigation_previous)
    View viewNavigationPrevious;

    @BindView(R.id.fragment_recipe_step_navigation_current)
    TextView viewNavigationCurrent;

    @BindView(R.id.fragment_recipe_step_navigation_next)
    View viewNavigationNext;

    @BindView(R.id.fragment_recipe_step_navigation_final)
    View viewNavigationFinal;

    public RecipeStepFragment() {
    }

    public static RecipeStepFragment newInstance(RecipeParcelable recipeParcelable, boolean navigabilityEnabled) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(RECIPE_DATA_KEY, recipeParcelable);
        bundle.putBoolean(NAVIGABILITY_ENABLED_KEY, navigabilityEnabled);

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
            navigabilityEnabled = bundle.getBoolean(NAVIGABILITY_ENABLED_KEY);
            totalSteps = recipeData.getSteps().size();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_step, container, false);

        ButterKnife.bind(this, view);

        viewNavigationInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecipeStepFragment.this.onClickNavigationInit();
            }
        });

        viewNavigationPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecipeStepFragment.this.onClickNavigationPrevious();
            }
        });

        viewNavigationNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecipeStepFragment.this.onClickNavigationNext();
            }
        });

        viewNavigationFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecipeStepFragment.this.onClickNavigationFinal();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        videoPlayer.initialize();
    }

    @Override
    public void onPause() {
        super.onPause();
        videoPlayer.release();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (navigabilityEnabled == false) {
            ButterKnife.findById(getView(), R.id.fragment_recipe_step_navigation_view).setVisibility(View.GONE);
        }

        if (!ConfigUtil.isDeviceTablet(getContext()) && !ConfigUtil.isOrientationPortrait(getContext())) {
            ((RecipeActivity) getActivity()).getSupportActionBar().hide();
        }

        index = ((RecipeActivity)getActivity()).getRecipeStepIndex();

        updateView();
    }

    private void updateView() {
        formatDescription();
        updateMediaSource();
        executeLogic();
    }

    public void updateRecipeStepIndex(int index) {
        if (this.index == index) {
            return;
        }
        this.index = index;

        updateView();
    }

    private void updateMediaSource() {
        if (videoPlayer != null) {
            videoPlayer.release();
            videoPlayer = null;
        }

        {
            String source = recipeData.getSteps().get(index).getVideoURL();
            if (!TextUtils.isEmpty(source)) {
                exoPlayerView.setVisibility(View.VISIBLE);

                videoPlayer = new VideoPlayer(getContext(), Uri.parse(source), exoPlayerView);
                return;
            }
        }
        {
            String source = recipeData.getSteps().get(index).getThumbnailURL();
            if (!TextUtils.isEmpty(source)) {
                exoPlayerView.setVisibility(View.VISIBLE);

                videoPlayer = new VideoPlayer(getContext(), Uri.parse(source), exoPlayerView);
                return;
            }
        }

        exoPlayerView.setVisibility(View.GONE);
    }

    private void formatDescription() {
        View view = getView();
        if (view != null) {
            String description = recipeData.getSteps().get(index).getDescription();
            description = description.replaceFirst("^[0-9]+[. ]+", "");
            stepDescription.setText(description);
        }
    }

    private void executeLogic() {
        viewNavigationCurrent.setText("" + (index + 1) + "/" + totalSteps);

        if (index == 0) {
            viewNavigationInit.setEnabled(false);
            viewNavigationPrevious.setEnabled(false);
            viewNavigationNext.setEnabled(true);
            viewNavigationFinal.setEnabled(true);
        } else if (index == (totalSteps - 1)) {
            viewNavigationInit.setEnabled(true);
            viewNavigationPrevious.setEnabled(true);
            viewNavigationNext.setEnabled(false);
            viewNavigationFinal.setEnabled(false);
        } else {
            viewNavigationInit.setEnabled(true);
            viewNavigationPrevious.setEnabled(true);
            viewNavigationNext.setEnabled(true);
            viewNavigationFinal.setEnabled(true);
        }
    }

    private void onClickNavigationInit() {
        if (index != 0) {
            index = 0;

            ((RecipeActivity)getActivity()).setIndexRecipeStepFragment(index);
            updateView();
        }
    }

    private void onClickNavigationPrevious() {
        if (index > 0) {
            index--;

            ((RecipeActivity)getActivity()).setIndexRecipeStepFragment(index);
            updateView();
        }
    }

    private void onClickNavigationNext() {
        if (index < (totalSteps - 1)) {
            index++;

            ((RecipeActivity)getActivity()).setIndexRecipeStepFragment(index);
            updateView();
        }
    }

    private void onClickNavigationFinal() {
        if (index != (totalSteps - 1)) {
            index = totalSteps - 1;

            ((RecipeActivity)getActivity()).setIndexRecipeStepFragment(index);
            updateView();
        }
    }
}
