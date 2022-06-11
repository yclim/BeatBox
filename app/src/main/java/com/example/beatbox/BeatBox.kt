package com.example.beatbox

import android.content.res.AssetManager
import android.util.Log
import java.lang.Exception

private const val TAG = "BeatBox"
private const val SOUNDS_FOLDER = "sample_sounds"
class BeatBox(private val assetManager: AssetManager) {

    val sounds: List<Sound>
    init {
        sounds = loadSounds()
    }

    private fun loadSounds(): List<Sound> {
        try {
            val soundNames = assetManager.list(SOUNDS_FOLDER)
            Log.d(TAG, "Found ${soundNames?.size} sounds")
            return soundNames?.map { Sound("$SOUNDS_FOLDER/$it") } ?: emptyList()

        } catch (e: Exception) {
            Log.e(TAG, "could not list assets", e)
            return emptyList()
        }
    }


}