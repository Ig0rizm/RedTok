package ru.project.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.project.R
import ru.project.app.GlideApp
import ru.project.databinding.FragmentFeedBinding
import ru.project.extensions.toast
import ru.project.viewmodels.DataState
import ru.project.viewmodels.FeedViewModel
import ru.project.viewmodels.SharedViewModel

class FeedFragment : Fragment(R.layout.fragment_feed) {

    private val binding by viewBinding(FragmentFeedBinding::bind)
    private val viewModel: FeedViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.LoadingState -> {

                }

                is DataState.LoadedState -> {
                    createPost(it.subreddit, it.icon, it.title, it.data)
                }

                is DataState.ErrorState -> {
                    if (it.error != null) toast(it.error)
                    else toast("Error")
                }

                is DataState.IllegalTokenState -> {
                    toast("Illegal token!")
                    findNavController()
                        .navigate(FeedFragmentDirections.actionDataFragmentToAuthFragment())
                }
                else -> {}
            }
        }

        sharedViewModel.selected.observe(viewLifecycleOwner) { item ->
            if (item.equals("refresh")) {
                viewModel.getPost("antiwork")
            }
        }
    }

    private fun createPost(postSubreddit: String?, iconUrl: String?, postTitle: String?, data: String?) {
        if (postSubreddit != null && iconUrl != null && postTitle != null && data != null) {

            val image = GlideApp.with(this).load(iconUrl)
            val bundle = Bundle()
            bundle.putString("data", data)

            with (binding) {
                subreddit.text = postSubreddit
                title.text = postTitle
                image.into(icon)

                parentFragmentManager.beginTransaction().replace(post.id, TextPostFragment::class.java, bundle).commit()
            }
        }
    }
}