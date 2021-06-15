package com.srgnk.simplealarmclock.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.srgnk.simplealarmclock.databinding.StopAlarmActivityBinding
import com.srgnk.simplealarmclock.service.AlarmService

class StopAlarmActivity: AppCompatActivity() {

    private lateinit var binding: StopAlarmActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = StopAlarmActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.stopAlarm.setOnClickListener {
            applicationContext.stopService(Intent(applicationContext, AlarmService::class.java))

            val intent = Intent(this, AppActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}