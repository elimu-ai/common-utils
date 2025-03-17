package ai.elimu.common.utils.data.model.tts

import android.speech.tts.TextToSpeech

enum class QueueMode(val mode: Int) {

    /**
     * Queue mode where all entries in the playback queue (media to be played and text to be
     * synthesized) are dropped and replaced by the new entry. Queues are flushed with respect to
     * a given calling app. Entries in the queue from other callees are not discarded.
     */
    FLUSH(TextToSpeech.QUEUE_FLUSH),

    /**
     * Queue mode where the new entry is added at the end of the playback queue.
     */
    ADD(TextToSpeech.QUEUE_ADD)
}