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
    }
}