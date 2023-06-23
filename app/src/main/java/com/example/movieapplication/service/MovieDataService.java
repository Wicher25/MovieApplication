package com.example.movieapplication.service;

import com.example.movieapplication.model.Result;
import com.example.movieapplication.model.Result_Now_Playing;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataService {


    //Base Url
    //https://api.themoviedb.org/3/

    //End_Point Url:
    //movie/popular?api_key=3a6384c6f100e9debec11474148ebe6e

    @GET("movie/popular")
    Call<Result> getPopularMovies(@Query("api_key")String apiKey);

    @GET("movie/now_playing")
    Call<Result_Now_Playing> getNowPlayingMovies(@Query("api_key") String apiKey);
}
