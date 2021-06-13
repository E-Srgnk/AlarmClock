package com.srgnk.simplealarmclock.di

import androidx.room.Room
import com.srgnk.simplealarmclock.mvp.model.AlarmDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val applicationModule = module {
    single { Room.databaseBuilder(androidContext(), AlarmDatabase::class.java, "alarm").build() }
}