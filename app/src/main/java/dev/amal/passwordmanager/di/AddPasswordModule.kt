package dev.amal.passwordmanager.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.amal.passwordmanager.feature_add_password.data.remote.AddPasswordApi
import dev.amal.passwordmanager.feature_add_password.data.repository.AddPasswordRepositoryImpl
import dev.amal.passwordmanager.feature_add_password.domain.repository.AddPasswordRepository
import dev.amal.passwordmanager.feature_add_password.domain.use_case.AddPasswordUseCase
import dev.amal.passwordmanager.feature_add_password.domain.use_case.AddPasswordUseCases
import dev.amal.passwordmanager.feature_add_password.domain.use_case.GetPasswordsUseCase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AddPasswordModule {

    @Provides
    @Singleton
    fun provideAddPasswordApi(client: OkHttpClient): AddPasswordApi = Retrofit.Builder()
        .baseUrl("http://192.168.1.11:8080/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AddPasswordApi::class.java)

    @Provides
    @Singleton
    fun provideAddPasswordRepository(api: AddPasswordApi): AddPasswordRepository =
        AddPasswordRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideUseCases(
        repository: AddPasswordRepository
    ): AddPasswordUseCases = AddPasswordUseCases(
        getPasswordsUseCase = GetPasswordsUseCase(repository),
        addPasswordUseCase = AddPasswordUseCase(repository)
    )
}