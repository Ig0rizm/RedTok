// Generated by Dagger (https://dagger.dev).
package ru.project.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Preconditions;
import ru.project.data.models.PostService;
import ru.project.factories.PostLoader;
import ru.project.net.RedditClient;
import ru.project.net.RedditService;
import ru.project.ui.FeedFragment;
import ru.project.viewmodels.FeedViewModel;
import ru.project.viewmodels.FeedViewModel_MembersInjector;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerAppComponent {
  private DaggerAppComponent() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private AppModule appModule;

    private NetworkModule networkModule;

    private Builder() {
    }

    public Builder appModule(AppModule appModule) {
      this.appModule = Preconditions.checkNotNull(appModule);
      return this;
    }

    public Builder networkModule(NetworkModule networkModule) {
      this.networkModule = Preconditions.checkNotNull(networkModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://dagger.dev/unused-modules.
     */
    @Deprecated
    public Builder repositoryModule(RepositoryModule repositoryModule) {
      Preconditions.checkNotNull(repositoryModule);
      return this;
    }

    public AppComponent build() {
      Preconditions.checkBuilderRequirement(appModule, AppModule.class);
      if (networkModule == null) {
        this.networkModule = new NetworkModule();
      }
      return new AppComponentImpl(appModule, networkModule);
    }
  }

  private static final class AppComponentImpl implements AppComponent {
    private final NetworkModule networkModule;

    private final AppModule appModule;

    private final AppComponentImpl appComponentImpl = this;

    private AppComponentImpl(AppModule appModuleParam, NetworkModule networkModuleParam) {
      this.networkModule = networkModuleParam;
      this.appModule = appModuleParam;

    }

    private RedditService redditService() {
      return NetworkModule_ProvideRedditServiceFactory.provideRedditService(networkModule, AppModule_ProvideApplicationFactory.provideApplication(appModule));
    }

    private RedditClient redditClient() {
      return NetworkModule_ProvideRedditClientFactory.provideRedditClient(networkModule, redditService(), AppModule_ProvideApplicationFactory.provideApplication(appModule));
    }

    private PostLoader postLoader() {
      return NetworkModule_ProvidePostLoaderFactory.providePostLoader(networkModule, redditClient());
    }

    private PostService postService() {
      return NetworkModule_ProvidePostServiceFactory.providePostService(networkModule, postLoader());
    }

    @Override
    public void inject(FeedViewModel viewModel) {
      injectFeedViewModel(viewModel);
    }

    @Override
    public void inject(FeedFragment feedFragment) {
    }

    private FeedViewModel injectFeedViewModel(FeedViewModel instance) {
      FeedViewModel_MembersInjector.injectPostService(instance, postService());
      return instance;
    }
  }
}