package com.example.movieapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieapplication.adapters.MovieAdapter;

import com.example.movieapplication.databinding.FragmentMyLibraryBinding;
import com.example.movieapplication.model.Movie;
import com.example.movieapplication.viewmodel.MainActivityViewModel;
import com.example.movieapplication.viewmodel.MainActivityViewModel_Now_Playing;

import java.util.ArrayList;

public class MyLibraryFragment extends Fragment {


    private ArrayList<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private MainActivityViewModel_Now_Playing mainActivityViewModel_Now_Playing;
    private FragmentMyLibraryBinding fragmentMyLibraryBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentMyLibraryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_library, container, false);
        return fragmentMyLibraryBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mainActivityViewModel_Now_Playing = new ViewModelProvider(requireActivity()).get(MainActivityViewModel_Now_Playing.class);

        swipeRefreshLayout = fragmentMyLibraryBinding.swipeLayoutNowPlaying;
        swipeRefreshLayout.setColorSchemeResources(R.color.black);
        swipeRefreshLayout.setOnRefreshListener(() -> getNowPlayingMovies());

        getNowPlayingMovies();
    }

    private void getNowPlayingMovies() {
        mainActivityViewModel_Now_Playing.getAllMovies().observe(getViewLifecycleOwner(), moviesFromLiveData -> {
            movies = new ArrayList<>(moviesFromLiveData);
            showOnRecyclerView();
        });
    }

    private void showOnRecyclerView() {
        recyclerView = fragmentMyLibraryBinding.rvMoviesNowPlaying;
        movieAdapter = new MovieAdapter(requireContext(), movies);

        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
    }
}