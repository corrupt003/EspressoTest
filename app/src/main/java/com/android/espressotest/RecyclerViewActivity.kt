package com.android.espressotest

import android.com.espressotest.R
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.espressotest.data.Data
import com.android.espressotest.data.ListData
import com.android.espressotest.extensions.toImageResId
import com.google.gson.Gson

class RecyclerViewActivity : AppCompatActivity() {
    /**
     * The list item we will show latter.
     */
    private lateinit var listData: ListData

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: ViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        findAndSetView()
        val gson = Gson()
        listData = gson.fromJson(Utils.getJsonReaderFromAssets(assets), ListData::class.java)
        Log.i(TAG, "listData = $listData")
    }

    override fun onStart() {
        super.onStart()
        // Change Appbar title.
        supportActionBar?.title = getString(R.string.recycler_view_view_activity_title)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = ViewAdapter(listData.data)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
    }

    private fun findAndSetView() {
        recyclerView = findViewById(R.id.recycler_view)
    }

    class ViewAdapter(
            data: List<Data>
    ) : RecyclerView.Adapter<DataHolder>() {
        private var listData: MutableList<Data> = ArrayList(data)

        private val checkChangeListener = CompoundButton.OnCheckedChangeListener { view, isCheck ->
            Handler().post {
                val holder: DataHolder = view.tag as DataHolder
                val position = holder.adapterPosition
                setItemChecked(position, isCheck)
            }
        }

        private fun setItemChecked(position: Int, check: Boolean) {
            listData[position].check_box = check
            notifyItemChanged(position)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
            val root = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
            val holder = DataHolder(root)
            holder.switch.tag = holder
            holder.switch.setOnCheckedChangeListener(checkChangeListener)
            return holder
        }

        override fun getItemCount(): Int {
            return listData.size
        }

        override fun onBindViewHolder(holder: DataHolder, position: Int) {
            holder.image.setImageResource(listData[position].image.toImageResId())
            holder.title.text = listData[position].title
            holder.text.text = listData[position].text
            holder.switch.isChecked = listData[position].check_box

            if (holder.switch.isChecked) {
                holder.image.visibility = View.VISIBLE
                holder.title.visibility = View.VISIBLE
                holder.text.visibility = View.VISIBLE
            } else {
                holder.image.visibility = View.INVISIBLE
                holder.title.visibility = View.INVISIBLE
                holder.text.visibility = View.INVISIBLE
            }
        }
    }

    class DataHolder(root: View) : RecyclerView.ViewHolder(root) {
        val image: ImageView = root.findViewById(R.id.recyclerview_item_image)
        val title: TextView = root.findViewById(R.id.recyclerview_item_title)
        val text: TextView = root.findViewById(R.id.recyclerview_item_message)
        val switch: Switch = root.findViewById(R.id.recyclerview_item_switch)
    }

    companion object {
        private val TAG = RecyclerViewActivity::class.java.simpleName
    }
}