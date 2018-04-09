package com.example.vladimirbabenko.hotlinecustom.fragments;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.vladimirbabenko.hotlinecustom.R;
import com.example.vladimirbabenko.hotlinecustom.data.DataManager;
import com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.TabsPagerFragmentAdapter;
import com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.j.TabsPagerFragmentAdapterJ;

public class MainScreenFragmentJ extends Fragment {

  private DataManager mDataManager;
  @BindView(R.id.tlTabLayout) TabLayout mTabLayout;
  @BindView(R.id.vpPager) ViewPager mViewPager;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mDataManager = DataManager.Companion.create(container.getContext());

    View view = null;
    view = inflater.inflate(R.layout.fragment_main_screen, container, false);

    ButterKnife.bind(this, view);

    TabsPagerFragmentAdapter adapter = new TabsPagerFragmentAdapter(getChildFragmentManager());

    mViewPager.setAdapter(adapter);
    mTabLayout.setupWithViewPager(mViewPager);


    return view;
  }
}
