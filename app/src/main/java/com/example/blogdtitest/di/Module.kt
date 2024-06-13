package com.example.blogdtitest.di

import android.app.Application
import android.content.SharedPreferences
import com.example.blogdtitest.repository.BlogRepository
import com.example.blogdtitest.service.BlogService
import com.example.blogdtitest.viewmodel.MainActivityViewModel
import com.example.blogdtitest.viewmodel.NewPostViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val netModule = module {
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }
    fun provideHttpClient(cache: Cache): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .cache(cache)

        return okHttpClientBuilder.build()
    }

    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    fun provideGson(): Gson = GsonBuilder().create()

    single { provideCache(androidApplication()) }
    single { provideHttpClient(get()) }
    single { provideGson() }
    single { provideRetrofit(get(), get()) }
}

val apiModule = module {
    fun provideCountryApi(retrofit: Retrofit) : BlogService{
        return retrofit.create(BlogService::class.java)
    }

    single { provideCountryApi(get()) }
}

val viewModelModule = module {
    viewModel<MainActivityViewModel>()
    viewModel<NewPostViewModel>()
}

val repositoryModule = module {
    fun provideUserRepository(api: BlogService, sharedPreferences: SharedPreferences): BlogRepository {
        return BlogRepository(api, sharedPreferences)
    }
    single { provideUserRepository(get(), get()) }
}


val appModule = module {

    single{
        getSharedPrefs(androidApplication())
    }

}
fun getSharedPrefs(androidApplication: Application): SharedPreferences{
    return  androidApplication.getSharedPreferences("default",  android.content.Context.MODE_PRIVATE)
}