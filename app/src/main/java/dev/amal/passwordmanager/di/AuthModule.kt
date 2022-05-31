package dev.amal.passwordmanager.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.amal.passwordmanager.feature_auth.data.remote.AuthApi
import dev.amal.passwordmanager.feature_auth.data.repository.AuthRepositoryImpl
import dev.amal.passwordmanager.feature_auth.domain.repository.AuthRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthModule {

    @Provides
    @Singleton
    fun provideAuthApi(client: OkHttpClient): AuthApi = Retrofit.Builder()
        .baseUrl("http://192.168.1.13:8080/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi, prefs: SharedPreferences): AuthRepository =
        AuthRepositoryImpl(api, prefs)

}