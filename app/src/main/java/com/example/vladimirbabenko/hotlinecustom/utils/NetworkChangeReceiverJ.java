package com.example.vladimirbabenko.hotlinecustom.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
import com.example.vladimirbabenko.hotlinecustom.data.DataManager;

public class NetworkChangeReceiverJ extends BroadcastReceiver {
  @Override public void onReceive(final Context context, final Intent intent) {

    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo netInfo = cm.getActiveNetworkInfo();

    //should check null because in airplane mode it will be null
    Boolean isOnline = netInfo != null && netInfo.isConnected();

    Toast.makeText(context, "Network is avaible " + isOnline, Toast.LENGTH_SHORT).show();

    DataManager.Companion.create().getPrefs().setWithInternetConnection(isOnline);
  }
}
