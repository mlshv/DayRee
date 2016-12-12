package com.mlshv.dayree.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.mlshv.dayree.R
import com.mlshv.dayree.db.DatabaseHelper
import com.mlshv.dayree.model.Record
import com.mlshv.dayree.ui.view.RecordsListItem
import java.util.*

class RecordsAdapter() : RecyclerView.Adapter<RecordsAdapter.ViewHolder>() {

    private val records : ArrayList<Record> = DatabaseHelper.getAllRecords()

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.titleView.text = records[position].title
        holder.textView.text = records[position].text.replace("\n", " ")
        holder.recordId = records[position].id
    }

    override fun getItemCount() = records.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context)
                .inflate(R.layout.record_list_item, parent, false) as RecordsListItem
        return ViewHolder(v)
    }

    fun remove(index: Int) {
        records.removeAt(index)
        this.notifyDataSetChanged()
    }

    fun update() {
        records.clear()
        records.addAll(DatabaseHelper.getAllRecords())
    }

    class ViewHolder(v: RecordsListItem) : RecyclerView.ViewHolder(v) {
        val titleView = v.findViewById(R.id.record_list_item_title) as TextView
        val textView = v.findViewById(R.id.record_list_item_text) as TextView
        var recordId = v.recordId
    }
}