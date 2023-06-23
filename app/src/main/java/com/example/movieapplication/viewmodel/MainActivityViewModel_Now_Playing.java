package com.example.movieapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.movieapplication.model.Movie;
import com.example.movieapplication.model.MovieRepository_Now_Playing;

import java.util.List;

public class MainActivityViewModel_Now_Playing extends AndroidViewModel {


    private MovieRepository_Now_Playing movieRepository_Now_Playing;

    public MainActivityViewModel_Now_Playing(@NonNull Application application)
    {
        super(application);
        movieRepository_Now_Playing = new MovieRepository_Now_Playing(application);
    }

    //LiveData
    public LiveData<List<Movie>> getAllMovies()
    {
        return movieRepository_Now_Playing.getMutableLiveData();
    }

}
