package com.android.espressotest

import android.com.espressotest.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

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
