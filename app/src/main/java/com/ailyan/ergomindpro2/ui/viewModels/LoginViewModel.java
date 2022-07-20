package com.ailyan.ergomindpro2.ui.viewModels;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.ailyan.ergomindpro2.data.repositories.UserRepository;
import com.ailyan.ergomindpro2.utilities.LoginCallback;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginViewModel extends ViewModel {
    private static final String TAG = LoginViewModel.class.getSimpleName();
    private final UserRepository userRepository;
    private Disposable disposable;

    public LoginViewModel() {
        userRepository = new UserRepository();
    }

    public void login(String username, String password, LoginCallback loginCallback) {
        disposable = userRepository.login(username, password)
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        authResponse -> {
                            if (authResponse.message == null)
                                loginCallback.onSuccess();
                            else
                                loginCallback.onFailed(authResponse.message);
                        },
                        throwable -> Log.e(TAG, "Login failed!", throwable)
                );
    }

    @Override
    protected void onCleared() {
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
        super.onCleared();
    }
}
