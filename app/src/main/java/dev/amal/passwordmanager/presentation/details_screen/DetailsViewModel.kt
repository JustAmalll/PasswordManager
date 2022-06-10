package dev.amal.passwordmanager.presentation.details_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.amal.passwordmanager.core.domain.models.Password
import dev.amal.passwordmanager.utils.Constants.DETAILS_ARGUMENT_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedItem: MutableStateFlow<Password?> = MutableStateFlow(null)
    val selectedItem: StateFlow<Password?> = _selectedItem

//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            val itemId = savedStateHandle.get<Int>(DETAILS_ARGUMENT_KEY)
//            itemId?.let {
//                repository.getSelectedItem(itemId = itemId).collect { item ->
//                    _selectedItem.value = item
//                }
//            }
//        }
//    }
}