package com.heligate.codebase.di.module

import android.content.Context
import android.util.Log
import com.heligate.codebase.BuildConfig
import com.heligate.codebase.data.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class ApplicationModule {
    private val BASE_URL = BuildConfig.BASE_URL
    private val cacheSize: Long = 10 * 1024 * 1024
    private val cacheTimeSec = 30
    private val timeout: Long = 60


    private val cacheInterceptor: Interceptor
        get() = Interceptor {
            val response = it.proceed(it.request())
            val cacheControl = CacheControl.Builder()
                .maxAge(cacheTimeSec, TimeUnit.SECONDS)
                .build()

            response.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build()
        }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    internal fun provideRetrofitService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.d("Retrofit", message) })
        interceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return interceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(context: Context, loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val cache = Cache(File(context.cacheDir, "http-cache"), cacheSize)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(cacheInterceptor)
            .cache(cache)
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .build()
    }
}
