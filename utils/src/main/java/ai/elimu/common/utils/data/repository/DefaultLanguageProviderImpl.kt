package ai.elimu.common.utils.data.repository

import ai.elimu.common.utils.data.repository.language.LanguageProvider

class DefaultLanguageProviderImpl: LanguageProvider {
    override fun getLanguage(): String {
        return ""
    }

    override fun getContentProviderId(): String {
        return "ai.elimu.content_provider"
    }
}