package com.srgnk.simplealarmclock.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.srgnk.simplealarmclock.R
import com.srgnk.simplealarmclock.mvp.view.AppView
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.srgnk.simplealarmclock.databinding.ActivityAppBinding

class AppActivity : AppCompatActivity(), AppView {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}