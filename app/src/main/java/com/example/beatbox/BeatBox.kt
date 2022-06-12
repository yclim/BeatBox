package com.example.beatbox

import android.content.res.AssetManager
import android.media.SoundPool
import android.util.Log
import java.lang.Exception

private const val TAG = "BeatBox"
private const val SOUNDS_FOLDER = "sample_sounds"
private const val MAX_SOUNDS = 5
class BeatBox(private val assetManager: AssetManager) {

    private val soundPool = SoundPool.Builder()
        .setMaxStreams(MAX_SOUNDS)
        .build()

    val sounds: List<Sound>
    init {
        sounds = loadSounds()
    }

    fun play(sound: Sound) {
        sound.soundId?.let {
            soundPool.play(it, 1.0f, 1.0f, 1, 0, 1.0f)
        }
    }

    fun release() {
        soundPool.release()
    }

    private fun loadSounds(): List<Sound> {
        try {
            val soundNames = assetManager.list(SOUNDS_FOLDER)
            Log.d(TAG, "Found ${soundNames?.size} sounds")
            return soundNames?.map {
                val sound = Sound("$SOUNDS_FOLDER/$it")
                load(sound)
                sound
            } ?: emptyList()

        } catch (e: Exception) {
            Log.e(TAG, "could not list assets", e)
            return emptyList()
        }
    }

    private fun load(sound: Sound) {
        val assetFileDescriptor = assetManager.openFd(sound.assetPath)
        val soundId = soundPool.load(assetFileDescriptor, 1)
        sound.soundId = soundId
    }


}