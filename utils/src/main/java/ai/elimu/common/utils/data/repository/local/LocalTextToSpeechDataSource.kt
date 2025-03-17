package ai.elimu.common.utils.data.repository.local

import ai.elimu.common.utils.data.model.tts.QueueMode
import android.speech.tts.UtteranceProgressListener

interface LocalTextToSpeechDataSource {
    suspend fun speak(text: CharSequence, queueMode: QueueMode, utteranceId: String)
    fun isSpeaking(): Boolean
    fun stop()
    fun setOnUtteranceProgressListener(listener: UtteranceProgressListener): Int
    fun playSilentUtterance(
        durationInMs: Long, queueMode: Int,
        utteranceId: String?
    ): Int
}