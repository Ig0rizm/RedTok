package ru.project.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.functions.Action
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

class FeedViewModel(app: Application) : AndroidViewModel(app) {

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
    }

    fun getAdapter(): CardAdapter {
        return cardAdapter
    }

    fun onViewPagerPageSelected(position: Int) {
        if (cardAdapter.itemCount - 2 <= position) {
            postService.addPost { param: String -> state.set(DataState.ErrorState(param)) }
        }
        if (cardAdapter.itemCount - 1 == position) {
            state.set(DataState.LoadingState)
        }
        else {
            state.set(DataState.DefaultState)
        }
    }

    fun refresh() {
        postService.removeAll()
    }
}