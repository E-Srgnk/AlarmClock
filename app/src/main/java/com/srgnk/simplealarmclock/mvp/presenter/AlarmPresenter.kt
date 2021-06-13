package com.srgnk.simplealarmclock.mvp.presenter

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

    fun clickedSaveAlarm(alarmId: Long, hours: Int, minutes: Int) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                val alarm = Alarm(hours, minutes)
                if (alarmId != -1L)
                    alarm.id = alarmId
                id = db.alarmDao().insert(alarm)
            }
        }
        viewState.showMessage(R.string.alarm_saved)
    }

    fun clickedDeleteAlarm(alarmId: Long) {
        if (alarmWasSaved()) deleteAlarm(id)
        else deleteAlarm(alarmId)

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

    private fun alarmWasSaved() = id != -1L
}