package dayree.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup
import dayree.ui.fragment.AudiosFragment
import dayree.ui.fragment.PhotosFragment
import dayree.ui.fragment.RecordsFragment


class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    val fragments = arrayOfNulls<Fragment>(3)
    var currentFragment: Fragment? = null

    init {
        fragments[0] = (RecordsFragment())
        fragments[1] = (AudiosFragment())
        fragments[2] = (PhotosFragment())
    }

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