package com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.j;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.example.vladimirbabenko.hotlinecustom.R;

public class CarPartsFragmentJ extends Fragment {

  public static final String title = "Car Parts";

  public static CarPartsFragmentJ newInstance() {
     Bundle args = new Bundle();
     CarPartsFragmentJ fragment = new CarPartsFragmentJ();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = null;
    view = inflater.inflate(R.layout.fragment_car_parts, container, false);

    ButterKnife.bind(this, view);

    return view;
  }
}
