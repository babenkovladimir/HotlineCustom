package com.example.vladimirbabenko.hotlinecustom.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.data.DataManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.view.civCircleImageView
import kotlinx.android.synthetic.main.fragment_profile.view.tvProfileEmail
import kotlinx.android.synthetic.main.fragment_profile.view.tvProfileId
import kotlinx.android.synthetic.main.fragment_profile.view.tvProfileName

class ProfileFragment : Fragment() {

  private val dataManager = DataManager.create

  companion object {
    fun newInstance(bundle: Bundle?): ProfileFragment {
      val fragment = ProfileFragment()
      fragment.arguments = bundle
      return fragment
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_profile, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState) //    val user = dataManager.prefs.userInJson
    val user = dataManager.getUser()
    Log.d("TAGPROFIREREALM", "realmYser = "+ user.toString())
//    with(user) {
//      with(view) {
//        tvProfileId.text = user.id
//        tvProfileName.text = displayedName
//        tvProfileEmail.text = email
//      }
//    }
//
//    Picasso.get().load(user.fotoUrl).error(R.drawable.mr_white).into(view.civCircleImageView)
  }
}