package com.srgnk.simplealarmclock.mvp.view

import com.srgnk.simplealarmclock.mvp.model.Alarm
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

@StateStrategyType(AddToEndSingleStrategy::class)
interface AlarmView: MvpView {

    fun setHours(hours: Int)

    fun setMinutes(minutes: Int)

    fun closeScreen()

    @Skip
    fun showMessage(message: Int)
}