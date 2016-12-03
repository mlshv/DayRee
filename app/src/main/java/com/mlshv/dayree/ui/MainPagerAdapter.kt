package com.mlshv.dayree.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup
import com.mlshv.dayree.ui.fragment.AudiosFragment
import com.mlshv.dayree.ui.fragment.PhotosFragment
import com.mlshv.dayree.ui.fragment.RecordsFragment


class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    val fragments = kotlin.arrayOfNulls<Fragment>(3)
    var currentFragment: Fragment? = null

    init {
        fragments[0] = (RecordsFragment())
        fragments[1] = (AudiosFragment())
        fragments[2] = (PhotosFragment())
    }

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size

    override fun setPrimaryItem(container: ViewGroup?, position: Int, any: Any) {
        if (currentFragment !== any) {
            currentFragment = any as Fragment
        }
        super.setPrimaryItem(container, position, any)
    }
}