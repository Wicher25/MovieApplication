package com.example.movieapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.example.movieapplication.databinding.FragmentDiscoverBinding;
import com.example.movieapplication.model.Movie;
import com.example.movieapplication.viewmodel.MainActivityViewModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class DiscoverFragment extends Fragment {



    private ArrayList<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel mainActivityViewModel;
    private FragmentDiscoverBinding fragmentDiscoverBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentDiscoverBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_discover, container, false);
        return fragmentDiscoverBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);

        swipeRefreshLayout = fragmentDiscoverBinding.swipeLayout;
        swipeRefreshLayout.setColorSchemeResources(R.color.black);
        swipeRefreshLayout.setOnRefreshListener(() -> getPopularMovies());

        getPopularMovies();
    }

    private void getPopularMovies() {
        mainActivityViewModel.getAllMovies().observe(getViewLifecycleOwner(), moviesFromLiveData -> {
            movies = new ArrayList<>(moviesFromLiveData);
            showOnRecyclerView();
        });
    }

    private void showOnRecyclerView() {
        recyclerView = fragmentDiscoverBinding.rvMovies;
        movieAdapter = new MovieAdapter(requireContext(), movies);

        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
    }
}