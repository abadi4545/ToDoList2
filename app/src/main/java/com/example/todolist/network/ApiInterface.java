package com.example.todolist.network;

import com.example.todolist.model.MyData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("todos")
    Call<List<MyData>> getMyDatas();

}
