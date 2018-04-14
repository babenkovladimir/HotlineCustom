package com.example.vladimirbabenko.hotlinecustom.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

public class BattaryChargingReceiver extends BroadcastReceiver {

  @Override public void onReceive(Context context, Intent intent) {

    int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
    boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
        status == BatteryManager.BATTERY_STATUS_FULL;

    int isChargingNow  = BatteryManager.BATTERY_STATUS_CHARGING;

    Toast.makeText(context, "Battary is charging - "+ isChargingNow, Toast.LENGTH_SHORT).show();
  }
}
