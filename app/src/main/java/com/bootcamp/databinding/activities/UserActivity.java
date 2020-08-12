package com.bootcamp.databinding.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bootcamp.databinding.R;
import com.bootcamp.databinding.utils.ViewUtil;
import com.bumptech.glide.Glide;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        ImageView ivUser = findViewById(R.id.ivUser);
        TextView tvName = findViewById(R.id.tvName), tvEmail = findViewById(R.id.tvEmail);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            Glide.with(this).load(bundle.getString(SecondActivity.USER_AVATAR)).circleCrop().into(ivUser);

            tvName.setText(bundle.getString(SecondActivity.USER_NAME));
            tvEmail.setText(bundle.getString(SecondActivity.USER_EMAIL));

            ViewUtil.showArrowBack(this, tvName.getText().toString());
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}