package com.example.vladimirbabenko.hotlinecustom.fragments.j;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.vladimirbabenko.hotlinecustom.R;
import com.example.vladimirbabenko.hotlinecustom.data.DataManager;
import com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.TabsPagerFragmentAdapter;

public class MainScreenFragmentJ extends Fragment {

  private DataManager mDataManager;
  //@BindView(R.id.tlTabLayout) TabLayout mTabLayout;
  TabLayout mTabLayout;
  //@BindView(R.id.vpPager) ViewPager mViewPager;
  //@BindView(R.id.vpPager)
  ViewPager mViewPager;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mDataManager = DataManager.Companion.getCreate();
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {


    View view = null;
    view = inflater.inflate(R.layout.fragment_main_screen, container, false);

    mViewPager = (ViewPager) view.findViewById(R.id.vpPager);
    mTabLayout = (TabLayout) view.findViewById(R.id.tlTabLayout);
    //ButterKnife.bind(this, view);

    TabsPagerFragmentAdapter adapter = new TabsPagerFragmentAdapter(getChildFragmentManager());

    mViewPager.setAdapter(adapter);
    mTabLayout.setupWithViewPager(mViewPager);

    return view;
  }
}
