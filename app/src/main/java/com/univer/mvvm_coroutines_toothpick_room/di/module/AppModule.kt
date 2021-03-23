package com.univer.mvvm_coroutines_toothpick_room.di.module

import android.content.Context
import com.univer.mvvm_coroutines_toothpick_room.core.ToolbarListener
import com.univer.mvvm_coroutines_toothpick_room.di.ApiPath
import com.univer.mvvm_coroutines_toothpick_room.di.provider.server.ApiPathProvider
import com.univer.mvvm_coroutines_toothpick_room.di.provider.server.MoshiProvider
import com.univer.mvvm_coroutines_toothpick_room.di.provider.server.OkHttpClientProvider
import com.univer.mvvm_coroutines_toothpick_room.di.provider.server.RetrofitProvider
import com.univer.mvvm_coroutines_toothpick_room.di.provider.service.SearchServiceProvider
import com.univer.mvvm_coroutines_toothpick_room.model.recent.repository.RecentRepository
import com.univer.mvvm_coroutines_toothpick_room.model.recent.repository.RecentRepositoryImpl
import com.univer.mvvm_coroutines_toothpick_room.model.search.net.service.SearchService
import com.univer.mvvm_coroutines_toothpick_room.model.search.repository.SearchRepository
import com.univer.mvvm_coroutines_toothpick_room.model.search.repository.SearchRepositoryImpl
import com.univer.mvvm_coroutines_toothpick_room.presentation.recent.RecentInteractor
import com.univer.mvvm_coroutines_toothpick_room.presentation.recent.RecentInteractorImpl
import com.univer.mvvm_coroutines_toothpick_room.presentation.search.SearchInteractor
import com.univer.mvvm_coroutines_toothpick_room.presentation.search.SearchInteractorImpl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.module

fun appModule(context: Context) = module {
    bind<Context>().toInstance(context)
    //bind<ContactsProvider>().toClass<ContactsProviderImpl>()

    bind<Retrofit>().toProvider(RetrofitProvider::class).providesSingleton()
    bind<OkHttpClient>().toProviderInstance(OkHttpClientProvider()).providesSingleton()
    bind<MoshiConverterFactory>().toProviderInstance(MoshiProvider()).providesSingleton()
    bind<String>().withName(ApiPath::class).toProviderInstance(ApiPathProvider()).providesSingleton()

    bind<SearchService>().toProvider(SearchServiceProvider::class).providesSingleton()

    bind<SearchInteractor>().toClass<SearchInteractorImpl>()
    bind<SearchRepository>().toClass<SearchRepositoryImpl>()
    bind<RecentInteractor>().toClass<RecentInteractorImpl>()
    bind<RecentRepository>().toClass<RecentRepositoryImpl>()

    bind<ToolbarListener>().singleton()
}