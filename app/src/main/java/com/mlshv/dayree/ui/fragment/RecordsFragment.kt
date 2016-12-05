package com.mlshv.dayree.ui.fragment

import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import com.mlshv.dayree.R
import com.mlshv.dayree.ui.RecordsAdapter
import android.support.v7.widget.LinearLayoutManager

class RecordsFragment : Fragment() {

    var recordsRecyclerView : RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_records, container, false)
        recordsRecyclerView = view.findViewById(R.id.record_list) as RecyclerView

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val recyclerViewAdapter = RecordsAdapter()
        recordsRecyclerView?.adapter = recyclerViewAdapter
        recordsRecyclerView?.layoutManager = LinearLayoutManager(activity)
        recordsRecyclerView?.setHasFixedSize(true)
    }
}