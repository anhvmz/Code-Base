package com.heligate.codebase.base

import android.content.Context
import com.heligate.codebase.di.component.ApplicationComponent
import com.heligate.codebase.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerApplicationComponent.builder().application(this).build()
        appComponent.inject(this)

        return appComponent
    }

    companion object {
        fun get(context: Context): BaseApplication {
            return context.applicationContext as BaseApplication
        }
    }
}