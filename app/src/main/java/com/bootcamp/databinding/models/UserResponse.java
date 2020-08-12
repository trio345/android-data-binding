package com.bootcamp.databinding.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserResponse{

	@SerializedName("per_page")
	private int perPage;

	@SerializedName("total")
	private int total;

	@SerializedName("ad")
	private UserAddressModel ad;

	@SerializedName("data")
	private ArrayList<UserModel> data;

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	public void setPerPage(int perPage){
		this.perPage = perPage;
	}

	public int getPerPage(){
		return perPage;
	}

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setAd(UserAddressModel ad){
		this.ad = ad;
	}

	public UserAddressModel getAd(){
		return ad;
	}

	public void setData(ArrayList<UserModel> data){
		this.data = data;
	}

	public ArrayList<UserModel> getData(){
		return data;
	}

	public void setPage(int page){
		this.page = page;
	}

	public int getPage(){
		return page;
	}

	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	public int getTotalPages(){
		return totalPages;
	}
}