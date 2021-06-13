package com.srgnk.simplealarmclock.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface AlarmView: MvpView {

    @AddToEndSingle
    fun closeScreen()

    @Skip
    fun showMessage(message: Int)
}