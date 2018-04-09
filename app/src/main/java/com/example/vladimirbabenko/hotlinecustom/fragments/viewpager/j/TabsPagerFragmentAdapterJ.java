package com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.j;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerFragmentAdapterJ extends FragmentPagerAdapter {

  final int PAGE_COUNT = 3;
  private String tabTitles[] = new String[] { NoteBookListFragmentJ.title, CarPartsFragmentJ.title, SomeFragmentJ.title };

  public TabsPagerFragmentAdapterJ(FragmentManager fm) {
    super(fm);
  }

  @Override public Fragment getItem(int position) {
    switch(position){
      case 0: return NoteBookListFragmentJ.newInstance(null);
      case 1: return CarPartsFragmentJ.newInstance();
      case 2: return SomeFragmentJ.newInstance();
      default: return new Fragment();
    }
  }

  @Override public int getCount() {
    return PAGE_COUNT;
  }

  @Nullable @Override public CharSequence getPageTitle(int position) {
    return tabTitles[position];
  }
}
