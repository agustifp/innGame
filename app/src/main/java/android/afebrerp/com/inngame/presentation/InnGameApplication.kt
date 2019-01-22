package android.afebrerp.com.inngame.presentation

import android.afebrerp.com.inngame.presentation.koininjection.generalModules
import android.app.Application
import org.koin.android.ext.android.startKoin
import org.koin.standalone.KoinComponent

class InnGameApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, generalModules)
    }

}
