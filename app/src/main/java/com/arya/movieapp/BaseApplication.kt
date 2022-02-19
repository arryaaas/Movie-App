package com.arya.movieapp

import android.app.Application
import com.arya.movieapp.core.di.databaseModule
import com.arya.movieapp.core.di.networkModule
import com.arya.movieapp.core.di.repositoryModule
import com.arya.movieapp.di.useCaseModule
import com.arya.movieapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}