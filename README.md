# EspressoTest #

A small project for practicing Android Espresso. Myabe include UI Automator in the future.

## Project Structure ##

There are tow main parts:

### 1. app/src/main ###
It's the main package of the app code, define some [AppCompatActivity](https://developer.android.com/reference/androidx/appcompat/app/AppCompatActivity) and `resources` folder to be shown on the UI.

* OnViewActivity1: The main entry point of this app. It contains some `Views`, including `TextView`, `EditText`, and `Button`. We also put a `ScrollView` in the layout file (activity_onview1.xml). Not only define some views inside the `ScrollView`, we also create views and add them to `ScrollView` dynamically by codes.

* OnViewActivity2: A very simple `Activity`, just want to practice how to Espresso to jump to another `Activity`.

* ListViewActivity: The most important view inside the coressponding layout file (activity_listview.xml) is `ListView`, and we define some data to show using [ListView](https://developer.android.com/reference/android/widget/ListView) and [BaseAdapter](https://developer.android.com/reference/android/widget/BaseAdapter).

* RecyclerViewActivity: Like `ListViewActivity`, but change `ListView` to [RecyclerView](https://developer.android.com/reference/android/support/v7/widget/RecyclerView) , and define custom [Recycler.Adapter](https://developer.android.com/reference/android/support/v7/widget/RecyclerView.Adapter) and [RecyclerView.ViewHolder](https://developer.android.com/reference/android/support/v7/widget/RecyclerView.ViewHolder) to convert data into UI.

### 2. app/src/androidTest ###

A folder we can put our Espresso test class into it. There are test clasees respected to the Activity classes in app/src/main

#### android.com.espressotest.answer ####

A folder to put ready-to-run test clesses

* OnViewActivityAnswerTest: Contains some test cases use [Espresso.onView()](https://developer.android.com/reference/android/support/test/espresso/Espresso.html#onView(org.hamcrest.Matcher%3Candroid.view.View%3E)) to find a specific view.

* ListViewAnswerTest: Contains some test cases use [Espresso.onData()](https://developer.android.com/reference/android/support/test/espresso/Espresso.html#onData(org.hamcrest.Matcher%3C?%20extends%20java.lang.Object%3E)) to find a specific item inside `ListView`.

* RecyclerViewAnswerTest: Contains some test cases to test `RecyclerView`. It's a advanced test class, because original Espresso API can only find one `item` inside RecyclerView, we have nothing to find specific `View` in `item` unless we define some custom [ViewAction](https://developer.android.com/reference/android/support/test/espresso/ViewAction) and [ViewAssertion](https://developer.android.com/reference/android/support/test/espresso/ViewAssertion) for `RecyclerView`. For more information, please check `RecyclerViewActionsExtension` in project.


#### android.com.espressotest.practice ####

A folder to put empty test classes to let you add codes. Each test case has some hints to let you find views, do some actions and check the view's status. If you still have troubles, you can refer to the its corresponding `xxxAnswerTest` in the `answer` folder.