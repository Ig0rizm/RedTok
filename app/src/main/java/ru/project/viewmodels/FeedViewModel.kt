package ru.project.viewmodels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import ru.project.app.RedTok
import ru.project.data.models.Post
import ru.project.data.models.Subreddit
import ru.project.extensions.default
import ru.project.extensions.set
import ru.project.repo.FeedRepository

sealed class DataState {
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

    val state = MutableLiveData<DataState>()
    val feedRepo = FeedRepository()

    private val redditApi = (app as? RedTok)?.redditApi
    private val sharedPref = app.getSharedPreferences("RedTokPreference", Context.MODE_PRIVATE)

    private val compositeDisposable = CompositeDisposable()

    init {
        if (feedRepo.getSubreddit() != null && feedRepo.getIcon() != null && feedRepo.getTitle() != null && feedRepo.getText() != null) {
            state.default(DataState.LoadedState(feedRepo.getSubreddit()!!, feedRepo.getTitle()!!, feedRepo.getIcon()!!, feedRepo.getText()!!))
        }
        else {
            state.default(DataState.LoadingState())
            getPost("antiwork")
        }
    }

    private fun getRandomPost(subreddit: String): Single<Response<Post>> {
        return redditApi!!.getRandomPost(subreddit, sharedPref.getString("apiToken", "")!!)
    }

    private fun getSubredditInfo(subreddit: String): Single<Response<Subreddit>> {
        return redditApi!!.getSubredditInfo(subreddit, sharedPref.getString("apiToken", "")!!)
    }

    fun getPost(subreddit: String) {
        val subredditInfo = getSubredditInfo(subreddit)
        val postInfo = getRandomPost(subreddit)

        compositeDisposable.add(postInfo.zipWith(subredditInfo, { t, u -> Pair(t, u)})
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.first.isSuccessful && it.second.isSuccessful) {
                    val postBody = it.first.body()!!
                    val subredditBody = it.second.body()!!

                    state.set(DataState.LoadedState(
                        findSubreddit(postBody),
                        findTitle(postBody),
                        findIcon(subredditBody),
                        findData(postBody)))
                }
                else {
                    sharedPref.edit().putString("apiToken", "").apply()
                    state.set(DataState.IllegalTokenState())
                }
            }, {
                state.set(DataState.ErrorState(null))
                Log.e("RedTok", "Error")
            }))
    }

    private fun findTitle(post: Post): String {
        return post.first().data.children.first().data.title
    }

    private fun findSubreddit(post: Post): String {
        return post.first().data.children.first().data.subreddit
    }

    private fun findIcon(subreddit: Subreddit): String {
        return subreddit.data.iconImg
    }

    private fun findData(post: Post): String? {
        return post.first().data.children.first().data.selftextHtml
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}