package com.mlshv.dayree.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup
import com.mlshv.dayree.ui.fragment.AudiosFragment
import com.mlshv.dayree.ui.fragment.PhotosFragment
import com.mlshv.dayree.ui.fragment.RecordsFragment


class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    val fragments : Array<Fragment> = arrayOf(
            RecordsFragment(),
            AudiosFragment(),
            PhotosFragment())

    var currentFragment = fragments[0]

    fun scrollTop() = (fragments[0] as RecordsFragment).scrollTop()

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size

    override fun setPrimaryItem(container: ViewGroup?, position: Int, any: Any) {
        if (currentFragment !== any) {
            currentFragment = any as Fragment
        }
        super.setPrimaryItem(container, position, any)
    }
}