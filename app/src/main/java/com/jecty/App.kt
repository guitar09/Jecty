package com.jecty

import android.app.Application
import com.dep.jecty.Jecty.startJecty
import com.jecty.di.Module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startJecty {
          module = Module(Session("#ID_67890"))
        }
    }
}
