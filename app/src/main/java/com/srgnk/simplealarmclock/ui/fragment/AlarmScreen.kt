package com.srgnk.simplealarmclock.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.srgnk.simplealarmclock.databinding.FragmentAlarmBinding
import com.srgnk.simplealarmclock.mvp.view.AlarmView
import com.srgnk.simplealarmclock.ui.activity.AppActivity
import moxy.MvpAppCompatFragment

class AlarmScreen : MvpAppCompatFragment(), AlarmView {

    private var alarmBinding: FragmentAlarmBinding? = null
    private val binding get() = alarmBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        alarmBinding = FragmentAlarmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppActivity).setSupportActionBar(binding.toolbar)
        (activity as AppActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (activity as AppActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.hourPicker.maxValue = 23
        binding.hourPicker.minValue = 0

        binding.minutePicker.maxValue = 59
        binding.minutePicker.minValue = 0

        binding.saveAlarm.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.deleteAlarm.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        alarmBinding = null
    }
}