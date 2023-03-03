package ru.project.net

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import ru.project.data.reddit.RedditJsonPost
import ru.project.data.reddit.RedditJsonSubreddit

class RedditClient(
    private val redditService: RedditService,
    private val authorization: String
) {

    fun getPost(subreddit: String): Single<Response<RedditJsonPost>> {
        return redditService.getRandomPost(subreddit, authorization)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSubredditInfo(subreddit: String): Single<Response<RedditJsonSubreddit>> {
        return redditService.getSubredditInfo(subreddit, authorization)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}