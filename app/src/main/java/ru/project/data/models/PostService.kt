package ru.project.data.models

import android.annotation.SuppressLint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.project.factories.PostLoader

typealias PostListListener = (posts: List<Post?>) -> Unit

@SuppressLint("CheckResult")
class PostService(val postLoader: PostLoader) {
    private val postList = mutableListOf<Post?>(null)
    private val listeners = mutableSetOf<PostListListener>()

    init {
        addPost()
    }

    fun addPost() {
        postLoader.getPost("antiwork")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                result -> run {
                postList[postList.size - 1] = result
                postList += null
            }
                notifyChanges()
            }
    }

    fun removeAll() {
        postList.removeAll { it != null }
        addPost()
    }

    fun addListener(listener: PostListListener) {
        listeners.add(listener)
        listener.invoke(postList)
    }

    fun removeListeners() {

    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(postList) }
    }
}