package com.hector.userdb.RecyclerViewRequirements;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hector.userdb.DataModels.MovieData;
import com.hector.userdb.R;

import java.util.List;

public class MovieDataAdapter extends RecyclerView.Adapter<MovieRecyclerViewHolder> {

    Context context;
    List<MovieData> movieDataList;

    public MovieDataAdapter(Context context, List<MovieData> movieDataList) {
        this.context = context;
        this.movieDataList = movieDataList;
    }

    @NonNull
    @Override
    public MovieRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movies_item_row, parent, false);
        return new MovieRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRecyclerViewHolder holder, int position) {
        holder.title.setText("Title: "+ movieDataList.get(position).getTitle());
        holder.release_date.setText("Release Date: "+ movieDataList.get(position).getRelease_date());
        holder.vote_average.setText("Vote Average: "+ movieDataList.get(position).getVote_average());
        holder.overview.setText("Overview: "+ movieDataList.get(position).getOverview());

        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500"+movieDataList.get(position).getPoster_path())
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(Glide.with(context).load(R.drawable.ic_baseline_image_24))
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return movieDataList.size();
    }
}
