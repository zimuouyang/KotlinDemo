package gateway.hitrontech.com.kotlindemo.base

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by ShuaiLei Zhao on 4/7/2019.
 */
 
 class BaseFragmentAdapter : FragmentPagerAdapter {

    private var fragmentList : List<Fragment>? = ArrayList()
    private var mTitles : List<String>? = null

    constructor(fm : FragmentManager, frgmentList : List<Fragment>) : super(fm) {
        this.fragmentList = fragmentList
    }

    constructor(fm: FragmentManager, fragmentList: List<Fragment>, mTitles: List<String>) : super(fm) {
        this.mTitles = mTitles
        setFragments(fm, fragmentList, mTitles)
    }

    //刷新fragment
    @SuppressLint("CommitTransaction")
    private fun setFragments(fm: FragmentManager, fragments: List<Fragment>, mTitles: List<String>) {
        this.mTitles = mTitles
        if (this.fragmentList != null) {
            val ft = fm.beginTransaction()
            fragmentList?.forEach {
                ft.remove(it)
            }
            ft?.commitAllowingStateLoss()
            fm.executePendingTransactions()
        }
        this.fragmentList = fragments
        notifyDataSetChanged()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitles ?.get(position) ?: ""
    }
    override fun getItem(pos: Int): Fragment {
        return fragmentList !!.get(pos)
    }

    override fun getCount() : Int {
        return fragmentList?.size ?: 0
    }
}