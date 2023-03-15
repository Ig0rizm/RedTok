package ru.project.factories

import io.reactivex.Single
import ru.project.data.models.Post
import ru.project.net.RedditClient

class PostLoader(private val redditClient: RedditClient) {

    fun getPost(subredditName: String): Single<Post> {
        val subreddit = redditClient.getSubredditInfo(subredditName)
        val post = redditClient.getPost(subredditName)

        return Single.zip(subreddit, post) { subredditResult, postResult ->
            Post(
                subredditResult.body()!!.data.displayName,
                postResult.body()!!.first().data.children.first().data.title,
                subredditResult.body()!!.data.iconImg,
                postResult.body()!!.first().data.children.first().data.selftextHtml?: postResult.body()!!.first().data.children.first().data.urlOverriddenByDest!!
            )
        }
    }
}