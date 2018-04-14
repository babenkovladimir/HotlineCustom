package com.example.vladimirbabenko.hotlinecustom.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class MyReceiverCheckInternetConnectionJ extends BroadcastReceiver {

  @Override public void onReceive(Context context, Intent intent) {
    ConnectivityManager cm =
        (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
    boolean isConnected = activeNetwork.isConnectedOrConnecting();

    //Toast.makeText(context, "Now connection is"+ isConnected, Toast.LENGTH_SHORT).show();
  }
}
