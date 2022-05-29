package dev.amal.passwordmanager.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.amal.passwordmanager.feature_auth.data.remote.AuthApi
import dev.amal.passwordmanager.feature_auth.domain.repository.AuthRepository
import dev.amal.passwordmanager.feature_auth.data.repository.AuthRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi =
        Retrofit.Builder()
            .baseUrl("http://192.168.1.13:8080/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()

    @Provides
    @Singleton
    fun provideSharedPref(app: Application): SharedPreferences =
        app.getSharedPreferences("prefs", MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi, prefs: SharedPreferences): AuthRepository =
        AuthRepositoryImpl(api, prefs)

}