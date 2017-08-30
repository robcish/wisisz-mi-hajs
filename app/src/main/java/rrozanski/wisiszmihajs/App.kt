package rrozanski.wisiszmihajs

import android.app.Application
import rrozanski.wisiszmihajs.di.DI

/**
 * Created by Robert Różański on 27.08.2017.
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DI.di().initAppComponent(this)
    }
}
