package com.univer.mvvm_coroutines_toothpick_room.di.module

import android.content.Context
import com.univer.mvvm_coroutines_toothpick_room.model.preferences.app.AppPreferenceStorage
import com.univer.mvvm_coroutines_toothpick_room.model.preferences.app.AppPreferenceStorageImpl
import com.univer.mvvm_coroutines_toothpick_room.di.*
import com.univer.mvvm_coroutines_toothpick_room.di.provider.server.*
import com.univer.mvvm_coroutines_toothpick_room.di.provider.service.SearchServiceProvider
import com.univer.mvvm_coroutines_toothpick_room.model.preferences.call.CallPreferenceStorage
import com.univer.mvvm_coroutines_toothpick_room.model.preferences.call.CallPreferenceStorageImpl
import com.univer.mvvm_coroutines_toothpick_room.model.recent.repository.RecentRepository
import com.univer.mvvm_coroutines_toothpick_room.model.recent.repository.RecentRepositoryImpl
import com.univer.mvvm_coroutines_toothpick_room.model.search.net.service.SearchService
import com.univer.mvvm_coroutines_toothpick_room.model.search.repository.SearchRepository
import com.univer.mvvm_coroutines_toothpick_room.model.search.repository.SearchRepositoryImpl
import com.univer.mvvm_coroutines_toothpick_room.presentation.search.SearchInteractor
import com.univer.mvvm_coroutines_toothpick_room.presentation.search.SearchInteractorImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.module

fun appModule(context: Context) = module {
    bind<Context>().toInstance(context)

    // Coroutines
    bind<CoroutineDispatcher>().withName(IO::class).toInstance(Dispatchers.IO)
    bind<CoroutineDispatcher>().withName(Main::class).toInstance(Dispatchers.Main.immediate)
    bind<CoroutineDispatcher>().withName(Default::class).toInstance(Dispatchers.Default)

    // Provider.server
    bind<Retrofit>().toProvider(RetrofitProvider::class).providesSingleton()
    bind<OkHttpClient>().toProviderInstance(OkHttpClientProvider()).providesSingleton()
    bind<MoshiConverterFactory>().toProviderInstance(MoshiProvider()).providesSingleton()
    bind<String>().withName(ApiPath::class).toProviderInstance(ApiPathProvider()).providesSingleton()
    bind<AppPreferenceStorage>().toClass<AppPreferenceStorageImpl>().singleton()
    bind<CallPreferenceStorage>().toClass<CallPreferenceStorageImpl>().singleton()

    // Provider.service
    bind<SearchService>().toProvider(SearchServiceProvider::class).providesSingleton()

    // Model
    bind<SearchInteractor>().toClass<SearchInteractorImpl>()
    bind<SearchRepository>().toClass<SearchRepositoryImpl>()
    bind<RecentRepository>().toClass<RecentRepositoryImpl>()

}