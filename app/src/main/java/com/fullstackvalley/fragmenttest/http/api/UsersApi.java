package com.fullstackvalley.fragmenttest.http.api;

import com.fullstackvalley.fragmenttest.http.beans.JokeBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UsersApi {
    @GET("/joke/content/list.php?sort=asc&time=1418816972&key=7c222911dd059a21fab6ebe23da218ea")
    Call<JokeBean> getJoke(@Query("page") int page,@Query("pagesize") int pagesize);

    @GET("joke/content/list.php")
    Call<JokeBean> getJoke2(@Query("page") int page,@Query("pagesize") int pagesize);
}

