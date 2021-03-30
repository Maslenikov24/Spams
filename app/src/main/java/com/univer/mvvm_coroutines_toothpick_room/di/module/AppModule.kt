package com.univer.mvvm_coroutines_toothpick_room.di.module

import android.content.Context
import com.univer.mvvm_coroutines_toothpick_room.core.ToolbarListener
import com.univer.mvvm_coroutines_toothpick_room.di.ApiPath
import com.univer.mvvm_coroutines_toothpick_room.di.Default
import com.univer.mvvm_coroutines_toothpick_room.di.IO
import com.univer.mvvm_coroutines_toothpick_room.di.Main
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
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.module

fun appModule(context: Context) = module {
    bind<Context>().toInstance(context)
    //bind<ContactsProvider>().toClass<ContactsProviderImpl>()

    bind<CoroutineDispatcher>().withName(IO::class).toInstance(Dispatchers.IO)
    bind<CoroutineDispatcher>().withName(Main::class).toInstance(Dispatchers.Main.immediate)
    bind<CoroutineDispatcher>().withName(Default::class).toInstance(Dispatchers.Default)

    bind<Retrofit>().toProvider(RetrofitProvider::class).providesSingleton()
    bind<OkHttpClient>().toProviderInstance(OkHttpClientProvider()).providesSingleton()
    bind<MoshiConverterFactory>().toProviderInstance(MoshiProvider()).providesSingleton()
    bind<String>().withName(ApiPath::class).toProviderInstance(ApiPathProvider()).providesSingleton()

    bind<SearchService>().toProvider(SearchServiceProvider::class).providesSingleton()

    bind<SearchRepository>().toClass<SearchRepositoryImpl>()
    bind<RecentRepository>().toClass<RecentRepositoryImpl>()

    bind<ToolbarListener>().singleton()
}