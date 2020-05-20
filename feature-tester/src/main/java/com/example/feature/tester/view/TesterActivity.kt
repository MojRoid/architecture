package com.example.feature.tester.view

import androidx.navigation.fragment.NavHostFragment
import com.example.base.core.view.base.BaseActivity
import com.example.feature.tester.R
import kotlinx.android.synthetic.main.activity_tester.*

class TesterActivity(
    override val layoutResourceId: Int = R.layout.activity_tester
) : BaseActivity() {

    override fun onSupportNavigateUp() = NavHostFragment.findNavController(nav_host).navigateUp()
}
