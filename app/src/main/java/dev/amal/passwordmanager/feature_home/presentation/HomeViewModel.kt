package dev.amal.passwordmanager.feature_home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.amal.passwordmanager.feature_add_password.domain.use_case.PasswordUseCases
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val passwordUseCases: PasswordUseCases
) : ViewModel() {
    val passwords = passwordUseCases.getPasswordsUseCase().cachedIn(viewModelScope)
}