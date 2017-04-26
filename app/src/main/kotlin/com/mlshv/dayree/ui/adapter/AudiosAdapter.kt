package com.mlshv.dayree.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.mlshv.dayree.R
import com.mlshv.dayree.model.Audio
import com.mlshv.dayree.ui.view.AudioListItem
import com.mlshv.dayree.util.DatabaseHelper
import java.util.*

class AudiosAdapter : RecyclerView.Adapter<AudiosAdapter.AudioViewHolder>() {
    private val audios: ArrayList<Audio> = DatabaseHelper.getAllAudios()

    override fun onBindViewHolder(holder: AudioViewHolder?, position: Int) {
        holder!!.titleView.text = audios[position].title
        holder.fileNameView.text = audios[position].filename
        holder.audioDbId = audios[position].id
        holder.itemView.setOnLongClickListener { removeAndDelete(position, holder.audioDbId); true }
    }

    override fun getItemCount() = audios.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AudioViewHolder {
        val v = LayoutInflater.from(parent?.context)
                .inflate(R.layout.audios_list_item, parent, false) as AudioListItem
        return AudioViewHolder(v)
    }

    private fun removeAndDelete(listItemIndex: Int, dbIndex: Long) {
        DatabaseHelper.deleteRecordById(dbIndex)
        audios.removeAt(listItemIndex)
        this.notifyDataSetChanged()
    }

    fun update() {
        audios.clear()
        audios.addAll(DatabaseHelper.getAllAudios())
    }

    class AudioViewHolder(v: AudioListItem) : RecyclerView.ViewHolder(v) {
        val titleView = v.findViewById(R.id.audio_list_item_title) as TextView
        val fileNameView = v.findViewById(R.id.audio_list_item_filename) as TextView
        var audioDbId = v.audioDbId
    }
}