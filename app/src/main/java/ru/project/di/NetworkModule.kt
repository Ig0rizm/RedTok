package ru.project.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import ru.project.app.RedTok
import ru.project.net.RedditClient
import ru.project.net.RedditService

@Module
class NetworkModule {

    @Provides
    fun provideRedditService(app: Application): RedditService {
        return (app as RedTok).redditService
    }

    @Provides
    fun provideRedditClient(redditService: RedditService, app: Application): RedditClient {
        return RedditClient(redditService, app.getSharedPreferences("RedTokPreference", Context.MODE_PRIVATE).getString("apiToken", "")!!)
    }
}