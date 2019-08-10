package actions

import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ScrollView
import androidx.core.widget.NestedScrollView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ScrollToAction
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.util.HumanReadables
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.Matcher


class CustomAction {
    companion object {
        /**
         * A better scrollTo() which can be used in [ScrollView], [HorizontalScrollView] and [NestedScrollView].
         */
        fun betterScrollTo(): ViewAction {
            return object : ViewAction {
                override fun getDescription(): String {
                    return "scroll to"
                }

                override fun getConstraints(): Matcher<View> {
                    return allOf(
                            withEffectiveVisibility(Visibility.VISIBLE),
                            isDescendantOfA(
                                    anyOf(
                                        isAssignableFrom(ScrollView::class.java),
                                        isAssignableFrom(HorizontalScrollView::class.java),
                                        isAssignableFrom(NestedScrollView::class.java)
                                    )
                            )
                    )
                }

                override fun perform(uiController: UiController?, view: View?) {
                    try {
                        ScrollToAction().perform(uiController, view)
                    } catch (e: Exception) {
                        throw PerformException.Builder()
                                .withActionDescription(this.description)
                                .withViewDescription(HumanReadables.describe(view))
                                .withCause(e)
                                .build()
                    }
                    uiController?.loopMainThreadUntilIdle()
                }
            }
        }
    }
}