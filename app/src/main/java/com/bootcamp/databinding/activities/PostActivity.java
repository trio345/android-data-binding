package com.bootcamp.databinding.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.bootcamp.databinding.R;
import com.bootcamp.databinding.databinding.ActivityPostBinding;
import com.bootcamp.databinding.databinding.ActivityThirdBinding;
import com.bootcamp.databinding.databinding.ItemPostBinding;
import com.bootcamp.databinding.models.PostModel;
import com.bootcamp.databinding.utils.ViewUtil;
import com.bootcamp.databinding.viewmodels.DetailPostViewModel;
import com.bootcamp.databinding.viewmodels.factories.DetailPostViewModelFactory;

public class PostActivity extends AppCompatActivity {

    ActivityPostBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_post);

        PostModel postModel = getIntent().getParcelableExtra(ThirdActivity.DATA_POST);

        if (postModel != null) {
            ViewUtil.showArrowBack(this, postModel.getTitle());

            DetailPostViewModel viewModel = new DetailPostViewModelFactory(postModel)
                    .create(DetailPostViewModel.class);

            binding.setViewModel(viewModel);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        binding = null;
    }
}