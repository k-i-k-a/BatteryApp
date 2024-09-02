package com.kika.batteryapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager

class BatteryBroadcastReceiver(private val onBatteryLow: () -> Unit,private val onBatteryHigh: () -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val batteryLevel = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
        val batteryScale = intent?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1
        val batteryPct = batteryLevel / batteryScale.toFloat() * 100

        if (batteryPct.toInt() <= 20) {
            onBatteryLow()
        }else{
            onBatteryHigh()
        }
    }
}
