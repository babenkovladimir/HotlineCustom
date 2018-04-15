package com.example.vladimirbabenko.hotlinecustom.fragments.viewpager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.vladimirbabenko.hotlinecustom.fragments.NoteBookListFragment
import com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.car_part_fragment_mvp.CarPartsFragment

class TabsPagerFragmentAdapter(val fm: FragmentManager) : FragmentPagerAdapter(fm) {

  private val PAGE_COUNT: Int = 3
  private val tabTitels =
    arrayOf(NoteBookListFragment.title, CarPartsFragment.title, SomeFragment.title)

  override fun getItem(position: Int): Fragment {
    when (position) {
      0 -> return NoteBookListFragment.newInstance(null);
      1 -> return CarPartsFragment.newInstance(null)
      2 -> return SomeFragment.newInstance(null)
    }
    return Fragment()
  }

  override fun getCount() = PAGE_COUNT

  override fun getPageTitle(position: Int): CharSequence? = tabTitels[position]
}