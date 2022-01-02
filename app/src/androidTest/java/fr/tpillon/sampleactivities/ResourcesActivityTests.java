package fr.tpillon.sampleactivities;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.tpillon.sampleactivities.Views.ResourcesActivity;

@RunWith(AndroidJUnit4.class)
public class ResourcesActivityTests {
    @Rule
    public ActivityScenarioRule<ResourcesActivity> activityRule
            = new ActivityScenarioRule<>(ResourcesActivity.class);

    @Test
    public void verifyDefaultTextTest() {
        Espresso.onView(
            ViewMatchers.withText(R.string.text_value)
        ).check(
            ViewAssertions.matches(
                CoreMatchers.allOf(
                    ViewMatchers.isDisplayed(),
                    ViewMatchers.hasTextColor(R.color.text_color)
                )
            )
        );
    }
}