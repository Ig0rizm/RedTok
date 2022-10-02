package ru.project.di

import dagger.Module
import dagger.Provides
import ru.project.net.RedditClient
import ru.project.repository.FeedRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideFeedRepository(redditClient: RedditClient): FeedRepository {
        return FeedRepository(redditClient)
    }
}