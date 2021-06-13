package com.srgnk.simplealarmclock.mvp.model

import androidx.room.*

@Dao
interface AlarmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(alarm: Alarm): Long

    @Query("DELETE FROM alarm WHERE id = :alarmId")
    fun deleteById(alarmId: Long)

    @Query("SELECT * FROM alarm")
    fun getAllAlarms(): MutableList<Alarm>
}