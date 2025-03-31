package ai.elimu.common.utils.di

import ai.elimu.common.utils.data.repository.TextToSpeechRepository
import ai.elimu.common.utils.data.repository.TextToSpeechRepositoryImpl
import ai.elimu.common.utils.data.repository.language.LanguageProvider
import ai.elimu.common.utils.data.repository.local.LocalTextToSpeechDataSource
import ai.elimu.common.utils.data.repository.local.LocalTextToSpeechDataSourceImpl
import android.content.Context
import android.speech.tts.TextToSpeech
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

// Tells Dagger this is a Dagger module
@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindTTSRepository(repo: TextToSpeechRepositoryImpl): TextToSpeechRepository

    @Binds
    abstract fun bindLocalTTSDataSource(dataSource: LocalTextToSpeechDataSourceImpl): LocalTextToSpeechDataSource
}

@Module
@InstallIn(SingletonComponent::class)
internal object TextToSpeechModule {

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
