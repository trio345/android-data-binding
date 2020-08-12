package com.bootcamp.databinding.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bootcamp.databinding.R;
import com.bootcamp.databinding.databinding.ItemPostBinding;
import com.bootcamp.databinding.models.PostModel;
import com.bootcamp.databinding.viewmodels.DetailPostViewModel;
import com.bootcamp.databinding.viewmodels.factories.DetailPostViewModelFactory;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private ArrayList<PostModel> posts = new ArrayList<>();

    public interface PostListener {
        void onClick(PostModel postModel);
    }

    public void setPosts(ArrayList<PostModel> posts) {
        this.posts = posts;

        notifyDataSetChanged();
    }

    public PostListener postListener;

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPostBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_post,
                parent,
                false
        );

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        holder.bindData(posts.get(position), postListener);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemPostBinding binding;

        public ViewHolder(@NonNull ItemPostBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void bindData(PostModel postModel, PostListener postListener) {
            DetailPostViewModel viewModel = new DetailPostViewModelFactory(postModel)
                    .create(DetailPostViewModel.class);

            binding.setViewModel(viewModel);
            binding.cvPost.setOnClickListener(view -> postListener.onClick(postModel));
        }
    }
}
