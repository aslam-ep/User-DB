package com.hector.userdb.API;


import com.hector.userdb.DataModels.APIResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface MyAPI {
    @GET("day?api_key=a9132f98accf2082321b03563d9668a8")
    Call<APIResponse> getResponse();
}
