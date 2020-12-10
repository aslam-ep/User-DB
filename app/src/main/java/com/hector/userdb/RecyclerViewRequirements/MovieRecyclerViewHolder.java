package com.hector.userdb.RecyclerViewRequirements;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hector.userdb.R;

public class MovieRecyclerViewHolder extends RecyclerView.ViewHolder {

    TextView title,overview,release_date,vote_average;
    ImageView poster;

    public MovieRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.title);
        overview = itemView.findViewById(R.id.overview);
        release_date = itemView.findViewById(R.id.release_date);
        vote_average = itemView.findViewById(R.id.vote_average);
        poster = itemView.findViewById(R.id.posterImage);

    }
}
