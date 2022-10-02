package ru.project.net

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*
import ru.project.data.models.Post
import ru.project.data.models.Subreddit

interface RedditService {

    @GET("/r/{subreddit}/random")
    fun getRandomPost(
        @Path("subreddit") subreddit: String,
        @Header("Authorization") authorization: String
    ): Single<Response<Post>>

    @GET("/r/{subreddit}/about")
    fun getSubredditInfo(
        @Path("subreddit") subreddit: String,
        @Header("Authorization") authorization: String
    ): Single<Response<Subreddit>>
}