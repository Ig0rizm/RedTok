package ru.project.ui

import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.project.R
import ru.project.app.GlideApp
import ru.project.databinding.FragmentPostTextBinding

class TextPostFragment : Fragment(R.layout.fragment_post_text) {

    private val binding by viewBinding(FragmentPostTextBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val postData = arguments?.getString("data")
        val postTitle = arguments?.getString("title")
        val postSubreddit = arguments?.getString("subreddit")
        val iconUrl = arguments?.getString("iconUrl")

        val iconImage = GlideApp.with(this).load(iconUrl)

        with (binding) {
            iconImage.into(icon)
            data.text = Html.fromHtml(postData)
            subreddit.text = postSubreddit
            title.text = postTitle
        }
    }
}