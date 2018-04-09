package com.example.vladimirbabenko.hotlinecustom.fragments.viewpager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.j.NoteBookListFragmentJ

class TabsPagerFragmentAdapter(val fm: FragmentManager) : FragmentPagerAdapter(fm) {




  override fun getItem(position: Int): Fragment {
    when (position) {
      1 -> return NoteBookListFragmentJ.newInstance(null);

    }
    return Fragment()
  }

  override fun getCount(): Int {
    TODO(
      "not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}