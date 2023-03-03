package ru.project.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.project.R
import ru.project.databinding.ActivityMainBinding
import ru.project.viewmodels.MainViewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)
    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        with(binding) {
            swipeRefreshLayout.setOnRefreshListener {
                mViewModel.changeAllFragmentsData("refresh")
                swipeRefreshLayout.isRefreshing = false
            }
            setContentView(root)
        }
    }
}