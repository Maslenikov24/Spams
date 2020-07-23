package com.univer.mvvm_coroutines_toothpick_room.di

import android.content.Context
import com.univer.mvvm_coroutines_toothpick_room.di.provider.server.OkHttpClientProvider
import okhttp3.OkHttpClient
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.module

fun appModule(context: Context) = module {
    bind(Context::class.java).toInstance(context)
    bind(OkHttpClient::class.java).toProvider(OkHttpClientProvider::class.java).singleton()
}