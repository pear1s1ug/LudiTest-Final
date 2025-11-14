package cl.duoc.luditest_final

import android.app.Application

class LudiTestApplication : Application() {

    companion object {
        lateinit var appContext: Application
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}