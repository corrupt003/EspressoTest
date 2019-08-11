
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.core.internal.deps.guava.base.Predicate
import androidx.test.espresso.core.internal.deps.guava.collect.Iterables
import androidx.test.espresso.core.internal.deps.guava.collect.Iterators
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast
import androidx.test.espresso.util.TreeIterables
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.any
import org.hamcrest.Matcher

/**
 * An extension class to find item view in [RecyclerView].
 *
 * [Reference article](https://imsardine.github.io/2017/06/23/espresso-interact-with-verify-item-child-views/)
 */
class RecyclerViewActionsExtension {
    companion object {

        fun onChildView(childMatcher: Matcher<View>, viewAction: ViewAction): ViewAction {
            return ChildViewAction(childMatcher, viewAction)
        }

        fun itemAtPosition(position: Int): RecyclerViewItemAssertion {
            return RecyclerViewItemAssertion(position)
        }

        class ChildViewAction constructor(
            private val childMatcher: Matcher<View>,
            private val viewAction: ViewAction
        ) :
            ViewAction {

            override fun getConstraints(): Matcher<View> {
                return allOf(isAssignableFrom(ViewGroup::class.java), isDisplayingAtLeast(90))
            }

            override fun getDescription(): String {
                return String.format(
                    "perform action: %s on child view matching: %s",
                    viewAction.description, childMatcher
                )
            }

            override fun perform(uiController: UiController, view: View) {
                val childView = findView(childMatcher, view)
                viewAction.perform(uiController, childView)
            }
        }

        class RecyclerViewItemAssertion(private val position: Int) : ViewAssertion {
            private var childMatcher: Matcher<View>? = null

            private var viewAssertion = ViewAssertions.matches(any(View::class.java))

            fun onChildView(childMatcher: Matcher<View>): RecyclerViewItemAssertion {
                this.childMatcher = childMatcher
                return this
            }

            fun matches(viewMatcher: Matcher<View>): ViewAssertion {
                this.viewAssertion = ViewAssertions.matches(viewMatcher)
                return this
            }

            override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
                val viewHolder = (view as RecyclerView).findViewHolderForAdapterPosition(position) ?:
                    throw IllegalArgumentException("ViewHolder null!! Please use this in RecyclerView")

                try {
                    val viewAtPosition: View
                    childMatcher?.let {
                        viewAtPosition = findView(it, viewHolder.itemView)
                        viewAssertion.check(viewAtPosition, null)
                        return
                    }

                    viewAtPosition = viewHolder.itemView
                    viewAssertion.check(viewAtPosition, null)
                } catch (e: NoMatchingViewException) {
                    viewAssertion.check(null, e)
                }
            }

        }

        private fun findView(matcher: Matcher<View>, root: View): View {
            val matches = matchedViewIterator(root, matcher)
            if (matches.hasNext()) {
                val match = matches.next()
                return if (matches.hasNext()) {
                    throw AmbiguousViewMatcherException.Builder()
                        .withRootView(root)
                        .withViewMatcher(matcher)
                        .withView1(match)
                        .withView2(matches.next())
                        .withOtherAmbiguousViews(*Iterators.toArray(matches, View::class.java))
                        .build()
                } else {
                    match
                }

            } else {
                throw NoMatchingViewException.Builder()
                    .withViewMatcher(matcher)
                    .withRootView(root)
                    .build()
            }
        }

        private fun matchedViewIterator(root: View, matcher: Matcher<View>): Iterator<View> {
            val predicate = Predicate<View> { view ->
                view != null && matcher.matches(view)
            }

            return Iterables.filter(TreeIterables.breadthFirstViewTraversal(root), predicate).iterator()
        }
    }
}