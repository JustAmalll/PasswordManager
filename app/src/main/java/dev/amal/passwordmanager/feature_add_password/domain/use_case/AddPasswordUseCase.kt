package dev.amal.passwordmanager.feature_add_password.domain.use_case

import dev.amal.passwordmanager.R
import dev.amal.passwordmanager.core.utils.Resource
import dev.amal.passwordmanager.core.utils.SimpleResource
import dev.amal.passwordmanager.core.utils.UiText
import dev.amal.passwordmanager.feature_add_password.domain.repository.AddPasswordRepository

class AddPasswordUseCase(
    private val repository: AddPasswordRepository
) {

    suspend operator fun invoke(
        title: String, email: String, password: String, website: String
    ): SimpleResource {
        if (title.isBlank()) {
            return Resource.Error(
                uiText = UiText.StringResource(R.string.error_title_blank)
            )
        }
        if (email.isBlank()) {
            return Resource.Error(
                uiText = UiText.StringResource(R.string.error_email_blank)
            )
        }
        if (password.isBlank()) {
            return Resource.Error(
                uiText = UiText.StringResource(R.string.error_password_blank)
            )
        }
        if (website.isBlank()) {
            return Resource.Error(
                uiText = UiText.StringResource(R.string.error_website_blank)
            )
        }
        return repository.addPassword(title, email, password, website)
    }
}
