package com.srgnk.simplealarmclock.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.srgnk.simplealarmclock.databinding.AlarmBinding
import com.srgnk.simplealarmclock.mvp.model.Alarm
import java.util.*

class RecyclerAdapter(
    private var values: MutableList<Alarm>,
    private val itemClickListener: RecyclerViewClickListener
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private val calendar = GregorianCalendar()

    interface RecyclerViewClickListener {
        fun recyclerViewClickListener(view: View, alarmId: Long)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val itemBinding = AlarmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding, itemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        calendar.timeInMillis = values[position].time
        val hours = calendar.get(Calendar.HOUR_OF_DAY)
        val minutes = calendar.get(Calendar.MINUTE)
        holder.hours.text = if (hours < 10) "0$hours" else "$hours"
        holder.minutes.text = if (minutes < 10) "0$minutes" else "$minutes"
        holder.turnAlarm.isChecked = values[position].isActive
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(binding: AlarmBinding, private val listener: RecyclerViewClickListener) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        var hours = binding.hours
        var minutes = binding.minutes
        var turnAlarm = binding.turnAlarm

        init {
            binding.alarm.setOnClickListener(this)
            binding.turnAlarm.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            listener.recyclerViewClickListener(view, values[adapterPosition].id)
        }
    }

}