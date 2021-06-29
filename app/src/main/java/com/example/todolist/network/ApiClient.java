package com.example.todolist.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    //define base url
    public  static String base_url = "https://jsonplaceholder.typicode.com/";

    //retrofit
    public static Retrofit getClient(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static ApiInterface apiInterface(){
        return getClient().create(ApiInterface.class);
    }

}
