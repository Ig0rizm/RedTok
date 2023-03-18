package ru.project.di

import android.app.Application
import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject
import ru.project.viewmodels.MainState
import javax.inject.Singleton

@Module
class AppModule constructor(val app: Application) {

    @Provides
    fun provideApplication(): Application {
        return app
    }

    @Provides
    @Singleton
    fun provideMainStateLiveData(): MutableLiveData<MainState> {
        return MutableLiveData<MainState>()
    }

    @Provides
    @Singleton
    fun provideSwipeSubject(): PublishSubject<Boolean> {
        return PublishSubject.create()
    }
}