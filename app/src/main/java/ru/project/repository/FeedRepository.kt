package ru.project.repository

import android.annotation.SuppressLint
import android.util.Log
import ru.project.net.RedditClient

class FeedRepository(
    private val redditClient: RedditClient
) {

    @SuppressLint("CheckResult")
    fun getIcon(subreddit: String): String? {
        var iconUrl: String? = null

        redditClient.getSubredditInfo(subreddit).subscribe({
            if (it.isSuccessful) {
                iconUrl = it.body()!!.data.iconImg
            }
        }, {
            Log.e("RedTok", "Error")
        })

        return iconUrl
    }

    @SuppressLint("CheckResult")
    fun getTitle(subreddit: String): String? {
        var title: String? = null

        redditClient.getPost(subreddit).subscribe({
            if (it.isSuccessful) {
                val postBody = it.body()!!
                title = postBody.first().data.children.first().data.title
            }
        }, {
            Log.e("RedTok", "Error")
        })

        return title
    }

    @SuppressLint("CheckResult")
    fun getData(subreddit: String): String? {
        var data: String? = null

        redditClient.getPost(subreddit).subscribe({
            if (it.isSuccessful) {
                data = it.body()!!.first().data.children.first().data.selftextHtml

                if (data == null) {
                    data = it.body()!!.first().data.children.first().data.urlOverriddenByDest
                }
            }
        }, {
            Log.e("RedTok", "Error")
        })

        return data
    }

    @SuppressLint("CheckResult")
    fun getSubredditName(subreddit: String): String? {
        var subredditName: String? = null

        redditClient.getPost(subreddit).subscribe({
            if (it.isSuccessful) {
                subredditName = it.body()!!.first().data.children.first().data.subreddit
            }
        }, {
            Log.e("RedTok", "Error")
        })

        return subredditName
    }
}