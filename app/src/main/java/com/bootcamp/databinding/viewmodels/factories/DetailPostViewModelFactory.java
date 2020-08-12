package com.bootcamp.databinding.viewmodels.factories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bootcamp.databinding.models.PostModel;
import com.bootcamp.databinding.viewmodels.DetailPostViewModel;

import java.lang.reflect.InvocationTargetException;

public class DetailPostViewModelFactory implements ViewModelProvider.Factory {
    private PostModel postModel;

    public DetailPostViewModelFactory(PostModel postModel) {
        this.postModel = postModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(PostModel.class).newInstance(postModel);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return (T) new DetailPostViewModel(new PostModel());
    }
}
