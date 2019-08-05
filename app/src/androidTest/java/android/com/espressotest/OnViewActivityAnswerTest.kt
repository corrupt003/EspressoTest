package android.com.espressotest

import android.content.Context
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
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
class OnViewActivityAnswerTest {
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
        // Click R.id.button_main in OnViewActivity1.
        onView(withId(R.id.button_send)).perform(click())

        // R.id.text_header is in OnViewActivity2.
        onView(withId(R.id.text_header)).check(matches(isDisplayed()))

        onView(withId(R.id.button_reply)).perform(click())
        onView(withId(R.id.text_header_reply)).check(matches(isDisplayed()))
    }
}
