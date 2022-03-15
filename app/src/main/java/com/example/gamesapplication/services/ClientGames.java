package com.example.gamesapplication.services;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientGames {
    public static final String BASE_URL = "https://www.freetogame.com/api/"; //game?id=452
    private static Retrofit retrofit;

    public static Retrofit getApiClient() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.level(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(20, TimeUnit.SECONDS)
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(interceptor).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


/*
    Request request = new Request.Builder()
            .url("https://free-to-play-games-database.p.rapidapi.com/api/game?id=452")
            .get()
            .addHeader("x-rapidapi-host", "free-to-play-games-database.p.rapidapi.com")
            .addHeader("x-rapidapi-key", "SIGN-UP-FOR-KEY")
            .build();

    Response response = client.newCall(request).execute();

*/
}

