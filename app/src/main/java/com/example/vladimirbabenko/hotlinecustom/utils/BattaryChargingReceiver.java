package com.example.vladimirbabenko.hotlinecustom.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;
import android.widget.Toast;

public class BattaryChargingReceiver extends BroadcastReceiver {

  @Override public void onReceive(Context context, Intent intent) {

    String action = intent.getAction();
    if(action.equals(Intent.ACTION_POWER_CONNECTED)) {
      Toast.makeText(context, "Power connected", Toast.LENGTH_SHORT).show();
    }
    else if(action.equals(Intent.ACTION_POWER_DISCONNECTED)) {
      Toast.makeText(context, "Power disconected", Toast.LENGTH_SHORT).show();
    }

  }
}
