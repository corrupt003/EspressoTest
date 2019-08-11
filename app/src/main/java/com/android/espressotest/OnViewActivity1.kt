package com.android.espressotest

import android.com.espressotest.R
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet



class OnViewActivity1 : AppCompatActivity() {

    companion object {
        // Class name for Log tag
        private val LOG_TAG = OnViewActivity1::class.java.simpleName
        // Unique tag required for the intent extra
        const val EXTRA_MESSAGE = "com.android.espressotest.extra.MESSAGE"
        // Unique tag for the intent reply
        private const val TEXT_REQUEST = 1
    }

    // EditText view for the message
    private var mMessageEditText: EditText? = null
    // TextView for the reply header
    private var mReplyHeadTextView: TextView? = null
    // TextView for the reply body
    private var mReplyTextView: TextView? = null

    private lateinit var layout: ConstraintLayout
    private lateinit var scrollviewButton: Button
    private lateinit var scrollviewButtonTextView: TextView
    private lateinit var scrollviewSwitch: Switch
    private lateinit var scrollviewSwitchTextView: TextView

    private var clickTime = 0

    /**
     * Initializes the activity.
     *
     * @param savedInstanceState The current state data.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onview1)

        // Initialize all the view variables.
        mMessageEditText = findViewById(R.id.editText_activity1)
        mReplyHeadTextView = findViewById(R.id.text_header_reply)
        mReplyTextView = findViewById(R.id.text_message_reply)

        layout = findViewById(R.id.scrollview_root)
        scrollviewButton = findViewById(R.id.scrollview_button)
        scrollviewButtonTextView = findViewById(R.id.scrollview_button_text)
        scrollviewSwitch = findViewById(R.id.scrollview_switch)
        scrollviewSwitchTextView = findViewById(R.id.scrollview_switch_text)
        scrollviewButtonTextView.text = getString(R.string.click_times, clickTime)

        setListeners()
    }

    override fun onStart() {
        super.onStart()
        addViewsInScrollView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.activity1_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            when(it.itemId) {
                R.id.to_listview -> toListViewActivity()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setListeners() {
        scrollviewButton.setOnClickListener {
            scrollviewButtonTextView.text = getString(R.string.click_times, ++clickTime)
        }

        scrollviewSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                scrollviewSwitchTextView.text = getString(R.string.switch_on)
            } else {
                scrollviewSwitchTextView.text = getString(R.string.switch_off)
            }
        }
    }

    /**
     * All you need to know is that we create 5 TextViews and
     * add it into ScrollView's main ViewGroup here.
     */
    private fun addViewsInScrollView() {
        // Add 5 TextViews dynamically.
        repeat(5) {
            val textView = TextView(this)

            // Setup the text size, color, content of the TextView.
            val padding = resources.getDimensionPixelSize(R.dimen.default_padding)
            textView.setPadding(padding, padding, padding, padding)
            textView.setTextColor(Color.RED)
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.scrollview_textview_size))
            val text = getString(R.string.view_create_dynamic, it + 1)
            textView.text = text
            textView.contentDescription = text
            textView.id = it + 100

            layout.addView(textView)

            // Add some constraint here, because the only one child view
            // inside ScrollView is ConstraintLayout.
            val constraintSet = ConstraintSet()
            constraintSet.clone(layout)
            if (it == 0) {
                constraintSet.connect(textView.id, ConstraintSet.TOP, R.id.scrollview_switch_text, ConstraintSet.BOTTOM, 0)
            } else {
                constraintSet.connect(textView.id, ConstraintSet.TOP, textView.id - 1, ConstraintSet.BOTTOM, 0)
            }

            constraintSet.connect(textView.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0)
            constraintSet.connect(textView.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0)

            if (it == 4) {
                constraintSet.connect(R.id.scrollview_footer_image, ConstraintSet.TOP, textView.id, ConstraintSet.BOTTOM, 0)
            }
            constraintSet.applyTo(layout)
        }
    }

    private fun toListViewActivity() {
        val intent = Intent(this, ListViewActivity::class.java)
        startActivity(intent)
    }

    /**
     * Handles the onClick for the "Send" button. Gets the value of the main EditText,
     * creates an intent, and launches the second activity with that intent.
     *
     * The return intent from the second activity is onActivityResult().
     *
     * @param view The view (Button) that was clicked.
     */
    @Suppress("UNUSED_PARAMETER")
    fun launchSecondActivity(view: View) {
        Log.d(LOG_TAG, "Button clicked!")
        val intent = Intent(this, OnViewActivity2::class.java)
        val message = mMessageEditText!!.text.toString()
        intent.putExtra(EXTRA_MESSAGE, message)
        startActivityForResult(intent, TEXT_REQUEST)
    }

    /**
     * Handles the data in the return intent from SecondActivity.
     *
     * @param requestCode Code for the SecondActivity request.
     * @param resultCode Code that comes back from SecondActivity.
     * @param data Intent data sent back from SecondActivity.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Test for the right intent reply.
        if (requestCode == TEXT_REQUEST) {
            // Test to make sure the intent reply result was good.
            if (resultCode == RESULT_OK) {
                val reply = data?.getStringExtra(OnViewActivity2.EXTRA_REPLY)

                // Make the reply head visible.
                mReplyHeadTextView?.visibility = View.VISIBLE

                // Set the reply and make it visible.
                mReplyTextView?.text = reply
                mReplyTextView?.visibility = View.VISIBLE
            }
        }
    }
}
