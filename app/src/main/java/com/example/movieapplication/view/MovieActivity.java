package com.example.movieapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.movieapplication.R;
import com.example.movieapplication.databinding.ActivityMovieBinding;
import com.example.movieapplication.model.Movie;

public class MovieActivity extends AppCompatActivity {

    private Movie movie;
    private ActivityMovieBinding activityMovieBinding;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMovieBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie);

        Intent i = getIntent();
        if (i.hasExtra("Movie")) {
            movie = getIntent().getParcelableExtra("Movie");
            activityMovieBinding.setMovie(movie); // Set the movie object to the data binding variable
        } else {
            Toast.makeText(this, "No movie found", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}