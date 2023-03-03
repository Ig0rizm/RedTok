package ru.project.net

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*
import ru.project.data.reddit.RedditJsonPost
import ru.project.data.reddit.RedditJsonSubreddit

interface RedditService {

    @GET("/r/{subreddit}/random")
    fun getRandomPost(
        @Path("subreddit") subreddit: String,
        @Header("Authorization") authorization: String
    ): Single<Response<RedditJsonPost>>

    @GET("/r/{subreddit}/about")
    fun getSubredditInfo(
        @Path("subreddit") subreddit: String,
        @Header("Authorization") authorization: String
    ): Single<Response<RedditJsonSubreddit>>
}