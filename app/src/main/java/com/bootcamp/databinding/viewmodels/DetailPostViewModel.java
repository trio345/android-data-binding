package com.bootcamp.databinding.viewmodels;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.bootcamp.databinding.models.PostModel;

public class DetailPostViewModel extends ViewModel {
    public ObservableField<PostModel> post = new ObservableField<>();

    public DetailPostViewModel(PostModel postModel) {
        post.set(postModel);
    }
}
