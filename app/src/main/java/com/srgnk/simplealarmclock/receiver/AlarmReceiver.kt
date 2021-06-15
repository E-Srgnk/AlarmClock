package com.srgnk.simplealarmclock.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.srgnk.simplealarmclock.service.AlarmService
import com.srgnk.simplealarmclock.ui.activity.StopAlarmActivity


class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val intent2 = Intent(context, StopAlarmActivity::class.java)
        intent2.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        context?.startActivity(intent2)

        context?.startService(Intent(context, AlarmService::class.java))
    }
}