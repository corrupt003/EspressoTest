package android.com.espressotest.practice

import android.com.espressotest.R
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.android.espressotest.OnViewActivity1
import matchers.LongListMatcher.Companion.withBackgroundColor
import matchers.LongListMatcher.Companion.withContentText
import org.hamcrest.Matchers.anything
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListViewTest {
    @get:Rule
    val mActivityRule = ActivityTestRule(OnViewActivity1::class.java)

    @Test
    fun checkTitle() {
        // Do it yourself :)
    }

    @Test
    fun clickItem10_checkBackground() {
        // Step 1: Use `onData()` to get `DataInteraction`.
        // Step 2: Find ListView with id `R.id.list_view`.
        // Step 3: Scroll to the 10th position (index is 9).
        // Step 4: Click the item.
        // Step 5: Check the background color is `R.color.list_item_click`.
        //         You can write your own custom matcher, or use the pre-define
        //         one `withBackgroundColor()`.
        onData(anything()).inAdapterView(withId(R.id.list_view))
            .atPosition(9)
            .perform(click())
            .check(matches(withBackgroundColor(R.color.list_item_click)))
    }

    @Test
    fun findDataWithText_findCheckBox_clickAndCheck() {
        // Step 1: Use `onData()` with desired text.
        //         Tips: You can use LongListMatcher.withContentText().
        // Step 2: Find CheckBox with its id.
        // Step 3: Click the CheckBox.
        // Step 4: Check it is checked or not.
        onData(withContentText("He who has health has hope"))
            .onChildView(withId(R.id.listview_item_checkbox))
            .perform(click())
            .check(matches(isChecked()))
    }
}