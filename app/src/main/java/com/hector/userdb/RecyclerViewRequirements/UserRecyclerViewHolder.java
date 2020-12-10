package com.hector.userdb.RecyclerViewRequirements;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hector.userdb.R;

public class UserRecyclerViewHolder extends RecyclerView.ViewHolder {

    TextView name, number, address;
    ImageView profilePic;


    public UserRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.name);
        number = itemView.findViewById(R.id.number);
        address = itemView.findViewById(R.id.address);
        profilePic = itemView.findViewById(R.id.image);

    }
}
