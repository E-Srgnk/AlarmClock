package com.srgnk.simplealarmclock

import android.app.Application
import com.srgnk.simplealarmclock.di.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AlarmClockApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AlarmClockApp)
            modules(
                listOf(applicationModule)
            )
        }
    }
}