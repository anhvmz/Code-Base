package com.heligate.codebase.di

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass
import kotlin.annotation.Target
import kotlin.annotation.MustBeDocumented

@MustBeDocumented
@MapKey
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKey(val value: KClass<out ViewModel>)