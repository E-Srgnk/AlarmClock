package com.srgnk.simplealarmclock.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.srgnk.simplealarmclock.service.AlarmService
import com.srgnk.simplealarmclock.ui.activity.StopAlarmActivity
import java.util.*


class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val intentStopAlarm = Intent(context, StopAlarmActivity::class.java)
        intentStopAlarm.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intentStopAlarm.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intentStopAlarm.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val time = intent?.getLongExtra("alarm_time", 0L) ?: 0L
        intentStopAlarm.putExtra("alarm_time", time)

        context?.startActivity(intentStopAlarm)
        context?.startService(Intent(context, AlarmService::class.java))
    }
}