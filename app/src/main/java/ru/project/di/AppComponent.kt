package ru.project.di

import dagger.Component
import ru.project.ui.FeedFragment
import ru.project.viewmodels.FeedViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(viewModel: FeedViewModel)
    fun inject(feedFragment: FeedFragment)
}