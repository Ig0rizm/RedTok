package ru.project.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.project.extensions.default

sealed class MainState {
    object DefaultState : MainState()

    class ErrorState(val message: String) : MainState()
}

class MainViewModel(app: Application): AndroidViewModel(app) {

    val state = MutableLiveData<MainState>()

    init {
        state.default(MainState.DefaultState)
    }
}