package com.example.core

import android.app.Application
import android.content.Context

class BaseApplication : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        currentApplication = this
    }

    //伴生对象
    companion object {
        @JvmStatic
        @get:JvmName("currentApplication")
        internal lateinit var currentApplication: Context
            private set
//        @JvmStatic
//        fun currentApplication(): Context {
//            return currentApplication
//        }
    }
}