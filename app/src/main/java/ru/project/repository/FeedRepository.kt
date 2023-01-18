package ru.project.repository

import io.reactivex.Single
import retrofit2.Response
import ru.project.data.models.Post
import ru.project.data.models.Subreddit
import ru.project.net.RedditClient

class FeedRepository(
    private val redditClient: RedditClient
) {

    fun getSubreddit(subreddit: String): Single<Response<Subreddit>> {
        return redditClient.getSubredditInfo(subreddit)
    }

    fun getPost(subreddit: String): Single<Response<Post>> {
        return redditClient.getPost(subreddit)
    }
}