package ai.elimu.common.utils.data.repository.local

import ai.elimu.common.utils.data.model.tts.QueueMode
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import javax.inject.Inject

class LocalTextToSpeechDataSourceImpl @Inject constructor(
    private val tts: TextToSpeech
) : LocalTextToSpeechDataSource {

    override suspend fun speak(text: CharSequence, queueMode: QueueMode, utteranceId: String) {
        val params = Bundle().apply {
            putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, utteranceId)
        }
        Log.d("TextToSpeech", "speaking with tts language: " + tts.voice?.locale)
        tts.speak(text, queueMode.mode, params, utteranceId)
    }

    override fun isSpeaking(): Boolean {
        return tts.isSpeaking
    }

    override fun stop() {
        tts.stop()
    }

    override fun setOnUtteranceProgressListener(listener: UtteranceProgressListener): Int {
        return tts.setOnUtteranceProgressListener(listener)
    }

    override fun playSilentUtterance(durationInMs: Long, queueMode: Int, utteranceId: String?): Int {
        return tts.playSilentUtterance(durationInMs, queueMode, utteranceId)
    }

    override fun setSpeechRate(speechRate: Float): Int {
        return tts.setSpeechRate(speechRate)
    }
}