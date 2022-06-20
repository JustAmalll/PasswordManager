package dev.amal.passwordmanager.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.amal.passwordmanager.feature_add_password.data.remote.PasswordApi
import dev.amal.passwordmanager.feature_add_password.data.repository.PasswordRepositoryImpl
import dev.amal.passwordmanager.feature_add_password.domain.repository.PasswordRepository
import dev.amal.passwordmanager.feature_add_password.domain.use_case.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AddPasswordModule {

    @Provides
    @Singleton
    fun provideAddPasswordApi(client: OkHttpClient): PasswordApi = Retrofit.Builder()
        .baseUrl("http://192.168.0.77:8080")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PasswordApi::class.java)

    @Provides
    @Singleton
    fun provideAddPasswordRepository(api: PasswordApi): PasswordRepository =
        PasswordRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideUseCases(
        repository: PasswordRepository
    ): PasswordUseCases = PasswordUseCases(
        getPasswordsUseCase = GetPasswordsUseCase(repository),
        addPasswordUseCase = AddPasswordUseCase(repository),
        getPasswordDetailsUseCase = GetPasswordDetailsUseCase(repository),
        searchPasswordUseCase = SearchPasswordUseCase(repository)
    )
}