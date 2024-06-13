package com.example.blogdtitest.application

import android.app.Application
import com.example.blogdtitest.di.apiModule
import com.example.blogdtitest.di.appModule
import com.example.blogdtitest.di.netModule
import com.example.blogdtitest.di.repositoryModule
import com.example.blogdtitest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CustomApplication)
            modules(listOf(netModule, apiModule, viewModelModule, repositoryModule, appModule))
        }
    }
}