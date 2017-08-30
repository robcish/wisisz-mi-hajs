package rrozanski.wisiszmihajs.di

import rrozanski.wisiszmihajs.App
import rrozanski.wisiszmihajs.MainActivity

/**
* Created by Robert Różański on 27.08.2017.
*/


class DI {

    private val injector = Injector()
    private var appComponent: AppComponent? = null

    fun injectors(): Injector {
        return injector
    }

    fun initAppComponent(app: App) {
        appComponent = DaggerAppComponent.builder().contextModule(ContextModule(app)).build()
    }

    fun obtainAppComponent(): AppComponent? {
        return appComponent
    }


    inner class Injector {

        fun inject(activity: MainActivity) {
            obtainAppComponent()?.inject(activity)
        }
    }

    companion object {

        val di = DI()

        fun di(): DI {
            return di
        }
    }
}
