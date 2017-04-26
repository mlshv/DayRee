package com.mlshv.dayree.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.mlshv.dayree.R
import com.mlshv.dayree.util.DatabaseHelper
import com.mlshv.dayree.model.Record
import com.mlshv.dayree.ui.view.RecordsListItem
import java.util.*

class RecordsAdapter : RecyclerView.Adapter<RecordsAdapter.RecordViewHolder>() {

    private val records : ArrayList<Record> = DatabaseHelper.getAllRecords()

    override fun onBindViewHolder(holder: RecordViewHolder?, position: Int) {
        holder!!.titleView.text = records[position].title
        holder.textView.text = records[position].text.replace("\n", " ")
        holder.recordDbId = records[position].id
        holder.itemView.setOnLongClickListener { removeAndDelete(position, holder.recordDbId); true }
    }

    override fun getItemCount() = records.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecordViewHolder {
        val v = LayoutInflater.from(parent?.context)
                .inflate(R.layout.record_list_item, parent, false) as RecordsListItem
        return RecordViewHolder(v)
    }

    private fun removeAndDelete(listItemIndex: Int, dbIndex: Long) {
        DatabaseHelper.deleteRecordById(dbIndex)
        records.removeAt(listItemIndex)
        this.notifyDataSetChanged()
    }

    fun update() {
        records.clear()
        records.addAll(DatabaseHelper.getAllRecords())
    }

    class RecordViewHolder(v: RecordsListItem) : RecyclerView.ViewHolder(v) {
        val titleView = v.findViewById(R.id.record_list_item_title) as TextView
        val textView = v.findViewById(R.id.record_list_item_text) as TextView
        var recordDbId = v.recordDbId
    }
}