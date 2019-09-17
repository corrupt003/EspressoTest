package com.android.espressotest.answer

import RecyclerViewActionsExtension.Companion.itemAtPosition
import RecyclerViewActionsExtension.Companion.onChildView
import android.com.espressotest.R
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.android.espressotest.RecyclerViewActivity
import com.android.espressotest.RecyclerViewActivity.DataHolder
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecyclerViewAnswerTest {
    @get:Rule
    val mActivityRule = ActivityTestRule(RecyclerViewActivity::class.java)

    @Test
    fun find12thItem_imageIsNotDisplayed() {
        // Item position is 0-based.
        val position = 11

        onView(withId(R.id.recycler_view))
            .perform(scrollToPosition<DataHolder>(position))
            .check(
                itemAtPosition(position)
                    .onChildView(withId(R.id.recyclerview_item_image))
                    .matches(not(isDisplayed()))
            )
    }

    @Test
    fun find12thItem_switchOn_imageIsDisplayed() {
        // Item position is 0-based.
        val position = 11

        onView(withId(R.id.recycler_view))
            .perform(
                actionOnItemAtPosition<DataHolder>(
                    position,
                    onChildView(withId(R.id.recyclerview_item_switch), click())
                )
            )
            .check(
                itemAtPosition(position)
                    .onChildView(withId(R.id.recyclerview_item_image))
                    .matches(isDisplayed())
            )
    }
}