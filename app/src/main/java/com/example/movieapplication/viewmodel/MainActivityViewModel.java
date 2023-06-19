package com.example.movieapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.movieapplication.model.Movie;
import com.example.movieapplication.model.MovieRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;

    public MainActivityViewModel(@NonNull Application application)
    {
        super(application);
        movieRepository = new MovieRepository(application);
    }

    //LiveData
    public LiveData<List<Movie>> getAllMovies()
    {
        return movieRepository.getMutableLiveData();
    }


}
