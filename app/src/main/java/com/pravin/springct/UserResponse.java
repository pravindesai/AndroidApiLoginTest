package com.pravin.springct;

import com.google.gson.annotations.SerializedName;

public class UserResponse{

	public static String KEY_PHONE = "phone";
	public static String KEY_mail = "mail";
	public static String KEY_name = "name";

	@SerializedName("phone")
	private String phone;

	@SerializedName("name")
	private String name;

	@SerializedName("email")
	private String email;

	public String getPhone(){
		return phone;
	}

	public String getName(){
		return name;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"UserResponse{" + 
			"phone = '" + phone + '\'' + 
			",name = '" + name + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}