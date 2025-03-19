package ai.elimu.common.utils.data.repository

import ai.elimu.common.utils.data.model.tts.QueueMode
import ai.elimu.common.utils.data.repository.local.LocalTextToSpeechDataSource
import android.speech.tts.UtteranceProgressListener
import java.util.Locale
import javax.inject.Inject

class TextToSpeechRepositoryImpl @Inject constructor(
    private val localDataSource: LocalTextToSpeechDataSource,
): TextToSpeechRepository {

    override suspend fun speak(text: CharSequence, queueMode: QueueMode, utteranceId: String) {
        localDataSource.speak(text, queueMode, utteranceId)
    }

    override fun isSpeaking(): Boolean {
        return localDataSource.isSpeaking()
    }

    override fun stop() {
        localDataSource.stop()
    }

    override fun setOnUtteranceProgressListener(listener: UtteranceProgressListener): Int {
        return localDataSource.setOnUtteranceProgressListener(listener)
    }

    override fun playSilentUtterance(
        durationInMs: Long,
        queueMode: Int,
        utteranceId: String?
    ): Int {
        return localDataSource.playSilentUtterance(durationInMs, queueMode, utteranceId)
    }

    override fun setSpeechRate(speechRate: Float): Int {
        return localDataSource.setSpeechRate(speechRate)
    }

    override fun setLanguage(loc: Locale?): Int {
        return localDataSource.setLanguage(loc)
    }
}