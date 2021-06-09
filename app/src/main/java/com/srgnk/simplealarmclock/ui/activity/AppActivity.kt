package com.srgnk.simplealarmclock.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.srgnk.simplealarmclock.R
import com.srgnk.simplealarmclock.mvp.view.AppView

class AppActivity : AppCompatActivity(), AppView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
    }
}