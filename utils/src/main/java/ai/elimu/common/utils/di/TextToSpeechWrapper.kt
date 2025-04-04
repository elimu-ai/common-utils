package ai.elimu.common.utils.di

import ai.elimu.common.utils.toLanguage
import ai.elimu.common.utils.toLocale
import ai.elimu.content_provider.utils.SharedDataKeys
import android.content.Context
import android.net.Uri
import android.speech.tts.TextToSpeech
import android.util.Log

class TextToSpeechWrapper(context: Context, contentProviderId: String) {
    lateinit var tts: TextToSpeech
    private val TAG = "TextToSpeech"

    init {
        val uri = Uri.parse("content://" + contentProviderId
                + ".provider.shared_data/shared_data")
        val cursor = context.contentResolver.query(uri,
            null, null, null, null
        )
        var contentProviderLanguage = ""
        Log.v(TAG, "cursor: $cursor")
        cursor?.use {
            if (it.moveToNext()) {
                val id = it.getInt(it.getColumnIndexOrThrow("_id"))
                contentProviderLanguage = it.getString(
                    it.getColumnIndexOrThrow(SharedDataKeys.KEY_LANGUAGE)
                )
                Log.d(TAG, "ContentProvider language: $id - $contentProviderLanguage")
            }
        }

        Log.d(TAG, "init TextToSpeech START language: $contentProviderLanguage")
        tts = TextToSpeech(context) { status ->
            Log.d(TAG, "init TextToSpeech DONE. status: $status")
            if (status == TextToSpeech.SUCCESS) {
                tts.setSpeechRate(0.5f)

                if (contentProviderLanguage.isNotEmpty()) {
                    tts.setLanguage(contentProviderLanguage.lowercase().toLanguage().toLocale())
                }
            } else {
                Log.e(TAG, "TTS initialization failed with status: $status")
            }
        }
    }
}