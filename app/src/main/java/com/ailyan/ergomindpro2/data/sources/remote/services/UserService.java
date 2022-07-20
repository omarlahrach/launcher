package com.ailyan.ergomindpro2.data.sources.remote.services;

import com.ailyan.ergomindpro2.data.sources.remote.beans.AuthResponse;
import com.ailyan.ergomindpro2.utilities.Json;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {
    @FormUrlEncoded
    @POST("connexionVA.php")
    @Json
    Single<AuthResponse> login(@Field("login") String username, @Field("pass") String password);
}
