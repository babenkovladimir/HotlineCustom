package com.example.vladimirbabenko.hotlinecustom.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;

public class BaseActivityJ extends AppCompatActivity {

  protected final String TAG = "TAG";

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ButterKnife.bind(this);
  }
}
