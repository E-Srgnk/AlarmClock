package com.srgnk.simplealarmclock.di

import androidx.room.Room
import com.srgnk.simplealarmclock.mvp.model.AlarmDatabase
import com.srgnk.simplealarmclock.mvp.presenter.AlarmPresenter
import com.srgnk.simplealarmclock.mvp.presenter.MainPresenter
import com.srgnk.simplealarmclock.utils.AlarmUtils
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val applicationModule = module {

    // database
    single {
        Room.databaseBuilder(
            androidContext(), AlarmDatabase::class.java, "alarm"
        ).build()
    }

    // AlarmUtils
    factory {
        AlarmUtils(androidContext())
    }

    // AlarmPresenter
    factory {
        AlarmPresenter(get(), get())
    }

    // MainPresenter
    factory {
        MainPresenter(get(), get())
    }
}