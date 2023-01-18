package ru.project.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import ru.project.app.RedTok
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

class FeedViewModel(app: Application) : AndroidViewModel(app) {

    @Inject
    lateinit var feedRepository: FeedRepository

    val state = MutableLiveData<DataState>()
    private val compositeDisposable = CompositeDisposable()

    init {
        (app as RedTok).appComponent.inject(this)

        state.default(DataState.DefaultState())
        getPost("antiwork")
    }

    @SuppressLint("CheckResult")
    fun getPost(subreddit: String) {
        state.set(DataState.LoadingState())

        val a = feedRepository.getSubreddit(subreddit)
        val b = feedRepository.getPost(subreddit)

        a.zipWith(b) {t, u -> Pair(t ,u)}.subscribe({
            if (it.first.isSuccessful and it.second.isSuccessful) {
                var data: String? = it.second.body()!!.first().data.children.first().data.selftextHtml

                if (data == null) {
                    data = it.second.body()!!.first().data.children.first().data.urlOverriddenByDest
                }

                state.set(DataState.LoadedState(
                    it.first.body()!!.data.displayName,
                    it.second.body()!!.first().data.children.first().data.title,
                    it.first.body()!!.data.iconImg,
                    data))
            }
            else {
                state.set(DataState.IllegalTokenState())
            }
        }, {
            Log.e("RedTok", "Error")
        })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}