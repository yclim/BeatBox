package com.example.beatbox

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

// view model will not update layout file when sound is changed
// notifyChange fix that
class SoundViewModel(private val beatBox: BeatBox): BaseObservable() {
    fun onButtonClicked() {
        sound?.let {
            beatBox.play(it)
        }
    }

    var sound: Sound? = null
        set(sound) {
            field = sound
            notifyChange()
        }

    @get:Bindable
    val title: String?
        get() = sound?.name
}