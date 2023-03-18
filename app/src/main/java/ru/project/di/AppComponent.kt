package ru.project.di

import dagger.Component
import ru.project.viewmodels.FeedViewModel
import ru.project.viewmodels.MainViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(viewModel: MainViewModel)
    fun inject(viewModel: FeedViewModel)
}