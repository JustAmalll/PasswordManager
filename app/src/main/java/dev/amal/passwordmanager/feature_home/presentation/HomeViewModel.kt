package dev.amal.passwordmanager.feature_home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.amal.passwordmanager.feature_add_password.domain.use_case.AddPasswordUseCases
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val passwordUseCases: AddPasswordUseCases
) : ViewModel() {
    val passwords = passwordUseCases.getPasswordsUseCase().cachedIn(viewModelScope)
}