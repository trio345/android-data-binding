package com.bootcamp.databinding.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bootcamp.databinding.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void moveToNumberOne(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }

    public void moveToNumberTwo(View view) {
        startActivity(new Intent(this, ThirdActivity.class));
    }
}