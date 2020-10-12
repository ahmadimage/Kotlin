package com.example.fragmenttask

import android.app.Application
import com.example.fragmenttask.modules.NetworkModule
import com.example.fragmenttask.modules.NewsModules
import com.example.fragmenttask.modules.NotifModule
import com.example.fragmenttask.modules.RepositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(NetworkModule + RepositoryModule + NewsModules + NotifModule)
        }
    }
}