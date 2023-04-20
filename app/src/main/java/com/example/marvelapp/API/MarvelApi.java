package com.example.marvelapp.API;

import com.example.marvelapp.Models.Marvel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MarvelApi {

    @GET("demos/marvel")
    Call<List<Marvel>> getAllMarvelData();
}
