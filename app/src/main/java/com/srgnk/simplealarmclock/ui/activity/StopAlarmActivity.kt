package com.srgnk.simplealarmclock.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.srgnk.simplealarmclock.databinding.StopAlarmActivityBinding
import com.srgnk.simplealarmclock.service.AlarmService
import java.util.*

class StopAlarmActivity: AppCompatActivity() {

    private lateinit var binding: StopAlarmActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = StopAlarmActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val calendar = GregorianCalendar()
        val time = intent.getLongExtra("alarm_time", calendar.timeInMillis)
        calendar.timeInMillis = time

        binding.hours.text = calendar.get(Calendar.HOUR_OF_DAY).toString()
        binding.minutes.text = calendar.get(Calendar.MINUTE).toString()

        binding.stopAlarm.setOnClickListener {
            applicationContext.stopService(Intent(applicationContext, AlarmService::class.java))

            val intent = Intent(this, AppActivity::class.java)
            startActivity(intent)
            finish()
        }

        Thread {
            val alarmClockTime = 60000L
            Thread.sleep(alarmClockTime)
            applicationContext.stopService(Intent(applicationContext, AlarmService::class.java))
            finish()
        }.start()
    }
}