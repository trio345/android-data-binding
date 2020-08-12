package com.bootcamp.databinding.repositories.server.services;

import com.bootcamp.databinding.models.PostModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostService {
    @GET("posts")
    Call<ArrayList<PostModel>> getPosts();
}
