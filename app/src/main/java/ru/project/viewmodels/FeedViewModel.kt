package ru.project.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
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

    object IllegalTokenState : DataState()
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
        if (cardAdapter.itemCount - 2 == position || cardAdapter.itemCount - 1 == position) {
            postService.addPost()
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