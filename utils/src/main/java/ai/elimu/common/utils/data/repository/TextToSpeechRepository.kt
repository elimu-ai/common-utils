package ai.elimu.common.utils.data.repository

import ai.elimu.common.utils.data.model.tts.QueueMode
import android.speech.tts.UtteranceProgressListener

interface TextToSpeechRepository {
    suspend fun speak(text: CharSequence, queueMode: QueueMode, utteranceId: String)
    fun isSpeaking(): Boolean
    fun stop()
    fun setOnUtteranceProgressListener(listener: UtteranceProgressListener): Int
    fun playSilentUtterance(durationInMs: Long, queueMode: Int, utteranceId: String?): Int
    fun setSpeechRate(speechRate: Float): Int
}