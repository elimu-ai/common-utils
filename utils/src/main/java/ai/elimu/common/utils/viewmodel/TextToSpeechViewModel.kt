package ai.elimu.common.utils.viewmodel

import ai.elimu.common.utils.data.model.tts.QueueMode
import android.speech.tts.UtteranceProgressListener
import java.util.Locale

interface TextToSpeechViewModel {
    fun speak(text: CharSequence, queueMode: QueueMode, utteranceId: String)
    fun isSpeaking(): Boolean
    fun stop()
    fun setOnUtteranceProgressListener(listener: UtteranceProgressListener): Int
    fun playSilentUtterance(
        durationInMs: Long, queueMode: Int,
        utteranceId: String?
    ): Int
    fun setSpeechRate(speechRate: Float): Int
    fun setLanguage(loc: Locale?): Int
}