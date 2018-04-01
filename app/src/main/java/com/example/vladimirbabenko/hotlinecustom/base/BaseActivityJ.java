package com.example.vladimirbabenko.hotlinecustom.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import com.example.vladimirbabenko.hotlinecustom.data.PreferencesHelper;

public class BaseActivityJ extends AppCompatActivity {

  protected final String TAG = "TAG";
  protected final PreferencesHelper prefs = App.Companion.getAppPrefs();

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ButterKnife.bind(this);
  }
}
