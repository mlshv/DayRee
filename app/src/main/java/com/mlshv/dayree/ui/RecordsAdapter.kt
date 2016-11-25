package com.mlshv.dayree.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.mlshv.dayree.R

class RecordsAdapter(val dataset: Array<String>) : RecyclerView.Adapter<RecordsAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.v?.text = dataset[position]
    }

    override fun getItemCount() = dataset.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context)
                .inflate(R.layout.record_text_view, parent, false) as TextView
        return ViewHolder(v)
    }

    class ViewHolder(val v: TextView) : RecyclerView.ViewHolder(v)
}