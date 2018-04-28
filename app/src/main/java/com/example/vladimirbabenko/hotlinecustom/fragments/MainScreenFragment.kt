package com.example.vladimirbabenko.hotlinecustom.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.data.DataManager
import com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.TabsPagerFragmentAdapter
import kotlinx.android.synthetic.main.fragment_main_screen.view.tlTabLayout
import kotlinx.android.synthetic.main.fragment_main_screen.view.vpPager

class MainScreenFragment() : Fragment() {

  val dataManager = DataManager.create

  companion object {
    fun newInstance(bundle: Bundle?): MainScreenFragment {
      val fragment = MainScreenFragment()
      fragment.arguments = bundle
      return fragment
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_main_screen, container, false)


    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val adapter = TabsPagerFragmentAdapter(childFragmentManager)

    val tabLayout = view.tlTabLayout
    val viewPager = view.vpPager

    viewPager.adapter = adapter
    tabLayout.setupWithViewPager(viewPager)


  }
}