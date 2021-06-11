package com.srgnk.simplealarmclock.ui.fragment

import android.os.Bundle
import android.view.View
import com.srgnk.simplealarmclock.R
import com.srgnk.simplealarmclock.databinding.FragmentAlarmBinding
import com.srgnk.simplealarmclock.mvp.view.AlarmView
import com.srgnk.simplealarmclock.ui.activity.AppActivity
import moxy.MvpAppCompatFragment

class AlarmScreen: MvpAppCompatFragment(R.layout.fragment_alarm), AlarmView {

    private var alarmBinding: FragmentAlarmBinding? = null
    private val binding get() = alarmBinding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        alarmBinding = FragmentAlarmBinding.bind(view)

        (activity as AppActivity).setSupportActionBar(binding.toolbar)
        (activity as AppActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (activity as AppActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.hourPicker.maxValue = 23
        binding.hourPicker.minValue = 0

        binding.minutePicker.maxValue = 59
        binding.minutePicker.minValue = 0

    }
}