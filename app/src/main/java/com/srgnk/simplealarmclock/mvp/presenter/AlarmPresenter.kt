package com.srgnk.simplealarmclock.mvp.presenter

import android.util.Log
import com.srgnk.simplealarmclock.R
import com.srgnk.simplealarmclock.mvp.model.Alarm
import com.srgnk.simplealarmclock.mvp.model.AlarmDatabase
import com.srgnk.simplealarmclock.mvp.view.AlarmView
import com.srgnk.simplealarmclock.utils.AlarmUtils
import kotlinx.coroutines.*
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope
import java.util.*

@InjectViewState
class AlarmPresenter(
    private val alarmUtils: AlarmUtils,
    private val db: AlarmDatabase
) : MvpPresenter<AlarmView>() {

    private var id: Long = -1L

    fun viewIsReady(alarmId: Long) {
        id = alarmId
        if (isNotNewAlarm()) {
            presenterScope.launch {
                val alarm = withContext(Dispatchers.IO) {
                    db.alarmDao().getAlarmById(id)
                }
                withContext(Dispatchers.Main) {
                    val calendar = GregorianCalendar().also { it.timeInMillis = alarm.time }
                    viewState.setHours(calendar.get(Calendar.HOUR_OF_DAY))
                    viewState.setMinutes(calendar.get(Calendar.MINUTE))
                }
            }
        } else {
            val calendar = Calendar.getInstance(Locale.getDefault())
            viewState.setHours(calendar.get(Calendar.HOUR_OF_DAY))
            viewState.setMinutes(calendar.get(Calendar.MINUTE))
        }
    }

    fun clickedSaveAlarm(calendar: Calendar) {
        if (calendar.timeInMillis < System.currentTimeMillis()) {
            calendar.add(Calendar.HOUR_OF_DAY, 24)
        }

        val alarm = Alarm(calendar.timeInMillis, true)
        saveAlarm(alarm)

        viewState.showMessage(R.string.alarm_saved)
        viewState.closeScreen()
    }

    private fun saveAlarm(alarm: Alarm) {
        presenterScope.launch {
            id = withContext(Dispatchers.IO) {
                if (isNotNewAlarm())
                    alarm.id = id

                db.alarmDao().insert(alarm)
            }
            withContext(Dispatchers.Main) {
                alarm.id = id
                alarmUtils.setAlarmOn(alarm)
            }
        }
    }

    fun clickedDeleteAlarm() {
        deleteAlarm(id)

        alarmUtils.setAlarmOff(id)
        viewState.showMessage(R.string.alarm_deleted)
        viewState.closeScreen()
    }

    private fun deleteAlarm(id: Long) {
        presenterScope.launch {
            withContext(Dispatchers.IO) {
                db.alarmDao().deleteById(id)
            }
        }
    }

    private fun isNotNewAlarm() = id != -1L
}