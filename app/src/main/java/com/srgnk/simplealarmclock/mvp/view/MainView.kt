package com.srgnk.simplealarmclock.mvp.view

import com.srgnk.simplealarmclock.mvp.model.Alarm
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView: MvpView {

    fun showAlarms(alarms: MutableList<Alarm>)

    @Skip
    fun openAlarm(alarmId: Long)
}