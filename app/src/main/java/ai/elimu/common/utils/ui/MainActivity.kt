package ai.elimu.common.utils.ui

import ai.elimu.common.utils.checkIfAppstoreIsInstalled
import ai.elimu.common.utils.data.model.tts.QueueMode
import ai.elimu.common.utils.databinding.ActivityMainBinding
import ai.elimu.common.utils.viewmodel.TextToSpeechViewModel
import ai.elimu.common.utils.viewmodel.TextToSpeechViewModelImpl
import android.os.Bundle
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var ttsViewModel: TextToSpeechViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ttsViewModel = ViewModelProvider(this)[TextToSpeechViewModelImpl::class.java]

        binding.btnSpeak.setOnClickListener {
            val utteranceId = Random.nextInt().toString()
            ttsViewModel.speak(binding.edtSpeak.text.toString(), QueueMode.ADD, utteranceId)
            Log.d(TAG, "speak utteranceId: $utteranceId")
            binding.btnSpeak.isEnabled = false
        }

        ttsViewModel.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(utteranceId: String?) {
            }

            override fun onDone(utteranceId: String?) {
                Log.d(TAG, "onDone utteranceId: $utteranceId")
                lifecycleScope.launch {
                    binding.btnSpeak.isEnabled = true
                }
            }

            override fun onError(utteranceId: String?) {
            }

            override fun onError(utteranceId: String?, errorCode: Int) {
                super.onError(utteranceId, errorCode)
            }

        })

        window.apply {
            setLightStatusBar()
            setStatusBarColorCompat(android.R.color.white)
        }

        val isAppstoreInstalled = checkIfAppstoreIsInstalled("ai.elimu.appstore.debug")
        Log.d(TAG, "isAppstoreInstalled: $isAppstoreInstalled")
    }
}