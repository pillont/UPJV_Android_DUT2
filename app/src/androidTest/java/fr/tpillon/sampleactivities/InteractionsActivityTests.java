package fr.tpillon.sampleactivities;

import android.content.Context;
import android.view.View;

import androidx.annotation.StringRes;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.tpillon.sampleactivities.Views.InteractionsActivity;


@RunWith(AndroidJUnit4.class)
public class InteractionsActivityTests {

    @Rule
    public ActivityScenarioRule<InteractionsActivity> activityRule
            = new ActivityScenarioRule<>(InteractionsActivity.class);
    private View decorView;

    @Before
    public void Init(){
        activityRule.getScenario().onActivity(
                activity -> decorView = activity.getWindow().getDecorView()
        );
    }
    @Test
    public void verifyInitialText() {
        Espresso.onView(
            ViewMatchers.withId(R.id.savedTextView)
        ).check(
            ViewAssertions.matches(
                CoreMatchers.allOf(
                    ViewMatchers.isDisplayed(),
                    ViewMatchers.withText(R.string.empty_text),
                    ViewMatchers.hasTextColor(R.color.invalid_color)
                )
            )
        );
    }

    @Test
    public void verifyDefaultText() {
        Espresso.onView(
            ViewMatchers.withId(R.id.defaultSaveButton)
        ).perform(
            ViewActions.click()
        );

        Espresso.onView(
            ViewMatchers.withId(R.id.savedTextView)
        ).check(
            ViewAssertions.matches(
                CoreMatchers.allOf(
                    ViewMatchers.isDisplayed(),
                    ViewMatchers.withText(R.string.default_text),
                    ViewMatchers.hasTextColor(R.color.neutral_color)
                )
            )
        );
    }


    @Test
    public void verifySpecificText() {
        Espresso.onView(
            ViewMatchers.withId(R.id.editText)
        ).perform(
            ViewActions.typeText("my text is here !"),
            ViewActions.closeSoftKeyboard()
        );

        Espresso.onView(
            ViewMatchers.withId(R.id.submitButton)
        ).perform(
            ViewActions.click()
        );


        Espresso.onView(
            ViewMatchers.withId(R.id.savedTextView)
        ) .check(
            ViewAssertions.matches(
                CoreMatchers.allOf(
                    ViewMatchers.isDisplayed(),
                    ViewMatchers.withText("saved : MyTextIsHere!"),
                    ViewMatchers.hasTextColor(R.color.valid_color)
                )
            )
        );
    }


    @Test
    public void ErrorOnEmptyText() {
        Espresso.onView(
                ViewMatchers.withId(R.id.editText)
        ).perform(
                ViewActions.typeText("   "),
                ViewActions.closeSoftKeyboard()
        );

        Espresso.onView(
                ViewMatchers.withId(R.id.submitButton)
        ).perform(
                ViewActions.click()
        );

        Espresso.onView(
            ViewMatchers.withText(R.string.empty_text_error_message)
        )
        // NOTE : on vérifie que c est un toast
        .inRoot(RootMatchers.withDecorView(CoreMatchers.not(decorView)))
        .check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        );
    }

    private String getResourceString(@StringRes int id) {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        return targetContext.getResources().getString(id);
    }
}