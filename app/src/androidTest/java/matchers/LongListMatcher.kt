package matchers

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.View
import androidx.annotation.ColorRes
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher


class LongListMatcher {
    companion object {
        @Suppress("DEPRECATION")
        fun withBackgroundColor(@ColorRes colorRes: Int): Matcher<View> {
            return object : TypeSafeMatcher<View>() {
                override fun describeTo(description: Description?) {
                    description?.appendText("background color: $colorRes")
                }

                override fun matchesSafely(item: View?): Boolean {

                    if (item == null) {
                        return false
                    }

                    val context = item.context
                    if (item.background == null) {
                        return false
                    }

                    val backgroundColor = (item.background as ColorDrawable).color
                    val expectedColor: Int?

                    expectedColor = if (Build.VERSION.SDK_INT <= 22) {
                        context?.resources?.getColor(colorRes)
                    } else {
                        context?.getColor(colorRes)
                    }

                    return backgroundColor == expectedColor
                }
            }
        }
    }
}