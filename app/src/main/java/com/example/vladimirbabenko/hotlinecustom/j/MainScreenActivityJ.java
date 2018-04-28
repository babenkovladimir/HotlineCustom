package com.example.vladimirbabenko.hotlinecustom.j;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import butterknife.BindView;
import com.example.vladimirbabenko.hotlinecustom.R;
import com.example.vladimirbabenko.hotlinecustom.base.j.BaseActivityJ;
import com.example.vladimirbabenko.hotlinecustom.fragments.j.MainScreenFragmentJ;
import com.example.vladimirbabenko.hotlinecustom.fragments.j.ProfileFragmentJ;
import com.example.vladimirbabenko.hotlinecustom.fragments.SettingsFragmentJ;

public class MainScreenActivityJ extends BaseActivityJ {

  @BindView(R.id.navigationView) BottomNavigationView mNavigationView;
  @BindView(R.id.flContainer) FrameLayout mContainer;

  private MainScreenFragmentJ mMainScreenFragmentJ;
  private ProfileFragmentJ mProfileFragmentJ;
  private SettingsFragmentJ mSettingsFragmentJ;

  @Override public void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_main_screen);
    super.onCreate(savedInstanceState);

    setupUI();
  }

  private void setupUI() {
    mMainScreenFragmentJ = new MainScreenFragmentJ();
    mProfileFragmentJ = new ProfileFragmentJ();
    mSettingsFragmentJ = new SettingsFragmentJ();

    mNavigationView.inflateMenu(R.menu.menu_navigation_items);

    mNavigationView.setOnNavigationItemSelectedListener(item-> {
      switch (item.getItemId()) {
        case R.id.bnmiMain: replaceFragment(mMainScreenFragmentJ, "MAIN_SCREEN_FRAGMENT"); break;
        case R.id.bnmiProfile: replaceFragment(mProfileFragmentJ, "PROFILE_FRAGMENT"); break;
        case R.id.bnmiSettings: replaceFragment(mSettingsFragmentJ, "SETTINGS_FRAGMENT"); break;
        default: break;
      }
      return true;
    });

    setupFirstAppearenceFragment();
  }

  private void replaceFragment(Fragment newFragment, String tag){
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.replace(R.id.flContainer, newFragment, tag).commit();// No AddToBAckStack

  }

  private void setupFirstAppearenceFragment() {
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.add(R.id.flContainer, mMainScreenFragmentJ, "MAIN_SCREEN_FRAGMENT").commit();
  }

}
