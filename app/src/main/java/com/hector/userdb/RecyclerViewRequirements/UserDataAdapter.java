package com.hector.userdb.RecyclerViewRequirements;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hector.userdb.DataModels.UserData;
import com.hector.userdb.R;
import com.hector.userdb.SQLiteDB.DbBitmapUtility;
import com.hector.userdb.UserDetailsPage;

import java.io.Serializable;
import java.util.List;

public class UserDataAdapter extends RecyclerView.Adapter<UserRecyclerViewHolder> {

    Context context;
    List<UserData> userDataList;

    public UserDataAdapter(Context context, List<UserData> userDataList) {
        this.context = context;
        this.userDataList = userDataList;
    }

    @NonNull
    @Override
    public UserRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item_row, parent, false);
        return new UserRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRecyclerViewHolder holder, final int position) {
        holder.name.setText("Name: "+userDataList.get(position).getName());
        holder.number.setText("Number: "+userDataList.get(position).getNumber());
        holder.address.setText("Address: "+userDataList.get(position).getAddress());
        holder.profilePic.setImageBitmap(DbBitmapUtility.getImage(userDataList.get(position).getImage()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, UserDetailsPage.class);
                i.putExtra("userData", (Serializable) userDataList.get(position));
                context.startActivity(i);
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userDataList.size();
    }
}
