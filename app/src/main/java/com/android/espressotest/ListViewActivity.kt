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
import androidx.core.content.ContextCompat
import com.android.espressotest.data.Data
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

    /**
     * The data holder.
     */
    private lateinit var adapter: ListAdapter

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
        setupListView()
    }

    private fun getJsonReaderFromAssets(): Reader {
        val `is` = assets.open("list.json")
        return InputStreamReader(`is`, "UTF-8")
    }

    private fun findAndSetView() {
        listView = findViewById(R.id.list_view)
    }

    private fun setupListView() {
        adapter = ListAdapter(this, listData.data)
        listView.adapter = adapter
        // Define click event to change background color
        // instead of using `android:choiceMode` and `android:listSelector`
        // in layout xml to easy test this scenario.
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            adapter.notifyDataClicked(position)
        }
    }

    companion object {
        private val TAG: String = ListViewActivity::class.java.simpleName
    }

    class ListAdapter(
            private val context: Context,
            private val data: List<Data>
    ) : BaseAdapter() {

        private var clickPosition = -1

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

            viewHolder.image.setImageResource(data[position].image.toImageResId())
            viewHolder.title.text = data[position].title
            viewHolder.message.text = data[position].text
            viewHolder.checkbox.isChecked = data[position].check_box

            if (position == clickPosition) {
                viewHolder.rootView.setBackgroundColor(ContextCompat.getColor(context, R.color.list_item_click))
            } else {
                viewHolder.rootView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            }
            return root
        }

        override fun getItem(position: Int): Any {
            return data[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return data.size
        }

        fun notifyDataClicked(position: Int) {
            clickPosition = position
            notifyDataSetChanged()
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