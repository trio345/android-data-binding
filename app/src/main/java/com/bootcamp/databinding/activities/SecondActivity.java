package com.bootcamp.databinding.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bootcamp.databinding.R;
import com.bootcamp.databinding.adapters.UserAdapter;
import com.bootcamp.databinding.models.UserModel;
import com.bootcamp.databinding.models.UserResponse;
import com.bootcamp.databinding.repositories.server.clients.UserClient;
import com.bootcamp.databinding.utils.ViewUtil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {

    public final static String USER_NAME = "name";
    public final static String USER_EMAIL = "email";
    public final static String USER_AVATAR = "avatar";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        EditText etSearch = findViewById(R.id.etSearch);
        ProgressBar pbLoading = findViewById(R.id.pbLoading);
        RecyclerView rvUsers = findViewById(R.id.rvUsers);

        UserAdapter userAdapter = new UserAdapter();
        userAdapter.userListener = userModel -> {
            Bundle bundle = new Bundle();
            bundle.putString(USER_NAME, userModel.getFirstName() + " " + userModel.getLastName());
            bundle.putString(USER_EMAIL, userModel.getEmail());
            bundle.putString(USER_AVATAR, userModel.getAvatar());

            Intent intent = new Intent(this, UserActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        };

        rvUsers.setAdapter(userAdapter);

        final ArrayList<UserModel>[] users = new ArrayList[]{new ArrayList<>()};

        UserClient.service().getUsers().enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                pbLoading.setVisibility(View.GONE);

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        users[0] = response.body().getData();
                        userAdapter.setUsers(users[0]);
                    } else {
                        showMessage("Data user kosong!");
                    }
                } else {
                    showMessage(response.message());
                }

                rvUsers.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                t.printStackTrace();

                pbLoading.setVisibility(View.GONE);

                showMessage(t.getMessage());
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ArrayList<UserModel> userModels = new ArrayList<>();

                String s = charSequence.toString().toLowerCase();

                for (UserModel model : users[0]) {
                    if (model.getFirstName().toLowerCase().contains(s) ||
                            model.getLastName().toLowerCase().contains(s)) {

                        userModels.add(model);
                    }
                }

                userAdapter.setUsers(userModels);
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });

        etSearch.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                ViewUtil.hideKeyboard(textView);
                return true;
            }

            return false;
        });
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}