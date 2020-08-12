package com.bootcamp.databinding.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.bootcamp.databinding.R;
import com.bootcamp.databinding.utils.ConstantUtil;
import com.bootcamp.databinding.utils.SharedUtil;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = SharedUtil.preferences(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (sharedPreferences.getBoolean(ConstantUtil.FIRST_TIME, true)) {
                showDialog();
            } else {
                moveToHome();
            }
        }, 3000);
    }

    private void moveToHome() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    private void showDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        View view = getLayoutInflater().inflate(R.layout.dialog_term_and_condition, null);

        CheckBox cbTermAndCondition = view.findViewById(R.id.cbTermAndCondition);
        Button btnNext = view.findViewById(R.id.btnNext);

        btnNext.setOnClickListener(it -> {
            if (cbTermAndCondition.isChecked()) {
                Editor editor = sharedPreferences.edit();
                editor.putBoolean(ConstantUtil.FIRST_TIME, false);
                editor.apply();

                dialog.dismiss();

                moveToHome();
            } else {
                Toast.makeText(MainActivity.this, "Anda harus menyutujui dengan menchecklist pada checkbox yang bertuliskan saya setuju", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.setView(view);
        dialog.setCancelable(false);
        dialog.setIcon(R.mipmap.ic_launcher_round);
        dialog.setTitle("Syarat dan ketentuan");
        dialog.show();
    }
}