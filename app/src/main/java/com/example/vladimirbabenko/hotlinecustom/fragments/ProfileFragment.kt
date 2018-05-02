package com.example.vladimirbabenko.hotlinecustom.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.vladimirbabenko.hotlinecustom.R
import com.example.vladimirbabenko.hotlinecustom.data.DataManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.fabChangeProfileInformation
import kotlinx.android.synthetic.main.fragment_profile.view.civCircleImageView
import kotlinx.android.synthetic.main.fragment_profile.view.tvProfileEmail
import kotlinx.android.synthetic.main.fragment_profile.view.tvProfileId
import kotlinx.android.synthetic.main.fragment_profile.view.tvProfileName

class ProfileFragment : Fragment() {

  private val dataManager = DataManager.create

  companion object {
    fun newInstance(bundle: Bundle? = null): ProfileFragment {
      val fragment = ProfileFragment()
      fragment.arguments = bundle
      return fragment
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_profile, container, false)
  }

  // Fun gets userInfo from Realm
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState) // val user = dataManager.prefs.userInJson
    val userRealm = dataManager.getUser()

    with(userRealm) {
      with(view) {
        tvProfileId.text = userRealm?.userId
        tvProfileName.text = userRealm?.displayedName
        tvProfileEmail.text = userRealm?.email
      }
    }

    Picasso.get().load(userRealm?.fotoUrl).error(R.drawable.mr_white).into(view.civCircleImageView)

    fabChangeProfileInformation.setOnClickListener(){
      Toast.makeText(context, "Firebase is called", Toast.LENGTH_SHORT).show()
//      dataManager.saveToFirebase()

      dataManager.saveChosenListtoFirebase(dataManager.getChosenList())
    }
  }
}