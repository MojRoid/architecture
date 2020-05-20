package com.example.feature.main.view

import android.content.Intent
import android.os.Bundle
import com.example.base.core.view.base.BaseActivity

class SplashActivity(override val layoutResourceId: Int? = null) : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
