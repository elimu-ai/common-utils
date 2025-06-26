package ai.elimu.common.utils.di

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log

class TextToSpeechWrapper(context: Context) {
    lateinit var tts: TextToSpeech
    private val TAG = "TextToSpeech"

    init {
        Log.d(TAG, "init TextToSpeech START")
        tts = TextToSpeech(context) { status ->
            Log.d(TAG, "init TextToSpeech DONE. status: $status")
            if (status != TextToSpeech.SUCCESS) {
                Log.e(TAG, "TTS initialization failed with status: $status")
            }
        }
    }
}