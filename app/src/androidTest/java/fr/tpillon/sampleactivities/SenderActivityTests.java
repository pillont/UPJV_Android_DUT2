package fr.tpillon.sampleactivities;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.tpillon.sampleactivities.Views.SenderActivity;


@RunWith(AndroidJUnit4.class)
public class SenderActivityTests {

    @Rule
    public ActivityScenarioRule<SenderActivity> activityRule
            = new ActivityScenarioRule<>(SenderActivity.class);

    @Test
    public void emptyValuesTest() {
        Espresso.onView(
            ViewMatchers.withId(R.id.senderButton)
        ).perform(
            ViewActions.click()
        );

        Espresso.onView(
            ViewMatchers.withId(R.id.messageTextView)
        ).check(
            ViewAssertions.matches(
                CoreMatchers.allOf(
                    ViewMatchers.isDisplayed(),
                    ViewMatchers.withText("")
                )
            )
        );

        Espresso.onView(
            ViewMatchers.withId(R.id.numberTextView)
        ).check(
            ViewAssertions.matches(
                CoreMatchers.allOf(
                    ViewMatchers.isDisplayed(),
                    ViewMatchers.withText("0.0")
                )
            )
        );

        Espresso.onView(
            ViewMatchers.withId(R.id.flagTextView)
        ).check(
            ViewAssertions.matches(
                CoreMatchers.allOf(
                    ViewMatchers.isDisplayed(),
                    ViewMatchers.withText("false")
                )
            )
        );
    }


    @Test
    public void completeValuesTest() {
        Espresso.onView(
                ViewMatchers.withId(R.id.messageEditText)
        ).perform(
                ViewActions.typeText("toto")
        );

        Espresso.onView(
                ViewMatchers.withId(R.id.numberEditText)
        ).perform(
                ViewActions.typeText("123")
        );

        Espresso.onView(
                ViewMatchers.withId(R.id.flagSwitch)
        ).perform(
                ViewActions.click()
        );

        Espresso.onView(
                ViewMatchers.withId(R.id.senderButton)
        ).perform(
                ViewActions.click()
        );

        Espresso.onView(
                ViewMatchers.withId(R.id.messageTextView)
        ).check(
                ViewAssertions.matches(
                        CoreMatchers.allOf(
                                ViewMatchers.isDisplayed(),
                                ViewMatchers.withText("toto")
                        )
                )
        );

        Espresso.onView(
                ViewMatchers.withId(R.id.numberTextView)
        ).check(
                ViewAssertions.matches(
                        CoreMatchers.allOf(
                                ViewMatchers.isDisplayed(),
                                ViewMatchers.withText("123.0")
                        )
                )
        );

        Espresso.onView(
                ViewMatchers.withId(R.id.flagTextView)
        ).check(
                ViewAssertions.matches(
                        CoreMatchers.allOf(
                                ViewMatchers.isDisplayed(),
                                ViewMatchers.withText("true")
                        )
                )
        );
    }

}