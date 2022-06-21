package dev.amal.passwordmanager.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.amal.passwordmanager.feature_add_card.data.remote.CardApi
import dev.amal.passwordmanager.feature_add_card.data.repository.CardRepositoryImpl
import dev.amal.passwordmanager.feature_add_card.domain.repository.CardRepository
import dev.amal.passwordmanager.feature_add_card.domain.use_cases.AddCardUseCase
import dev.amal.passwordmanager.feature_add_card.domain.use_cases.CardUseCases
import dev.amal.passwordmanager.feature_add_password.data.remote.PasswordApi
import dev.amal.passwordmanager.feature_add_password.data.repository.PasswordRepositoryImpl
import dev.amal.passwordmanager.feature_add_password.domain.repository.PasswordRepository
import dev.amal.passwordmanager.feature_add_password.domain.use_cases.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AddPasswordModule {

    @Provides
    @Singleton
    fun providePasswordApi(client: OkHttpClient): PasswordApi = Retrofit.Builder()
        .baseUrl("http://192.168.0.77:8080")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PasswordApi::class.java)

    @Provides
    @Singleton
    fun providePasswordRepository(api: PasswordApi): PasswordRepository =
        PasswordRepositoryImpl(api)

    @Provides
    @Singleton
    fun providePasswordUseCases(
        repository: PasswordRepository
    ): PasswordUseCases = PasswordUseCases(
        getPasswordsUseCase = GetPasswordsUseCase(repository),
        addPasswordUseCase = AddPasswordUseCase(repository),
        getPasswordDetailsUseCase = GetPasswordDetailsUseCase(repository),
        searchPasswordUseCase = SearchPasswordUseCase(repository)
    )

    @Provides
    @Singleton
    fun provideCardApi(client: OkHttpClient): CardApi = Retrofit.Builder()
        .baseUrl("http://192.168.0.77:8080")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CardApi::class.java)

    @Provides
    @Singleton
    fun provideCardRepository(api: CardApi): CardRepository =
        CardRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideCardsUseCases(
        repository: CardRepository
    ): CardUseCases = CardUseCases(
        addCardUseCase = AddCardUseCase(repository),
    )
}