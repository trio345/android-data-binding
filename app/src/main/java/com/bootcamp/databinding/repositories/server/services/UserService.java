package com.bootcamp.databinding.repositories.server.services;

import com.bootcamp.databinding.models.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {
    @GET("users")
    Call<UserResponse> getUsers();
}
