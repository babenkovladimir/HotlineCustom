package com.example.vladimirbabenko.hotlinecustom

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.example.vladimirbabenko.hotlinecustom.base.BaseActivity
import com.example.vladimirbabenko.hotlinecustom.fragments.MainScreenFragmentJ
import com.example.vladimirbabenko.hotlinecustom.fragments.ProfileFragmentJ
import com.example.vladimirbabenko.hotlinecustom.fragments.SettingsFragmentJ
import kotlinx.android.synthetic.main.activity_main_screen.navigationView

class MainScreenActivity : BaseActivity() {

  val MAIN_SCREEN_FRAGMENT_TAG: String = "MAIN_SCREEN_FRAGMENT_TAG"
  val PROFILE_FRAGMENT_TAG: String = "PROFILE_FRAGMENT_TAG"
  val SETTINGS_FRAGMENT_TAG: String = "SETTINGS_FRAGMENT_TAG"

  val mainScreenFragment: MainScreenFragmentJ by lazy { MainScreenFragmentJ() }
  val profileFragment: ProfileFragmentJ by lazy { ProfileFragmentJ() }
  val settingsFragment: SettingsFragmentJ by lazy { SettingsFragmentJ() }


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main_screen)

    setupUI()
  }

  private fun setupUI() {
    navigationView.inflateMenu(R.menu.menu_navigation_items)

    navigationView.setOnNavigationItemSelectedListener({
      when (it.itemId) {
        R.id.bnmiMain -> replaceFragment(mainScreenFragment, MAIN_SCREEN_FRAGMENT_TAG)
        R.id.bnmiProfile -> replaceFragment(profileFragment, PROFILE_FRAGMENT_TAG)
        R.id.bnmiSettings -> replaceFragment(settingsFragment, SETTINGS_FRAGMENT_TAG)
        else -> {
          false
        }
      }
    })
    setupFirstAppearenceFragment()
  }

  private fun replaceFragment(newFragment: Fragment, tag: String): Boolean {
    val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
    ft.replace(R.id.flContainer, newFragment, tag).commit() //No AddToBackStack
    return true
  }

  private fun setupFirstAppearenceFragment() {
    val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
    ft.replace(R.id.flContainer, mainScreenFragment , MAIN_SCREEN_FRAGMENT_TAG).commit() //No AddToBackStack
  }
}