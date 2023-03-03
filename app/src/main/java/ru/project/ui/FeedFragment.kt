package ru.project.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
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

        (requireActivity().application as RedTok).appComponent.inject(this)

        activityViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        activityViewModel.getAllFragmentsData().observe(viewLifecycleOwner) {
            if (it == "refresh") {
                viewModel.refresh()
            }
        }

        binding.viewPager.adapter = viewModel.getAdapter()
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                viewModel.onViewPagerPageSelected(position)
                super.onPageSelected(position)
            }
        })

        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.LoadingState -> {
                    binding.viewPager.currentItem = binding.viewPager.currentItem - 1
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
}