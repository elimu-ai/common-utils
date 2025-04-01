package ai.elimu.common.utils.viewmodel

import ai.elimu.common.utils.data.model.tts.QueueMode
import ai.elimu.common.utils.data.repository.TextToSpeechRepository
import ai.elimu.common.utils.di.IoScope
import android.speech.tts.UtteranceProgressListener
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class TextToSpeechViewModelImpl @Inject constructor(
    @IoScope private val ioScope: CoroutineScope,
    private val textToSpeechRepository: TextToSpeechRepository
): ViewModel(), TextToSpeechViewModel {


    /**
     * Warning: Do not use this function directly to set TTS language. Instead, to set custom TTS
     * language for your app, implement {@link #LanguageProvider} and return the design language's
     * isoCode in {@link #getLanguage()} function. Then, provide the implementation via Hilt Module.
     *
     * Below is an example:
     * ```
     * class LanguageProviderImpl @Inject constructor(): LanguageProvider {
     *     override fun getLanguage(): String {
     *         return ""
     *     }
     *
     *     override fun getContentProviderId(): String {
     *         return BuildConfig.CONTENT_PROVIDER_APPLICATION_ID
     *     }
     * }
     * ....
     *
     * @Module
     * @InstallIn(SingletonComponent::class)
     * object DataModule {
     *
     *     @Provides
     *     fun providesLanguageProvider(): LanguageProvider = LanguageProviderImpl()
     * }
     * ```
     *
     *
     * Speaks the text using the specified queuing strategy and speech parameters, the text may
     * be spanned with TtsSpans.
     * This method is asynchronous, i.e. the method just adds the request to the queue of TTS
     * requests and then returns. The synthesis might not have finished (or even started!) at the
     * time when this method returns. In order to reliably detect errors during synthesis,
     * we recommend setting an utterance progress listener (see
     * {@link #setOnUtteranceProgressListener}) and using the
     * {@link Engine#KEY_PARAM_UTTERANCE_ID} parameter.
     *
     * @param text The string of text to be spoken. No longer than
     *            {@link #getMaxSpeechInputLength()} characters.
     * @param queueMode The queuing strategy to use, {@link #QUEUE_ADD} or {@link #QUEUE_FLUSH}.
     * @param utteranceId An unique identifier for this request.
     *
     * @return {@link #ERROR} or {@link #SUCCESS} of <b>queuing</b> the speak operation.
     */
    override fun speak(text: CharSequence, queueMode: QueueMode, utteranceId: String) {
        ioScope.launch {
            textToSpeechRepository.speak(text, queueMode, utteranceId)
        }
    }

    /**
     * Checks whether the TTS engine is busy speaking. Note that a speech item is considered
     * complete once it's audio data has been sent to the audio mixer, or written to a file.
     * There might be a finite lag between this point, and when the audio hardware completes playback.
     * Returns:
     * true if the TTS engine is speaking.
     */
    override fun isSpeaking(): Boolean {
        return textToSpeechRepository.isSpeaking()
    }

    /**
     * Interrupts the current utterance (whether played or rendered to file) and discards other utterances in the queue.
     * Returns:
     * ERROR or SUCCESS.
     */
    override fun stop() {
        textToSpeechRepository.stop()
    }

    /**
     * Sets the listener that will be notified of various events related to the synthesis of a given utterance. See UtteranceProgressListener and TextToSpeech. Engine. KEY_PARAM_UTTERANCE_ID.
     * Params:
     * listener – the listener to use.
     * Returns:
     * ERROR or SUCCESS
     */
    override fun setOnUtteranceProgressListener(listener: UtteranceProgressListener): Int {
        return textToSpeechRepository.setOnUtteranceProgressListener(listener)
    }

    /**
     * Plays silence for the specified amount of time using the specified
     * queue mode.
     * This method is asynchronous, i.e. the method just adds the request to the queue of TTS
     * requests and then returns. The synthesis might not have finished (or even started!) at the
     * time when this method returns. In order to reliably detect errors during synthesis,
     * we recommend setting an utterance progress listener (see
     * {@link #setOnUtteranceProgressListener}) and using the
     * {@link Engine#KEY_PARAM_UTTERANCE_ID} parameter.
     *
     * @param durationInMs The duration of the silence.
     * @param queueMode {@link #QUEUE_ADD} or {@link #QUEUE_FLUSH}.
     * @param utteranceId An unique identifier for this request.
     *
     * @return {@link #ERROR} or {@link #SUCCESS} of <b>queuing</b> the playSilentUtterance operation.
     */
    override fun playSilentUtterance(
        durationInMs: Long,
        queueMode: Int,
        utteranceId: String?
    ): Int {
        return textToSpeechRepository.playSilentUtterance(durationInMs, queueMode, utteranceId)
    }

    override fun setSpeechRate(speechRate: Float): Int {
        return textToSpeechRepository.setSpeechRate(speechRate)
    }

    override fun setLanguage(loc: Locale?): Int {
        return textToSpeechRepository.setLanguage(loc)
    }
}