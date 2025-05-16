package ai.elimu.keyboard.util

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.util.Log

/**
 * Utility class which helps releasing the [MediaPlayer] instance after
 * finishing playing the audio.
 *
 *
 *
 * See https://developer.android.com/reference/android/media/MediaPlayer.html#create%28android.content.Context,%20int%29
 */
object MediaPlayerHelper {
    fun play(context: Context?, resId: Int) {
        Log.i(MediaPlayerHelper::class.java.getName(), "play")

        val mediaPlayer = MediaPlayer.create(context, resId)
        mediaPlayer.setOnCompletionListener(object : OnCompletionListener {
            override fun onCompletion(mp: MediaPlayer?) {
                mediaPlayer.release()
            }
        })
        mediaPlayer.start()
    }
}
