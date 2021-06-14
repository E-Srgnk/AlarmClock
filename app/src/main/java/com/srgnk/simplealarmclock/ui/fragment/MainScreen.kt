package com.srgnk.simplealarmclock.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.srgnk.simplealarmclock.R
import com.srgnk.simplealarmclock.databinding.FragmentMainBinding
import com.srgnk.simplealarmclock.mvp.model.Alarm
import com.srgnk.simplealarmclock.mvp.presenter.MainPresenter
import com.srgnk.simplealarmclock.mvp.view.MainView
import com.srgnk.simplealarmclock.ui.adapter.RecyclerAdapter
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.koin.android.ext.android.get

class MainScreen : MvpAppCompatFragment(), RecyclerAdapter.RecyclerViewClickListener, MainView {

    private var mainBinding: FragmentMainBinding? = null
    private val binding get() = mainBinding!!

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter = get()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainBinding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addNewAlarm.setOnClickListener {
            val newAlarm = -1L
            findNavController().navigate(
                R.id.action_MainScreen_to_AlarmScreen,
                bundleOf("alarmId" to newAlarm)
            )
        }

        presenter.viewIsReady()
    }

    override fun showAlarms(alarms: MutableList<Alarm>) {
        val adapter = RecyclerAdapter(alarms, this)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
    }

    override fun openAlarm(alarmId: Long) {
        findNavController().navigate(
            R.id.action_MainScreen_to_AlarmScreen,
            bundleOf("alarmId" to alarmId)
        )
    }

    override fun recyclerViewClickListener(alarmId: Long) {
//        openAlarm(alarmId)
        presenter.clickedRecyclerItem(alarmId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainBinding = null
    }
}