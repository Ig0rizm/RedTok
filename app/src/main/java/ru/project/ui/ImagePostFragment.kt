package ru.project.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.project.R
import ru.project.app.GlideApp
import ru.project.databinding.FragmentPostImageBinding

class ImagePostFragment : Fragment(R.layout.fragment_post_image) {
    private val binding by viewBinding(FragmentPostImageBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageUrl = arguments?.getString("imageUrl")
        val postTitle = arguments?.getString("title")
        val postSubreddit = arguments?.getString("subreddit")
        val iconUrl = arguments?.getString("iconUrl")

        val imageData = GlideApp.with(this).load(imageUrl)
        val iconImage = GlideApp.with(this).load(iconUrl)

        with (binding) {
            iconImage.into(icon)
            imageData.into(image)
            subreddit.text = postSubreddit
            title.text = postTitle
        }
    }
}