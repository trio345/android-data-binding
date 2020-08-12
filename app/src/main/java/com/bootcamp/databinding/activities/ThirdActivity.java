package com.bootcamp.databinding.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bootcamp.databinding.R;
import com.bootcamp.databinding.adapters.PostAdapter;
import com.bootcamp.databinding.databinding.ActivityThirdBinding;
import com.bootcamp.databinding.utils.ViewUtil;
import com.bootcamp.databinding.viewmodels.PostViewModel;

public class ThirdActivity extends AppCompatActivity {

    public final static String DATA_POST = "post";

    ActivityThirdBinding binding;
    PostViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) getSupportActionBar().setElevation(0);

        PostAdapter postAdapter = new PostAdapter();
        postAdapter.postListener = postModel -> {
            Intent intent = new Intent(
                    ThirdActivity.this,
                    PostActivity.class
            ).putExtra(DATA_POST, postModel);
            startActivity(intent);
        };

        binding = DataBindingUtil.setContentView(this, R.layout.activity_third);
        binding.rvPosts.setLayoutManager(new StaggeredGridLayoutManager(
                2,
                LinearLayoutManager.VERTICAL
        ));
        binding.rvPosts.setAdapter(postAdapter);

        viewModel = new ViewModelProvider(this).get(PostViewModel.class);
        viewModel.getPosts().observe(this, postAdapter::setPosts);

        viewModel.isLoading().observe(this, isLoading -> {
            binding.pbLoading.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            binding.rvPosts.setVisibility(isLoading ? View.GONE : View.VISIBLE);
        });

        viewModel.getError().observe(this, error -> {
            if (error.length() > 0) Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });

        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModel.searchPost(charSequence.toString().toLowerCase());
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });

        binding.etSearch.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                ViewUtil.hideKeyboard(textView);
                return true;
            }

            return false;
        });

        binding.setViewModel(viewModel);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        binding = null;
    }
}