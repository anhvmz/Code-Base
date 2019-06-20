package com.heligate.codebase.di.component

import android.app.Application
import com.heligate.codebase.api.ApiModule
import com.heligate.codebase.base.BaseApplication
import com.heligate.codebase.di.builder.ActivityBuilder
import com.heligate.codebase.di.builder.ServiceBuilder
import com.heligate.codebase.di.module.ApplicationModule
import com.heligate.codebase.di.module.ContextModule
import com.heligate.codebase.repository.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ContextModule::class,
        ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        ApiModule::class,
        RepositoryModule::class,
        ActivityBuilder::class,
        ServiceBuilder::class]
)
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    fun inject(application: BaseApplication)


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}