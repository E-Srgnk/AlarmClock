package com.srgnk.simplealarmclock.mvp.model

import androidx.room.*

@Dao
interface AlarmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(alarm: Alarm): Long

    @Delete
    fun delete(alarm: Alarm)

    @Query("SELECT * FROM alarm")
    fun getAllAlarms(): MutableList<Alarm>
}