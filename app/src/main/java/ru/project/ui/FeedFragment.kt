package ru.project.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.webkit.URLUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.project.R
import ru.project.app.RedTok
import ru.project.databinding.FragmentFeedBinding
import ru.project.extensions.toast
import ru.project.viewmodels.DataState
import ru.project.viewmodels.FeedViewModel
import ru.project.viewmodels.MainViewModel

class FeedFragment : Fragment(R.layout.fragment_feed) {

    private val binding by viewBinding(FragmentFeedBinding::bind)
    private val viewModel: FeedViewModel by viewModels()

    lateinit var activityViewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        activityViewModel.getAllFragmentsData().observe(viewLifecycleOwner) {
            if (it == "refresh") {
                viewModel.getPost("antiwork")
            }
        }

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
                    toast("Invalid token!")
                    (view.context.applicationContext as RedTok).getSharedPreferences("RedTokPreference", Context.MODE_PRIVATE).edit().putString("apiToken", "").apply()
                    findNavController()
                        .navigate(FeedFragmentDirections.actionDataFragmentToAuthFragment())
                }
                else -> {}
            }
        }
    }

    private fun createPost(postSubreddit: String?, iconUrl: String?, postTitle: String?, data: String?) {
        if (postSubreddit != null && iconUrl != null && postTitle != null && data != null) {
            val bundle = Bundle()
            bundle.putString("subreddit", postSubreddit)
            bundle.putString("title", postTitle)
            bundle.putString("iconUrl", iconUrl)
            if (URLUtil.isValidUrl(data) and ((data.takeLast(4) == ".jpg") or (data.takeLast(4) == ".png"))) {
                bundle.putString("imageUrl", data)
                parentFragmentManager.beginTransaction().replace(binding.post.id, ImagePostFragment::class.java, bundle).commit()
            }
            else {
                bundle.putString("data", data)
                parentFragmentManager.beginTransaction().replace(binding.post.id, TextPostFragment::class.java, bundle).commit()
            }
        }
    }
}