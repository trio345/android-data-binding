package com.bootcamp.databinding.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bootcamp.databinding.models.PostModel;
import com.bootcamp.databinding.repositories.server.clients.PostClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends ViewModel {
    private MutableLiveData<ArrayList<PostModel>> posts = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();
    private ArrayList<PostModel> temp = new ArrayList<>();

    public PostViewModel() {
        fetchPosts();
    }

    private void fetchPosts() {
        isLoading.setValue(true);

        PostClient.service().getPosts().enqueue(new Callback<ArrayList<PostModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PostModel>> call, Response<ArrayList<PostModel>> response) {
                if (response.isSuccessful()) {
                    posts.setValue(response.body());

                    temp = posts.getValue();
                } else {
                    error.setValue(response.message());
                }

                isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<ArrayList<PostModel>> call, Throwable t) {
                t.printStackTrace();

                error.setValue(t.getMessage());

                isLoading.setValue(false);
            }
        });
    }

    public void searchPost(CharSequence s) {
        ArrayList<PostModel> postModels = new ArrayList<>();

        if (posts.getValue() != null) {
            for (PostModel post : temp) {
                if (post.getTitle().toLowerCase().contains(s) ||
                        post.getBody().toLowerCase().contains(s)) {

                    postModels.add(post);
                }
            }

            posts.setValue(postModels);
        }
    }

    public LiveData<ArrayList<PostModel>> getPosts() {
        return posts;
    }

    public LiveData<String> getError() {
        return error;
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }
}
