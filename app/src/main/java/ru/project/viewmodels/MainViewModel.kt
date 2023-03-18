package ru.project.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.subjects.PublishSubject
import ru.project.app.RedTok
import ru.project.extensions.default
import javax.inject.Inject

sealed class MainState {
    object DefaultState : MainState()

    class ErrorState(val message: String, val cause: Throwable?) : MainState()
}

open class MainViewModel(app: Application): AndroidViewModel(app) {

    @Inject
    lateinit var mainState: MutableLiveData<MainState>

    @Inject
    lateinit var swipeSubject: PublishSubject<Boolean>

    init {
        (app as RedTok).appComponent.inject(this)
        mainState.default(MainState.DefaultState)
    }

    fun handleSwipeState() {
        swipeSubject.onNext(true)
    }
}