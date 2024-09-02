package com.kika.batteryapp

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.kika.batteryapp.ui.theme.BatteryAppTheme
import com.kika.batteryapp.ui.theme.BatteryViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
private lateinit var batteryReceiver: BatteryBroadcastReceiver
private lateinit var viewModel: BatteryViewModel
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = BatteryViewModel()

        batteryReceiver = BatteryBroadcastReceiver({ viewModel.onBatteryLow() },
            { viewModel.onBatteryHigh() })

        registerReceiver(
            batteryReceiver,
            IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        )

        enableEdgeToEdge()
        setContent {
            BatteryAppTheme {
                BatteryImage(viewModel=viewModel)
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(batteryReceiver)
    }
}

@Composable
fun BatteryImage(
    modifier: Modifier = Modifier,
    viewModel: BatteryViewModel=viewModel()
) {
    val batteryImage by viewModel.batteryImage.collectAsState()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().then(modifier)
    ) {
        Image(
            painter = painterResource(id = batteryImage),
            contentDescription = "Battery"
        )
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun BatteryImagePreview() {
    BatteryImage()
}