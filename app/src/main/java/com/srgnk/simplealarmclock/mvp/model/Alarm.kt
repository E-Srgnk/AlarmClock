package com.srgnk.simplealarmclock.mvp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Alarm(
    var hour: Int,
    var minute: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}