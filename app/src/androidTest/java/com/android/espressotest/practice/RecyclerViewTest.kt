package com.android.espressotest.practice

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.android.espressotest.RecyclerViewActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecyclerViewTest {
    @get:Rule
    val mActivityRule = ActivityTestRule(RecyclerViewActivity::class.java)

    @Test
    fun find12thItem_imageIsNotDisplayed() {
        // Bonus, original Recycler related functions can not do this.
        // Tips: use `RecyclerViewActionExtension`.
    }

    @Test
    fun find12thItem_switchOn_imageIsDisplayed() {
        // Bonus, original Recycler related functions can not do this.
        // Tips: use `RecyclerViewActionExtension`.
    }
}