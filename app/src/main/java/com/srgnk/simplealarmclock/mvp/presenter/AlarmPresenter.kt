package com.srgnk.simplealarmclock.mvp.presenter

import com.srgnk.simplealarmclock.R
import com.srgnk.simplealarmclock.mvp.model.Alarm
import com.srgnk.simplealarmclock.mvp.model.AlarmDatabase
import com.srgnk.simplealarmclock.mvp.view.AlarmView
import kotlinx.coroutines.*
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class AlarmPresenter(private var alarm: Alarm?, private val db: AlarmDatabase): MvpPresenter<AlarmView>() {

    fun clickedSaveAlarm(hours: Int, minutes: Int) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                alarm = Alarm(hours, minutes)
                val alarmId = db.alarmDao().insert(alarm!!)
                alarm?.id = alarmId
            }
        }
        viewState.showMessage(R.string.alarm_saved)
    }

    fun clickedDeleteAlarm() {
        alarm?.let {
            GlobalScope.launch {
                withContext(Dispatchers.IO) {
                    db.alarmDao().delete(it)
                }
            }
        }
        viewState.showMessage(R.string.alarm_deleted)
        viewState.closeScreen()
    }
}