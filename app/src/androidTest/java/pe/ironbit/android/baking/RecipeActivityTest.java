package pe.ironbit.android.baking;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.atomic.AtomicBoolean;

import butterknife.ButterKnife;
import pe.ironbit.android.baking.activity.RecipeActivity;
import pe.ironbit.android.baking.contract.BakingContract;
import pe.ironbit.android.baking.model.ingredient.IngredientBuilder;
import pe.ironbit.android.baking.model.ingredient.IngredientData;
import pe.ironbit.android.baking.model.recipe.RecipeBuilder;
import pe.ironbit.android.baking.model.recipe.RecipeData;
import pe.ironbit.android.baking.model.recipe.RecipeParcelable;
import pe.ironbit.android.baking.model.step.StepBuilder;

@RunWith(AndroidJUnit4.class)
public class RecipeActivityTest {

    private static RecipeData recipeData;

    private static IdlingResource idlingResource;

    private class IdlingResourceHelper implements IdlingResource {
        private ResourceCallback callback;

        private AtomicBoolean state = new AtomicBoolean(true);

        @Override
        public String getName() {
            return getClass().getName();
        }

        @Override
        public boolean isIdleNow() {
            return state.get();
        }

        @Override
        public void registerIdleTransitionCallback(ResourceCallback callback) {
            this.callback = callback;
        }

        public void setState(boolean value) {
            state.set(value);
            if (value && callback != null) {
                callback.onTransitionToIdle();
            }
        }
    }

    private class VideoPlayerEventListener implements ExoPlayer.EventListener {
        private IdlingResourceHelper resource;

        public VideoPlayerEventListener(IdlingResource resource) {
            this.resource = (IdlingResourceHelper)resource;
        }

        @Override
        public void onTimelineChanged(Timeline timeline, Object manifest) {
        }

        @Override
        public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
        }

        @Override
        public void onLoadingChanged(boolean isLoading) {
        }

        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            if (playbackState == ExoPlayer.STATE_ENDED) {
                resource.setState(true);
            }
        }

        @Override
        public void onPlayerError(ExoPlaybackException error) {
        }

        @Override
        public void onPositionDiscontinuity() {
        }

        @Override
        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        }
    }

    @Rule
    public ActivityTestRule<RecipeActivity> activity =
            new ActivityTestRule<RecipeActivity>(RecipeActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    recipeData = createRecipeData();
                    RecipeParcelable recipeParcelable = new RecipeParcelable(recipeData);

                    Intent intent = super.getActivityIntent();
                    intent.putExtra(BakingContract.BUNDLE_RECIPE_DATA_KEY, recipeParcelable);
                    
                    return intent;
                }
            };

    @Before
    public void registerIdlingResource() {
        idlingResource = new IdlingResourceHelper();
        Espresso.registerIdlingResources(idlingResource);
    }

    @After
    public void unregisterIdlingResource() {
        if (idlingResource != null) {
            Espresso.unregisterIdlingResources(idlingResource);
        }
    }

    /**
     * Verify RecyclerView data populated.
     * Verify each ViewHolder by order.
     * scrollToPosition makes ViewHolder visible (verifiable)
     */
    @Test
    public void Test_VerifyIngredientList() {
        for (int index = 0; index < recipeData.getIngredients().size(); ++index) {
            IngredientData data = recipeData.getIngredients().get(index);

            Espresso.onView(ViewMatchers.withId(R.id.fragment_recipe_detail_ingredients))
                    .perform(RecyclerViewActions.scrollToPosition(index))
                    .check(ViewAssertions.matches(verifyIngredientDataInRecyclerView(index, data)));
        }
    }

    @Test
    public void Test_VerifyVideoPlayer() {
        // set listener
        ExoPlayer.EventListener listener = new VideoPlayerEventListener(idlingResource);
        ((RecipeActivity)activity.getActivity()).setVideoPlayerEventListener(listener);

        // open fragment with video player
        Espresso.onView(ViewMatchers.withId(R.id.fragment_recipe_detail_steps))
                .perform(RecyclerViewActions.scrollToPosition(0))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

        // start video player
        Espresso.onView(ViewMatchers.withId(R.id.exo_play))
                .perform(ViewActions.click());

        // wait until video finishes
        ((IdlingResourceHelper) idlingResource).setState(false);

        // verify whether the video player finished
        Espresso.onView(ViewMatchers.withId(R.id.exo_panel))
                .check(ViewAssertions.matches(verifyVideoPlayerFinished(R.id.exo_position, R.id.exo_duration)));
    }

    /**
     *
     * @param position
     * @param data
     * @return
     */
    private static Matcher<View> verifyIngredientDataInRecyclerView(final int position, final IngredientData data) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View view) {
                RecyclerView recyclerView = (RecyclerView) view;

                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                return verifyIngredientDataInViewHolder(viewHolder.itemView, position, data);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("error on ingredient data: " + position);
            }
        };
    }

    private static boolean verifyIngredientDataInViewHolder(final View view, int position, final IngredientData data) {
        TextView item = ButterKnife.findById(view, R.id.recyclerview_ingredient_item);
        TextView name = ButterKnife.findById(view, R.id.recyclerview_ingredient_name);
        TextView measure = ButterKnife.findById(view, R.id.recyclerview_ingredient_measure);
        TextView quantity = ButterKnife.findById(view, R.id.recyclerview_ingredient_quantity);

        if (!TextUtils.equals(name.getText(), data.getName())) {
            return false;
        }
        if (!TextUtils.equals(measure.getText(), data.getMeasure())) {
            return false;
        }
        if (Integer.parseInt(item.getText().toString()) != (position + 1)) {
            return false;
        }
        if (Integer.parseInt(quantity.getText().toString()) != data.getQuantity()) {
            return false;
        }
        return true;
    }

    private static Matcher<View> verifyVideoPlayerFinished(final int textViewId1, final int textViewId2) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View view) {
                TextView textView1 = ButterKnife.findById(view, textViewId1);
                TextView textView2 = ButterKnife.findById(view, textViewId2);

                return TextUtils.equals(textView1.getText(), textView2.getText());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("error on verify player video finished");
            }
        };
    }

    private static RecipeData createRecipeData() {
        RecipeBuilder recipeBuilder = new RecipeBuilder();

        recipeBuilder.setId(54)
                     .setName("recipe name")
                     .setServings(17)
                     .setImage("");

        addStep(recipeBuilder, 100, "description 1", "short description 1",
                "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd9cb_4-press-crumbs-in-pie-plate-creampie/4-press-crumbs-in-pie-plate-creampie.mp4",
                "");

        addIngredient(recipeBuilder, "ingredient 1", 1, "measure 1");
        addIngredient(recipeBuilder, "ingredient 2", 2, "measure 2");
        addIngredient(recipeBuilder, "ingredient 3", 3, "measure 3");
        addIngredient(recipeBuilder, "ingredient 4", 4, "measure 4");
        addIngredient(recipeBuilder, "ingredient 5", 5, "measure 5");
        addIngredient(recipeBuilder, "ingredient 6", 6, "measure 6");
        addIngredient(recipeBuilder, "ingredient 7", 7, "measure 7");
        addIngredient(recipeBuilder, "ingredient 8", 8, "measure 8");
        addIngredient(recipeBuilder, "ingredient 9", 9, "measure 9");
        addIngredient(recipeBuilder, "ingredient 10", 10, "measure 10");
        addIngredient(recipeBuilder, "ingredient 11", 11, "measure 11");
        addIngredient(recipeBuilder, "ingredient 12", 12, "measure 12");

        return recipeBuilder.build();
    }

    private static void addStep(RecipeBuilder builder, int id, String description, String shortDescription,
                                String videoUrl, String thumbnailUrl) {
        StepBuilder stepBuilder = new StepBuilder();

        stepBuilder.setId(id)
                   .setDescription(description)
                   .setShortDescription(shortDescription)
                   .setVideoURL(videoUrl)
                   .setThumbnailURL(thumbnailUrl);

        builder.addStep(stepBuilder.build());
    }

    private static void addIngredient(RecipeBuilder builder, String name, int quantity, String measure) {
        IngredientBuilder ingredientBuilder = new IngredientBuilder();

        ingredientBuilder.setName(name)
                         .setQuantity(quantity)
                         .setMeasure(measure);

        builder.addIngredient(ingredientBuilder.build());
    }
}
