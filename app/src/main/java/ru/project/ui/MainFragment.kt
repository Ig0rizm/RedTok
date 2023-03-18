package ru.project.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.project.R
import ru.project.app.RedTok
import ru.project.databinding.FragmentMainBinding
import ru.project.viewmodels.MainState
import ru.project.viewmodels.MainViewModel

class MainFragment: Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)
    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.mainState.observe(viewLifecycleOwner) {
            when (it) {
                is MainState.DefaultState -> {}

                is MainState.ErrorState -> {
                    Log.e("RedTok", "Error Message: " + it.message)
                    Log.e("RedTok", "Error Cause: " + it.cause)
                    (view.context.applicationContext as RedTok).getSharedPreferences("RedTokPreference", Context.MODE_PRIVATE).edit().putString("apiToken", "").apply()
                    findNavController().navigate(R.id.action_mainFragment_to_authFragment)
                }
            }
        }

        val nestedNavHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment_content) as? NavHostFragment
        val navController = nestedNavHostFragment?.navController!!

        with(binding) {
            swipeLayout.setOnRefreshListener {
                viewModel.handleSwipeState()
                swipeLayout.isRefreshing = false
            }

            navView.menu[0].setOnMenuItemClickListener {
                navController.navigate(R.id.toFeed)
                true
            }

            navView.menu[3].setOnMenuItemClickListener {
                navController.navigate(R.id.toProfile)
                true
            }
        }
    }
}