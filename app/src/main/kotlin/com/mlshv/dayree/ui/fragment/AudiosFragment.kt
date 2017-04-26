package com.mlshv.dayree.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mlshv.dayree.R
import com.mlshv.dayree.ui.adapter.AudiosAdapter

class AudiosFragment : Fragment() {
    lateinit var audiosRecyclerView : RecyclerView
    val recyclerViewAdapter = AudiosAdapter()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_audios, container, false)
        audiosRecyclerView = view.findViewById(R.id.audios_list) as RecyclerView
        return view
    }

    override fun onResume() {
        recyclerViewAdapter.notifyDataSetChanged()
        recyclerViewAdapter.update()
        super.onResume()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        audiosRecyclerView.adapter = recyclerViewAdapter
        audiosRecyclerView.layoutManager = LinearLayoutManager(activity)
        audiosRecyclerView.setHasFixedSize(true)
    }
}