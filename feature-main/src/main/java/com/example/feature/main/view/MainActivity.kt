package com.example.feature.main.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import com.example.base.core.view.base.BaseActivity
import com.example.feature.main.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity(
    override val layoutResourceId: Int = R.layout.activity_main
) : BaseActivity() {

    private val connectivityManager: ConnectivityManager by lazy {
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    private val connectivityIntentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)

    private val connectivityReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            renderConnectionStatus()
        }
    }

    override fun onSupportNavigateUp() = NavHostFragment.findNavController(nav_host).navigateUp()

    override fun onPause() {
        unregisterReceiver(connectivityReceiver)
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(connectivityReceiver, connectivityIntentFilter)
    }

    private fun renderConnectionStatus() {
        main_activity_offline_message.isVisible =
            connectivityManager.activeNetworkInfo?.isConnected != true
    }
}
