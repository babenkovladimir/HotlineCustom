package com.example.vladimirbabenko.hotlinecustom.base.j;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import com.example.vladimirbabenko.hotlinecustom.data.DataManager;
import com.example.vladimirbabenko.hotlinecustom.data.PreferencesHelper;

public abstract class BaseActivityJ extends AppCompatActivity {

  protected final String TAG = "TAG";
  // Get Preferences from App:Application()
  //protected final PreferencesHelper prefs = App.Companion.getAppPrefs();
    protected DataManager mDataManager;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mDataManager = DataManager.Companion.getCreate();
    ButterKnife.bind(this);
  }
}
