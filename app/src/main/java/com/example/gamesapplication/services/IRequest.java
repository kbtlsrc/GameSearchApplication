package com.example.gamesapplication.services;

import com.example.gamesapplication.data.all.Games;

import com.example.gamesapplication.data.search.SearchPart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IRequest {

    @GET("games")
    Call<List<SearchPart>> getGameList(@Query("api_key") String api_key);


    @GET("game")
    Call<Games> getGameDetail(@Query("id") int id);
}
