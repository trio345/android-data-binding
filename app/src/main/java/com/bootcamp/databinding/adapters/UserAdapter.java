package com.bootcamp.databinding.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bootcamp.databinding.R;
import com.bootcamp.databinding.models.UserModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private ArrayList<UserModel> users;

    public interface UserListener {
        void onClick(UserModel userModel);
    }

    public UserListener userListener;

    public void setUsers(ArrayList<UserModel> users) {
        this.users = users;

        notifyDataSetChanged();
    }

    public void addUsers(ArrayList<UserModel> users) {
        this.users.addAll(users);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        UserModel userModel = users.get(position);

        Glide.with(holder.itemView).load(userModel.getAvatar()).circleCrop().into(holder.ivUser);

        holder.tvName.setText(String.format("%s %s", userModel.getFirstName(), userModel.getLastName()));
        holder.tvEmail.setText(userModel.getEmail());

        holder.cvUser.setOnClickListener(view -> userListener.onClick(userModel));
    }

    @Override
    public int getItemCount() {
        return users != null ? users.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cvUser;
        ImageView ivUser;
        TextView tvName, tvEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cvUser = itemView.findViewById(R.id.cvUser);
            ivUser = itemView.findViewById(R.id.ivUser);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }
    }
}
