package com.example.vladimirbabenko.hotlinecustom.fragments.viewpager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.vladimirbabenko.hotlinecustom.fragments.NoteBookListFragment
import com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.j.CarPartsFragmentJ
import com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.j.NoteBookListFragmentJ
import com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.j.SomeFragmentJ

class TabsPagerFragmentAdapter(val fm: FragmentManager) : FragmentPagerAdapter(fm) {

  private val PAGE_COUNT: Int = 3
  private val tabTitels = arrayOf(NoteBookListFragment.title,"Car parts"," Some...")

  override fun getItem(position: Int): Fragment {
    when (position) {
      0 -> return NoteBookListFragment.newInstance(null);
      1 -> return CarPartsFragmentJ.newInstance()
      2 -> return SomeFragmentJ.newInstance()
    }
    return Fragment()
  }

  override fun getCount() = PAGE_COUNT

  override fun getPageTitle(position: Int): CharSequence? = tabTitels[position]

}