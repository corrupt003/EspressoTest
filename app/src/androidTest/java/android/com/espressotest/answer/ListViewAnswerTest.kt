package android.com.espressotest.answer

import android.com.espressotest.R
import android.widget.TextView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import com.android.espressotest.ListViewActivity
import matchers.LongListMatcher.Companion.withBackgroundColor
import matchers.LongListMatcher.Companion.withContentText
import org.hamcrest.CoreMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ListViewAnswerTest {
    @get:Rule
    val mActivityRule = ActivityTestRule(ListViewActivity::class.java)

    @Test
    fun checkTitle() {
        val context = getInstrumentation().targetContext
        // Check the Appbar title is the desired title.
        val title = context.getString(R.string.list_view_activity_title)
        onView(
            allOf(
                instanceOf(TextView::class.java),
                withParent(withResourceName("action_bar"))
            )
        ).check(matches(withText(title)))
    }

    @Test
    fun clickItem10_checkBackground() {
        onData(anything())
                .inAdapterView(withId(R.id.list_view))
                .atPosition(9)
                .perform(click())
                .check(matches(withBackgroundColor(R.color.list_item_click)))
    }

    @Test
    fun findDataWithText_findCheckBox_clickAndCheck() {
        val interaction = onData(
            withContentText("It is as well to know which way the wind blows")
        )

        interaction.onChildView(withId(R.id.listview_item_checkbox))
            .perform(click())
            .check(matches(isChecked()))
    }
}