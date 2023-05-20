package com.innaval.desafiocarrefour

import android.app.Application
import com.innaval.desafiocarrefour.di.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import timber.log.Timber

class CarrefourApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        GlobalContext.startKoin {
            androidContext(this@CarrefourApplication)
            modules(allModules())
        }
    }
}