package dev.amal.passwordmanager.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.amal.passwordmanager.domain.use_cases.UseCases
import dev.amal.passwordmanager.domain.use_cases.validation.ValidateEmail
import dev.amal.passwordmanager.domain.use_cases.validation.ValidatePassword
import dev.amal.passwordmanager.domain.use_cases.validation.ValidateTitle
import dev.amal.passwordmanager.domain.use_cases.validation.ValidateWebsite
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUseCases(): UseCases = UseCases(
        validateTitle = ValidateTitle(),
        validateEmail = ValidateEmail(),
        validatePassword = ValidatePassword(),
        validateWebsite = ValidateWebsite()
    )
}