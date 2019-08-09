package com.android.espressotest

import android.com.espressotest.R
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.espressotest.data.ListData
import com.android.espressotest.extensions.toImageResId
import com.google.gson.Gson
import java.io.InputStreamReader
import java.io.Reader


class ListViewActivity : AppCompatActivity() {
    /**
     * The list item we will show latter.
     */
    private lateinit var listData: ListData

    /**
     * The main UI component to show a bunch of list data.
     */
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listview)

        findAndSetView()
        val gson = Gson()
        listData = gson.fromJson(getJsonReaderFromAssets(), ListData::class.java)
        Log.i(TAG, "listData = $listData")
    }

    override fun onStart() {
        super.onStart()
        // Change Appbar title.
        supportActionBar?.title = getString(R.string.list_view_activity_title)
        listView.adapter = ListAdapter(this, listData)
    }

    private fun getJsonReaderFromAssets(): Reader {
        val `is` = assets.open("list.json")
        return InputStreamReader(`is`, "UTF-8")
    }

    private fun findAndSetView() {
        listView = findViewById(R.id.list_view)
    }

    companion object {
        private val TAG: String = ListViewActivity::class.java.simpleName
    }

    class ListAdapter(
            private val context: Context,
            private val list: ListData
    ) : BaseAdapter() {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val root: View
            val viewHolder: ViewHolder
            if (convertView == null) {
                root = LayoutInflater.from(context).inflate(R.layout.listview_item, parent, false)
                val image: ImageView = root.findViewById(R.id.listview_item_image)
                val title: TextView = root.findViewById(R.id.listview_item_title)
                val message: TextView = root.findViewById(R.id.listview_item_message)
                val checkbox: CheckBox = root.findViewById(R.id.listview_item_checkbox)
                viewHolder = ViewHolder(
                        root,
                        image,
                        title,
                        message,
                        checkbox
                )
                root.tag = viewHolder
            } else {
                root = convertView
                viewHolder = root.tag as ViewHolder
            }

            viewHolder.image.setImageResource(list.data[position].image.toImageResId())
            viewHolder.title.text = list.data[position].title
            viewHolder.message.text = list.data[position].text
            viewHolder.checkbox.isChecked = list.data[position].check_box

            return root
        }

        override fun getItem(position: Int): Any {
            return list.data[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return list.data.size
        }
    }

    data class ViewHolder(
            var rootView: View,
            var image: ImageView,
            var title: TextView,
            var message: TextView,
            var checkbox: CheckBox
    )
}