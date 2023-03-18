// Generated by Dagger (https://dagger.dev).
package ru.project.viewmodels;

import androidx.lifecycle.MutableLiveData;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import io.reactivex.subjects.PublishSubject;
import javax.inject.Provider;

@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class MainViewModel_MembersInjector implements MembersInjector<MainViewModel> {
  private final Provider<MutableLiveData<MainState>> mainStateProvider;

  private final Provider<PublishSubject<Boolean>> swipeSubjectProvider;

  public MainViewModel_MembersInjector(Provider<MutableLiveData<MainState>> mainStateProvider,
      Provider<PublishSubject<Boolean>> swipeSubjectProvider) {
    this.mainStateProvider = mainStateProvider;
    this.swipeSubjectProvider = swipeSubjectProvider;
  }

  public static MembersInjector<MainViewModel> create(
      Provider<MutableLiveData<MainState>> mainStateProvider,
      Provider<PublishSubject<Boolean>> swipeSubjectProvider) {
    return new MainViewModel_MembersInjector(mainStateProvider, swipeSubjectProvider);
  }

  @Override
  public void injectMembers(MainViewModel instance) {
    injectMainState(instance, mainStateProvider.get());
    injectSwipeSubject(instance, swipeSubjectProvider.get());
  }

  @InjectedFieldSignature("ru.project.viewmodels.MainViewModel.mainState")
  public static void injectMainState(MainViewModel instance, MutableLiveData<MainState> mainState) {
    instance.mainState = mainState;
  }

  @InjectedFieldSignature("ru.project.viewmodels.MainViewModel.swipeSubject")
  public static void injectSwipeSubject(MainViewModel instance,
      PublishSubject<Boolean> swipeSubject) {
    instance.swipeSubject = swipeSubject;
  }
}