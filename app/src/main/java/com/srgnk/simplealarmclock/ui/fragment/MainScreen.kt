package com.srgnk.simplealarmclock.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.srgnk.simplealarmclock.R
import com.srgnk.simplealarmclock.databinding.FragmentMainBinding
import com.srgnk.simplealarmclock.mvp.view.MainView
import moxy.MvpAppCompatFragment

class MainScreen: MvpAppCompatFragment(), MainView {

    private var mainBinding: FragmentMainBinding? = null
    private val binding get() = mainBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainBinding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addNewAlarm.setOnClickListener {
            findNavController().navigate(R.id.action_MainScreen_to_AlarmScreen)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainBinding = null
    }
}