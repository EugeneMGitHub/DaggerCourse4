package com.example.daggerlesson4components

import android.app.Application
import android.content.Context
import com.example.daggerlesson4components.component.AppComponent
import com.example.daggerlesson4components.component.DaggerAppComponent

class MainApp : Application() {


    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .contextFun(context = this)
            .appId("AppName")
            .theLink("https://SomeLink.com")
            .build()
    }


}


val Context.appComponent: AppComponent
    get() = when (this) {
        is MainApp -> appComponent
        else -> applicationContext.appComponent
    }
