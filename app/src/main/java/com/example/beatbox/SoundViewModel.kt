package com.example.beatbox

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

// view model will not update layout file when sound is changed
// notifyChange fix that
class SoundViewModel: BaseObservable() {
    var sound: Sound? = null
        set(sound) {
            field = sound
            notifyChange()
        }

    @get:Bindable
    val title: String?
        get() = sound?.name
}