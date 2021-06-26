package com.srgnk.simplealarmclock.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.srgnk.simplealarmclock.databinding.FragmentAlarmBinding
import com.srgnk.simplealarmclock.mvp.presenter.AlarmPresenter
import com.srgnk.simplealarmclock.mvp.view.AlarmView
import com.srgnk.simplealarmclock.ui.activity.AppActivity
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.koin.android.ext.android.get
import java.util.*

class AlarmScreen : MvpAppCompatFragment(), AlarmView {

    private var alarmBinding: FragmentAlarmBinding? = null
    private val binding get() = alarmBinding!!

    @InjectPresenter
    lateinit var presenter: AlarmPresenter
    @ProvidePresenter
    fun providePresenter(): AlarmPresenter = get()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, binding.hourPicker.value)
            calendar.set(Calendar.MINUTE, binding.minutePicker.value)
            calendar.set(Calendar.SECOND, 0)

            presenter.clickedSaveAlarm(calendar)
        }

        binding.deleteAlarm.setOnClickListener {
            presenter.clickedDeleteAlarm()
        }

        val alarmId = arguments?.getLong("alarmId") ?: -1
        presenter.viewIsReady(alarmId)
    }

    override fun setHours(hours: Int) {
        binding.hourPicker.value = hours
    }

    override fun setMinutes(minutes: Int) {
        binding.minutePicker.value = minutes
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