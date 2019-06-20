package com.heligate.codebase.di.builder

import com.heligate.codebase.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector()
    abstract fun bindMainActivity(): MainActivity

}