package pe.ironbit.android.baking;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import pe.ironbit.android.baking.activity.BakingActivity;
import pe.ironbit.android.baking.util.ConfigUtil;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNot.not;

public class ActivityInstrumentedTest {
    @Rule
    public ActivityTestRule<BakingActivity> activity =
            new ActivityTestRule<BakingActivity>(BakingActivity.class);

    @Test
    public void FrameLayoutsAreActiveForDevices() {
        Espresso.onView(withId(R.id.activity_baking_recyclerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

        if (ConfigUtil.isDeviceTablet(activity.getActivity().getApplicationContext())) {
            Espresso.onView(withId(R.id.activity_recipe_framelayout_one)).check(matches(isDisplayed()));
            Espresso.onView(withId(R.id.activity_recipe_framelayout_two)).check(matches(isDisplayed()));
        } else {
            Espresso.onView(withId(R.id.activity_recipe_framelayout_one)).check(matches(isDisplayed()));
            Espresso.onView(withId(R.id.activity_recipe_framelayout_two)).check(matches(not(isDisplayed())));
        }
    }
}
