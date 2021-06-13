package com.srgnk.simplealarmclock.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.srgnk.simplealarmclock.databinding.FragmentAlarmBinding
import com.srgnk.simplealarmclock.mvp.model.Alarm
import com.srgnk.simplealarmclock.mvp.model.AlarmDatabase
import com.srgnk.simplealarmclock.mvp.presenter.AlarmPresenter
import com.srgnk.simplealarmclock.mvp.view.AlarmView
import com.srgnk.simplealarmclock.ui.activity.AppActivity
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class AlarmScreen(private var alarm: Alarm? = null) : MvpAppCompatFragment(), AlarmView {

    private var alarmBinding: FragmentAlarmBinding? = null
    private val binding get() = alarmBinding!!

    @InjectPresenter
    lateinit var presenter: AlarmPresenter
    @ProvidePresenter
    fun providePresenter() = AlarmPresenter(alarm, Room.databaseBuilder(requireContext(), AlarmDatabase::class.java, "alarm").build())

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
            presenter.clickedSaveAlarm(binding.hourPicker.value, binding.minutePicker.value)
        }

        binding.deleteAlarm.setOnClickListener {
            presenter.clickedDeleteAlarm()
        }
    }

    override fun closeScreen() {
        findNavController().popBackStack()
    }

    override fun showMessage(message: Int) {
        Toast.makeText(context, getString(message), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        alarmBinding = null
    }
}