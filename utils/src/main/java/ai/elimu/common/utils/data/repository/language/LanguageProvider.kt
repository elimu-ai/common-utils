package ai.elimu.common.utils.data.repository.language

interface LanguageProvider {
    fun getLanguage(): String
    fun getContentProviderId(): String
}