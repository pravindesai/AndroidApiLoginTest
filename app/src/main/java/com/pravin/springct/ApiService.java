package com.pravin.springct;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    public static String BASEURL = "https://5d80a75f99f8a20014cf9661.mockapi.io/";

    @GET("api/login")
    Call<UserResponse> getDetails();

}
