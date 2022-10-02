package ru.project.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ru.project.extensions.default
import ru.project.extensions.set
import ru.project.repository.FeedRepository
import javax.inject.Inject

sealed class DataState {
    class DefaultState: DataState()

    class LoadingState: DataState()

    class LoadedState(
        val subreddit: String?,
        val title: String?,
        val icon: String?,
        val data: String?): DataState()

    class ErrorState(
        val error: String?): DataState()

    class IllegalTokenState: DataState()
}

class FeedViewModel : ViewModel() {

    @Inject
    lateinit var feedRepository: FeedRepository

    val state = MutableLiveData<DataState>()
    private val compositeDisposable = CompositeDisposable()

    init {
        state.default(DataState.DefaultState())
        getPost("antiwork")
    }

    fun getPost(subreddit: String) {
        state.set(DataState.LoadingState())

        state.set(DataState.LoadedState(
            feedRepository.getSubredditName(subreddit),
            feedRepository.getTitle(subreddit),
            feedRepository.getIcon(subreddit),
            feedRepository.getData(subreddit)))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}