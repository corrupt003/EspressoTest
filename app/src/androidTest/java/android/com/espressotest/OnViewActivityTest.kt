package android.com.espressotest

import android.content.Context
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
        // Step 4: Find a TextView with id `R.id.text_header_replay` in OnViewActivity1, and check it is displayed.
    }
}
