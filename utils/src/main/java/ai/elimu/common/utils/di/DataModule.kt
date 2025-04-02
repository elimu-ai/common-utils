package ai.elimu.common.utils.di

import ai.elimu.common.utils.data.repository.DefaultLanguageProviderImpl
import ai.elimu.common.utils.data.repository.TextToSpeechRepository
import ai.elimu.common.utils.data.repository.TextToSpeechRepositoryImpl
import ai.elimu.common.utils.data.repository.language.LanguageProvider
import ai.elimu.common.utils.data.repository.local.LocalTextToSpeechDataSource
import ai.elimu.common.utils.data.repository.local.LocalTextToSpeechDataSourceImpl
import ai.elimu.common.utils.di.StringKey.LANGUAGE_CUSTOM
import ai.elimu.common.utils.di.StringKey.LANGUAGE_DEFAULT
import android.content.Context
import android.speech.tts.TextToSpeech
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import java.util.Optional

// Tells Dagger this is a Dagger module
@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindTTSRepository(repo: TextToSpeechRepositoryImpl): TextToSpeechRepository

    @Binds
    abstract fun bindLocalTTSDataSource(dataSource: LocalTextToSpeechDataSourceImpl): LocalTextToSpeechDataSource
}

object StringKey {
    const val LANGUAGE_DEFAULT = "defaultLanguage"
    const val LANGUAGE_CUSTOM = "customLanguage"
}

@Module
@InstallIn(SingletonComponent::class)
internal object TextToSpeechModule {

    @StringKey(LANGUAGE_DEFAULT)
    @Provides
    @IntoMap
    fun providesOptionalLanguageProvider(): Optional<LanguageProvider> = Optional.empty()

    @Provides
    fun providesLanguageProvider(providers: Map<String, @JvmSuppressWildcards Optional<LanguageProvider>>): LanguageProvider {
        val defaultImpl = DefaultLanguageProviderImpl()
        return providers[LANGUAGE_CUSTOM]?.orElse(defaultImpl) ?: defaultImpl
    }

    @Provides
    fun providesTextToSpeech(
        @ApplicationContext context: Context,
        languageProvider: LanguageProvider
    ): TextToSpeech =
        TextToSpeechWrapper(
            context,
            languageProvider.getLanguage(),
            languageProvider.getContentProviderId()
        ).tts
}
