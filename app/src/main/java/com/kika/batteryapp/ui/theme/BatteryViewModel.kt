package com.kika.batteryapp.ui.theme

import android.util.Log
import androidx.lifecycle.ViewModel
import com.kika.batteryapp.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BatteryViewModel : ViewModel() {
    private val _batteryImage= MutableStateFlow(R.drawable.battery_full)

    val batteryImage = _batteryImage.asStateFlow()

    fun onBatteryLow() {
        _batteryImage.value = R.drawable.battery_low
    }
    fun onBatteryHigh() {
        _batteryImage.value = R.drawable.battery_full
    }

}