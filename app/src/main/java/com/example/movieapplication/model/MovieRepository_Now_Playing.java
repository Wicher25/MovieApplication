package com.example.movieapplication.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.movieapplication.R;
import com.example.movieapplication.service.MovieDataService;
import com.example.movieapplication.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository_Now_Playing {

    private ArrayList<Movie> movies = new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData_Now_Playing = new MutableLiveData<>();
    private Application application;

    public MovieRepository_Now_Playing(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movie>> getMutableLiveData()
    {
        MovieDataService movieDataService = RetrofitInstance.getService();

        Call<Result_Now_Playing> call = movieDataService.getNowPlayingMovies(application.getApplicationContext().getString(R.string.api_key));

        call.enqueue(new Callback<Result_Now_Playing>() {

            @Override
            public void onResponse(Call <Result_Now_Playing> call, Response<Result_Now_Playing> response) {
                Result_Now_Playing result = response.body();

                if (result != null && result.getResults() != null)
                {
                    movies = (ArrayList<Movie>) result.getResults();
                    mutableLiveData_Now_Playing.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });

        return mutableLiveData_Now_Playing;
    }



}
