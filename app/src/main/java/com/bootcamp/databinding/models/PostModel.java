package com.bootcamp.databinding.models;

import android.os.Parcel;
import android.os.Parcelable;

public class PostModel implements Parcelable {
    private int id, userId;
    private String title, body;

    public PostModel() {}

    public PostModel(int id, int userId, String title, String body) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

	protected PostModel(Parcel in) {
		id = in.readInt();
		userId = in.readInt();
		title = in.readString();
		body = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeInt(userId);
		dest.writeString(title);
		dest.writeString(body);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<PostModel> CREATOR = new Creator<PostModel>() {
		@Override
		public PostModel createFromParcel(Parcel in) {
			return new PostModel(in);
		}

		@Override
		public PostModel[] newArray(int size) {
			return new PostModel[size];
		}
	};

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
