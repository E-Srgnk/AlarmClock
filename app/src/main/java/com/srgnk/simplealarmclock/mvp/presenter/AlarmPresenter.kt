package com.srgnk.simplealarmclock.mvp.presenter

import android.util.Log
import com.srgnk.simplealarmclock.R
import com.srgnk.simplealarmclock.mvp.model.Alarm
import com.srgnk.simplealarmclock.mvp.model.AlarmDatabase
import com.srgnk.simplealarmclock.mvp.view.AlarmView
import kotlinx.coroutines.*
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class AlarmPresenter(private val db: AlarmDatabase) : MvpPresenter<AlarmView>() {

    private var id: Long = -1L

    fun viewIsReady(alarmId: Long) {
        id = alarmId
        if (isNotNewAlarm()) {
            GlobalScope.launch {
                val alarm = withContext(Dispatchers.IO) {
                    db.alarmDao().getAlarmById(id)
                }
                withContext(Dispatchers.Main) {
                    viewState.setHours(alarm.hour)
                    viewState.setMinutes(alarm.minute)
                }
            }
        }
    }

    fun clickedSaveAlarm(hours: Int, minutes: Int) {
        val alarm = Alarm(hours, minutes)
        saveAlarm(alarm)

        viewState.showMessage(R.string.alarm_saved)
    }

    private fun saveAlarm(alarm: Alarm) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                if (isNotNewAlarm())
                    alarm.id = id

                id = db.alarmDao().insert(alarm)
            }
        }
    }

    fun clickedDeleteAlarm() {
        deleteAlarm(id)

        viewState.showMessage(R.string.alarm_deleted)
        viewState.closeScreen()
    }

    private fun deleteAlarm(id: Long) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                db.alarmDao().deleteById(id)
            }
        }
    }

    private fun isNotNewAlarm() = id != -1L
}