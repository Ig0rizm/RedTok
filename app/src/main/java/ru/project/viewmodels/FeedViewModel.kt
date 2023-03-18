package ru.project.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.MutableLiveData
import ru.project.app.RedTok
import ru.project.data.models.PostListListener
import ru.project.data.models.PostService
import ru.project.extensions.default
import ru.project.extensions.set
import ru.project.viewadapters.CardAdapter
import javax.inject.Inject

sealed class DataState {
    object DefaultState : DataState()

    object LoadingState : DataState()

    class ErrorState(val message: String) : DataState()
}

@SuppressLint("CheckResult")
class FeedViewModel(app: Application) : MainViewModel(app) {

    val state = MutableLiveData<DataState>()

    private val cardAdapter = CardAdapter()
    private val postListeners: PostListListener = {
        cardAdapter.postList = it
    }

    @Inject
    lateinit var postService: PostService

    init {
        (app as RedTok).appComponent.inject(this)
        state.default(DataState.DefaultState)

        postService.addListener(postListeners)
        swipeSubject.subscribe { refresh() }
    }

    fun getAdapter(): CardAdapter {
        return cardAdapter
    }

    fun onViewPagerPageSelected(position: Int) {
        if (cardAdapter.itemCount - 2 <= position) {
            postService.addPost { msg: String, cause: Throwable? ->
                run {
                    state.set(DataState.ErrorState(msg))
                    mainState.set(MainState.ErrorState(msg, cause))
                }
            }
        }
        if (cardAdapter.itemCount - 1 == position) {
            state.set(DataState.LoadingState)
        }
        else {
            state.set(DataState.DefaultState)
        }
    }

    private fun refresh() {
        postService.removeAll()
    }
}