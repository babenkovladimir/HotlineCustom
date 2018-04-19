package com.example.vladimirbabenko.hotlinecustom

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.vladimirbabenko.hotlinecustom.base.BaseActivity
import com.example.vladimirbabenko.hotlinecustom.fragments.MainScreenFragmentJ
import com.example.vladimirbabenko.hotlinecustom.fragments.ProfileFragmentJ
import com.example.vladimirbabenko.hotlinecustom.fragments.SettingsFragmentJ
import com.mikepenz.actionitembadge.library.ActionItemBadge
import kotlinx.android.synthetic.main.activity_main_screen.navigationView
import kotlinx.android.synthetic.main.activity_main_screen.toolbar

class MainScreenActivity : BaseActivity() {

  val MAIN_SCREEN_FRAGMENT_TAG: String = "MAIN_SCREEN_FRAGMENT_TAG"
  val PROFILE_FRAGMENT_TAG: String = "PROFILE_FRAGMENT_TAG"
  val SETTINGS_FRAGMENT_TAG: String = "SETTINGS_FRAGMENT_TAG"

  val mainScreenFragment: MainScreenFragmentJ by lazy { MainScreenFragmentJ() }
  val profileFragment: ProfileFragmentJ by lazy { ProfileFragmentJ() }
  val settingsFragment: SettingsFragmentJ by lazy { SettingsFragmentJ() } //lateinit var navigationView:NavigationView
  var bageCount = 125;

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main_screen)

    setupUI(savedInstanceState)

  }

  override fun onSaveInstanceState(outState: Bundle?) {
    outState?.putInt("opened_fragment", navigationView.selectedItemId)
    super.onSaveInstanceState(outState)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.menu_main_screen, menu)

    //ActionItemBadge.hide(menu?.findItem(R.id.miBascket))

    //ActionItemBadge.update(menu?.findItem(R.id.miBascket),11)
    ActionItemBadge.update(this, menu!!.findItem(R.id.miBascket),
      ContextCompat.getDrawable(this, R.drawable.ic_shopping_cart_24dp), ActionItemBadge.BadgeStyles.RED.getStyle(), dataManager.getFromBasket().size)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    Toast.makeText(this, "before onvalidate", Toast.LENGTH_SHORT).show()
    invalidateOptionsMenu()
    return true
    return super.onOptionsItemSelected(item)
  }


  // Private Helpers

  private fun setupUI(savedInstanceState: Bundle?) {
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

    val currentFragment = savedInstanceState?.getInt("opened_fragment")
    if (currentFragment != null) {
      navigationView.selectedItemId = currentFragment
    } else setupFirstAppearenceFragment()

    val toolbar = toolbar
    setSupportActionBar(toolbar)
  }

  private fun replaceFragment(newFragment: Fragment, tag: String): Boolean {
    val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
    ft.replace(R.id.flContainer, newFragment, tag).commit() //No AddToBackStack
    return true
  }

  private fun setupFirstAppearenceFragment() {
    val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
    ft.replace(R.id.flContainer, mainScreenFragment, MAIN_SCREEN_FRAGMENT_TAG)
      .commit() //No AddToBackStack
  }
}