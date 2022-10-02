package ru.project.di

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class AppModule constructor(val app: Application) {

    @Provides
    fun provideApplication(): Application {
        return app
    }
}