package com.univer.mvvm_coroutines_toothpick_room.di.module

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.univer.mvvm_coroutines_toothpick_room.di.ApiPath
import com.univer.mvvm_coroutines_toothpick_room.di.provider.server.ApiPathProvider
import com.univer.mvvm_coroutines_toothpick_room.di.provider.server.MoshiProvider
import com.univer.mvvm_coroutines_toothpick_room.di.provider.server.OkHttpClientProvider
import com.univer.mvvm_coroutines_toothpick_room.di.provider.service.SearchServiceProvider
import com.univer.mvvm_coroutines_toothpick_room.model.search.net.service.SearchService
import okhttp3.OkHttpClient
import retrofit2.converter.moshi.MoshiConverterFactory
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.module

fun appModule(context: Context) = module {
    bind<Context>().toInstance(context)

    bind<OkHttpClient>().toProviderInstance(OkHttpClientProvider()).providesSingleton()
    bind<MoshiConverterFactory>().toProviderInstance(MoshiProvider()).providesSingleton()
    bind<String>().withName(ApiPath::class).toProviderInstance(ApiPathProvider()).providesSingleton()

    bind<SearchService>().toProvider(SearchServiceProvider::class).providesSingleton()
}