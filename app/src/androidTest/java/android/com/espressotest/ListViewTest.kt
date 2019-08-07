package android.com.espressotest

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.android.espressotest.OnViewActivity1
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListViewTest {
    @get:Rule
    val mActivityRule = ActivityTestRule(OnViewActivity1::class.java)

    @Test
    fun launchActivity_fromOnViewActivity1_toListViewActivity_checkTitle() {
        // Step 1: Open `ActionBar` menu.
        // Step 2: Click the first item, a.k.a `ListView` item, and may jump to ListViewActivity.
        // Step 3: Check the `ActionBar` title with text `ListView Page` in ListViewActivity.
    }
}