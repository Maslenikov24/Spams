package com.graduate.spams.di.module

import android.content.Context
import com.graduate.spams.model.preferences.app.AppPreferenceStorage
import com.graduate.spams.model.preferences.app.AppPreferenceStorageImpl
import com.graduate.spams.di.*
import com.graduate.spams.di.provider.server.*
import com.graduate.spams.di.provider.service.AuthServiceProvider
import com.graduate.spams.di.provider.service.SearchServiceProvider
import com.graduate.spams.model.auth.net.service.AuthService
import com.graduate.spams.model.detail.repository.DeatilRepositoryImpl
import com.graduate.spams.model.detail.repository.DetailRepository
import com.graduate.spams.model.manage.ManageRepository
import com.graduate.spams.model.manage.ManageRepositoryImpl
import com.graduate.spams.model.notification.NotificationRepository
import com.graduate.spams.model.notification.NotificationRepositoryImpl
import com.graduate.spams.model.preferences.call.CallPreferenceStorage
import com.graduate.spams.model.preferences.call.CallPreferenceStorageImpl
import com.graduate.spams.model.recent.repository.RecentRepository
import com.graduate.spams.model.recent.repository.RecentRepositoryImpl
import com.graduate.spams.model.search.net.service.SearchService
import com.graduate.spams.model.search.repository.SearchRepository
import com.graduate.spams.model.search.repository.SearchRepositoryImpl
import com.graduate.spams.presentation.search.SearchInteractor
import com.graduate.spams.presentation.search.SearchInteractorImpl
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
    bind<AuthService>().toProvider(AuthServiceProvider::class).providesSingleton()
    bind<SearchService>().toProvider(SearchServiceProvider::class).providesSingleton()

    // Model
    bind<SearchInteractor>().toClass<SearchInteractorImpl>()
    bind<SearchRepository>().toClass<SearchRepositoryImpl>()
    bind<RecentRepository>().toClass<RecentRepositoryImpl>()
    bind<DetailRepository>().toClass<DeatilRepositoryImpl>()
    bind<ManageRepository>().toClass<ManageRepositoryImpl>()
    bind<NotificationRepository>().toClass<NotificationRepositoryImpl>()

    bind<String>().withName(MacAddress::class).toProvider(MacAddressProvider::class).providesSingleton()

}