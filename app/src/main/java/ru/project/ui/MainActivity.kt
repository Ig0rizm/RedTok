package ru.project.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.project.R
import ru.project.databinding.ActivityMainBinding
import ru.project.viewmodels.MainViewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)
    private lateinit var mViewModel: MainViewModel

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val navController = findNavController(R.id.nav_host_fragment_content_main)

        with(binding) {
            navView.menu[0].setOnMenuItemClickListener {
                navController.navigate(R.string.bottomMenu_feed)
                true
            }

            navView.menu[3].setOnMenuItemClickListener {
                navController.navigate(R.string.bottomMenu_profile)
                true
            }

            swipeRefreshLayout.setOnRefreshListener {
                mViewModel.changeAllFragmentsData()
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }
}