package com.android.espressotest

import android.com.espressotest.R
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.espressotest.data.ListData
import com.google.gson.Gson
import java.io.InputStreamReader
import java.io.Reader


class ListViewActivity : AppCompatActivity() {
    private lateinit var listData: ListData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gson = Gson()
        listData = gson.fromJson(getJsonReaderFromAssets(), ListData::class.java)
        Log.i(TAG, "listData = $listData")
    }

    override fun onStart() {
        super.onStart()
        // Change Appbar title.
        supportActionBar?.title = getString(R.string.list_view_activity_title)
    }

    private fun getJsonReaderFromAssets(): Reader {
        val `is` = assets.open("list.json")
        return InputStreamReader(`is`, "UTF-8")
    }

    companion object {
        private val TAG: String = ListViewActivity::class.java.simpleName
    }
}