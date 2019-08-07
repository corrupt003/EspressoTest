package com.android.espressotest

import android.com.espressotest.R
import androidx.appcompat.app.AppCompatActivity

class ListViewActivity : AppCompatActivity() {
    override fun onStart() {
        super.onStart()
        // Change Appbar title.
        supportActionBar?.title = getString(R.string.list_view_activity_title)
    }
}