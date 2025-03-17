package ai.elimu.common.utils.di

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log

class TextToSpeechWrapper(context: Context) {
    lateinit var tts: TextToSpeech
    init {
        Log.d("TextToSpeech", "init TextToSpeech START")
        tts = TextToSpeech(context) { status ->
            Log.d("TextToSpeech", "init TextToSpeech DONE. status: $status")
            if (status == TextToSpeech.SUCCESS) {
                tts.setSpeechRate(0.5f)
            } else {
                Log.e("TextToSpeech", "TTS initialization failed with status: $status")
            }
        }
    }
}