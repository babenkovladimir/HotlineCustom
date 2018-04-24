package com.example.vladimirbabenko.hotlinecustom

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.example.vladimirbabenko.hotlinecustom.bascket_activity_mvp.BascketActivity
import com.example.vladimirbabenko.hotlinecustom.base.BaseActivity
import com.example.vladimirbabenko.hotlinecustom.event_bus.Events.BascketEvent
import com.example.vladimirbabenko.hotlinecustom.event_bus.GlobalBus
import com.example.vladimirbabenko.hotlinecustom.fragments.MainScreenFragmentJ
import com.example.vladimirbabenko.hotlinecustom.fragments.ProfileFragmentJ
import com.example.vladimirbabenko.hotlinecustom.fragments.SettingsFragmentJ
import com.mikepenz.actionitembadge.library.ActionItemBadge
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.activity_main_screen.navigationView
import kotlinx.android.synthetic.main.activity_main_screen.toolbar

class MainScreenActivity : BaseActivity() {

  val MAIN_SCREEN_FRAGMENT_TAG: String = "MAIN_SCREEN_FRAGMENT_TAG"
  val PROFILE_FRAGMENT_TAG: String = "PROFILE_FRAGMENT_TAG"
  val SETTINGS_FRAGMENT_TAG: String = "SETTINGS_FRAGMENT_TAG"

  val mainScreenFragment: MainScreenFragmentJ by lazy { MainScreenFragmentJ() }
  val profileFragment: ProfileFragmentJ by lazy { ProfileFragmentJ() }
  val settingsFragment: SettingsFragmentJ by lazy { SettingsFragmentJ() } //lateinit var navigationView:NavigationView

  val bus = GlobalBus.instance

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main_screen)
    bus.register(this);

    setupUI(savedInstanceState)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_main_screen, menu)
    val count = dataManager.getFromBasket().size
    if (count < 1) {
      ActionItemBadge.hide(menu?.findItem(R.id.miBascket))
    } else {
      ActionItemBadge.update(this, menu!!.findItem(R.id.miBascket),
        ContextCompat.getDrawable(this, R.drawable.ic_shopping_cart_24dp),
        ActionItemBadge.BadgeStyles.RED.getStyle(), count)
    }
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    startActivity(Intent(this, BascketActivity::class.java))
    return true
  }

  override fun onDestroy() {
    bus.unregister(this);
    super.onDestroy()
  }

  // Private Helpers

  @Subscribe fun updateBadge(event: BascketEvent) {
    invalidateOptionsMenu()
  }

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

  override fun onSaveInstanceState(outState: Bundle?) {
    outState?.putInt("opened_fragment", navigationView.selectedItemId)
    super.onSaveInstanceState(outState)
  }
}