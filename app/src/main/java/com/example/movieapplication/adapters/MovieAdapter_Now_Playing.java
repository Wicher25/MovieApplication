package com.example.movieapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapplication.databinding.MovieListItemBinding;
import com.example.movieapplication.model.Movie;
import com.example.movieapplication.view.MovieActivity;
import com.example.movieapplication.R;

import java.util.ArrayList;

public class MovieAdapter_Now_Playing extends RecyclerView.Adapter<MovieAdapter_Now_Playing.MovieViewHolder> {

    private Context context;
    private ArrayList<Movie> movieArrayList;
    private OnItemClickListener itemClickListener;

    public MovieAdapter_Now_Playing(Context context, ArrayList<Movie> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieListItemBinding movieListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.movie_list_item, parent, false);

        return new MovieViewHolder(movieListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieArrayList.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private MovieListItemBinding movieListItemBinding;

        public MovieViewHolder(MovieListItemBinding movieListItemBinding) {
            super(movieListItemBinding.getRoot());
            this.movieListItemBinding = movieListItemBinding;

            movieListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && itemClickListener != null) {
                        itemClickListener.onItemClick(view, position);
                    }
                }
            });
        }

        public void bind(Movie movie) {
            movieListItemBinding.setMovie(movie);
            movieListItemBinding.executePendingBindings();
        }
    }
}

