package com.srgnk.simplealarmclock.mvp.presenter

import com.srgnk.simplealarmclock.mvp.model.AlarmDatabase
import com.srgnk.simplealarmclock.mvp.view.MainView
import com.srgnk.simplealarmclock.utils.AlarmUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPresenter(
    private val alarmUtils: AlarmUtils,
    private val db: AlarmDatabase
) : MvpPresenter<MainView>() {

    fun viewIsReady() {
        GlobalScope.launch {
            val alarms = withContext(Dispatchers.IO) {
                db.alarmDao().getAllAlarms()
            }
            withContext(Dispatchers.Main) {
                viewState.showAlarms(alarms)
            }
        }
    }

    fun clickedRecyclerItem(alarmId: Long) {
        viewState.openAlarm(alarmId)
    }

    fun changeAlarmActivity(alarmId: Long) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                val alarm = db.alarmDao().getAlarmById(alarmId)
                alarm.isActive = !alarm.isActive
                db.alarmDao().insert(alarm)
                when (alarm.isActive) {
                    true -> alarmUtils.setAlarmOn(alarm)
                    false -> alarmUtils.setAlarmOff(alarm.id)
                }
            }
        }
    }

}