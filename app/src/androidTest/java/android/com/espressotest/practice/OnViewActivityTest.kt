package android.com.espressotest.practice

import android.com.espressotest.R
import android.content.Context
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.android.espressotest.OnViewActivity1
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class OnViewActivityTest {
    /**
     * The @Rule annotation lets you add or redefine the behavior of each test method in a reusable way,
     * using one of the test rule classes that the Android Testing Support Library provides,
     * such as ActivityTestRule or ServiceTestRule.
     *
     * The rule here uses an ActivityTestRule object,
     * which provides functional testing of a single Activity—in this case, OnViewActivity1.class.
     *
     * During the duration of the test you will be able to manipulate your Activity directly,
     * using ViewMatchers, ViewActions, and ViewAssertions.
     */
    @get:Rule
    val mActivityRule = ActivityTestRule(OnViewActivity1::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = androidx.test.core.app.ApplicationProvider.getApplicationContext<Context>()
        assertEquals("android.com.espressotest", appContext.packageName)
    }

    @Test
    fun activityLaunch_launchOnViewActivity1_then_launchOnViewActivity2() {
        // Step 1: Find button in OnViewActivity1 and click it.
        // Step 2: Find a TextView with id `R.id.text_header` in OnViewActivity2, and check it is displayed.
        // Step 3: Find button in OnViewActivity2 and click it.
        // Step 4: Find a TextView with id `R.id.text_header_reply` in OnViewActivity1, and check it is displayed.
        onView(withId(R.id.button_send))
            .perform(click())
        onView(withId(R.id.text_header)).check(matches(isDisplayed()))
        onView(withId(R.id.button_reply))
            .perform(click())
        onView(withId(R.id.text_header_reply)).check(matches(isDisplayed()))
    }

    @Test
    fun textInputOutput_send_text_from_Activity1_to_Activity2() {
        // Step 1: Define the desired input string.
        // Step 2: In Activity1, find the EditText, and type the desired string.
        // Step 3: In Activity1, find the button, and click it.
        // Step 4: In Activity2, find the TextView with id `text_message`,
        //         and check it's text is the desired input string or not.
        onView(withId(R.id.editText_activity1)).perform(typeText("hey apple"))
        onView(withId(R.id.button_send))
            .perform(click())
        // go to Activity2
        onView(withId(R.id.text_message)).check(matches(withText("hey apple")))
        onView(withId(R.id.editText_second)).perform(typeText("hi orange"))
        onView(withId(R.id.button_reply))
            .perform(click())
        // go back to Activity1
        onView(withId(R.id.text_message_reply)).check(matches(withText("hi orange")))
    }

    @Test
    fun scrollView_findButton_click2Times_checkTextView() {
        // Step 1: Find the Button with id `scrollview_button` in ScrollView.
        // Step 2: Scroll to that view, and click two times.
        // Step 3: Find the TextView with id `scrollview_button_text`.
        // Step 4: Check that TextView display the text `Click 2 times` or not.
        onView(withId(R.id.scrollview_button))
            .perform(scrollTo(), click(), click())
        onView(withId(R.id.scrollview_button_text))
            .check(matches(withText("Click 2 times")))
        // Bones: What if we use `NestedScrollView` instead of `ScrollView` in the layout file?
    }

    @Test
    fun scrollView_findSwitch_click_checkSwitch_checkTextView() {
        // Step 1: Find the Switch with id `scrollview_switch` in ScrollView.
        // Step 2: Scroll to that view, and click it to switch on.
        // Step 3: Check the Switch is set checked or not (isChecked()).
        // Step 4: Find the TextView with id `scrollview_switch_text` in ScrollView.
        // Step 5: Check the view display the text `Switch ON` or not.
        onView(withId(R.id.scrollview_switch))
            .perform(scrollTo(), click())
            .check(matches(isChecked()))
        onView(withId(R.id.scrollview_switch_text))
            .perform(scrollTo())
            .check(matches(withText("Switch ON")))
        // Bones: What if we use `NestedScrollView` instead of `ScrollView` in the layout file?
    }
}
