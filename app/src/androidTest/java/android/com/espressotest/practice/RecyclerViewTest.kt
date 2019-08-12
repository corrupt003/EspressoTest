package android.com.espressotest.practice

import RecyclerViewActionsExtension.Companion.onChildView
import android.com.espressotest.R
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.android.espressotest.RecyclerViewActivity
import com.android.espressotest.RecyclerViewActivity.DataHolder
import org.hamcrest.CoreMatchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
class RecyclerViewTest {
    @get:Rule
    val mActivityRule = ActivityTestRule(RecyclerViewActivity::class.java)

    @Test
    fun find12thItem_imageIsNotDisplayed() {
        // Bonus, original Recycler related functions can not do this.
        // Tips: use `RecyclerViewActionExtension`.
        onView(withId(R.id.recycler_view))
            .perform(scrollToPosition<DataHolder>(11))
            .check(
                RecyclerViewActionsExtension.itemAtPosition(11)
                    .onChildView(withId(R.id.recyclerview_item_image))
                    .matches(CoreMatchers.not(ViewMatchers.isDisplayed()))
            )
        sleep(3000)

    }

    @Test
    fun find12thItem_switchOn_imageIsDisplayed() {
        // Bonus, original Recycler related functions can not do this.
        // Tips: use `RecyclerViewActionExtension`.
        onView(withId(R.id.recycler_view))
            .perform(
                actionOnItemAtPosition<DataHolder>(11,
                onChildView(withId(R.id.recyclerview_item_switch), click()))
            )
            .check(
                RecyclerViewActionsExtension.itemAtPosition(11)
                    .onChildView(withId(R.id.recyclerview_item_image))
                    .matches(ViewMatchers.isDisplayed())
            )

        sleep(3000)

    }
}