package com.ailyan.ergomindpro2.data.repositories;

import com.ailyan.ergomindpro2.data.sources.remote.beans.AuthResponse;
import com.ailyan.ergomindpro2.data.sources.remote.RetrofitInstance;
import com.ailyan.ergomindpro2.data.sources.remote.services.UserService;

import io.reactivex.rxjava3.core.Single;

public class UserRepository {
    private final UserService userService;

    public UserRepository() {
        userService = RetrofitInstance.getInstance().create(UserService.class);
    }

    public Single<AuthResponse> login(String username, String password) {
        return userService.login(username, password);
    }
}
