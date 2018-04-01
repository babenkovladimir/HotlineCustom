package com.example.vladimirbabenko.hotlinecustom.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.vladimirbabenko.hotlinecustom.R;

public class ProfileFragmentJ extends Fragment {

  public static ProfileFragmentJ newInstance(Bundle args) {
    ProfileFragmentJ fragment = new ProfileFragmentJ();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view=null;

    view = inflater.inflate(R.layout.fragment_profile, container, false);


    return view;
  }
}
