package dev.amal.passwordmanager.core.utils

import androidx.annotation.StringRes
import dev.amal.passwordmanager.R

sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    data class StringResource(@StringRes val id: Int) : UiText()

    companion object {
        fun unknownError(): UiText = StringResource(R.string.error_unknown)
    }
}
