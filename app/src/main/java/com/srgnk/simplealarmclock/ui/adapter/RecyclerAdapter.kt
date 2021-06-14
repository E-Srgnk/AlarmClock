package com.srgnk.simplealarmclock.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.srgnk.simplealarmclock.databinding.AlarmBinding
import com.srgnk.simplealarmclock.mvp.model.Alarm

class RecyclerAdapter(
    private var values: MutableList<Alarm>,
    private val itemClickListener: RecyclerViewClickListener
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    interface RecyclerViewClickListener {
        fun recyclerViewClickListener(alarmId: Long)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val itemBinding = AlarmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding, itemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hours = values[position].hour
        val minutes = values[position].minute
        holder.hours.text = if (hours < 10) "0$hours" else hours.toString()
        holder.minutes.text = if (minutes < 10) "0$minutes" else minutes.toString()
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(binding: AlarmBinding, private val listener: RecyclerViewClickListener) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        var hours = binding.hours
        var minutes = binding.minutes

        init {
            binding.alarm.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            listener.recyclerViewClickListener(values[adapterPosition].id)
        }
    }

}