package com.example.movieapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.movieapplication.adapters.MovieAdapter_Now_Playing;
import com.example.movieapplication.databinding.FragmentMyLibraryBinding;
import com.example.movieapplication.model.Movie;
import com.example.movieapplication.view.MovieActivity;
import com.example.movieapplication.viewmodel.MainActivityViewModel_Now_Playing;

import java.util.ArrayList;
import java.util.Random;

public class MyLibraryFragment extends Fragment {

    private MainActivityViewModel_Now_Playing mainActivityViewModel_Now_Playing;
    private FragmentMyLibraryBinding fragmentMyLibraryBinding;

    private ArrayList<Movie> movies;
    private MovieAdapter_Now_Playing movieAdapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentMyLibraryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_library, container, false);
        return fragmentMyLibraryBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainActivityViewModel_Now_Playing = new ViewModelProvider(requireActivity()).get(MainActivityViewModel_Now_Playing.class);
        fragmentMyLibraryBinding.RandomMoviesButton.setOnClickListener(v -> generateRandomMovie());
    }

    private void generateRandomMovie() {
        mainActivityViewModel_Now_Playing.getAllMovies().observe(getViewLifecycleOwner(), moviesFromLiveData -> {
            if (moviesFromLiveData != null && !moviesFromLiveData.isEmpty()) {
                int randomIndex = new Random().nextInt(moviesFromLiveData.size());
                Movie randomMovie = moviesFromLiveData.get(randomIndex);
                showOnRecyclerView(randomMovie);
            }
        });
    }

    private void showOnRecyclerView(Movie movie) {
        movies = new ArrayList<>();
        movies.add(movie);
        movieAdapter = new MovieAdapter_Now_Playing(requireContext(), movies);

        fragmentMyLibraryBinding.rvMoviesNowPlaying.setLayoutManager(new GridLayoutManager(requireContext(), 1));
        fragmentMyLibraryBinding.rvMoviesNowPlaying.setItemAnimator(new DefaultItemAnimator());
        fragmentMyLibraryBinding.rvMoviesNowPlaying.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();

        movieAdapter.setOnItemClickListener(new MovieAdapter_Now_Playing.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Movie selectedMovie = movies.get(position);

                Intent i = new Intent(requireContext(), MovieActivity.class);
                i.putExtra("Movie", selectedMovie);
                startActivity(i);
            }
        });
    }
}
