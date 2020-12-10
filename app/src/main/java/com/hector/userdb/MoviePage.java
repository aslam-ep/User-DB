package com.hector.userdb;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;
import com.hector.userdb.API.MyAPI;
import com.hector.userdb.API.RetrofitClient;
import com.hector.userdb.DataModels.APIResponse;
import com.hector.userdb.DataModels.MovieData;
import com.hector.userdb.RecyclerViewRequirements.MovieDataAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePage extends AppCompatActivity {
    
    RecyclerView recyclerView;
    ProgressBar progressBar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_page);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_movie_page);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = findViewById(R.id.progressBar2);
        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        fetchData();
    }

    private void fetchData() {
        MyAPI myAPI = RetrofitClient.getInstance().create(MyAPI.class);
        Call<APIResponse> call = myAPI.getResponse();
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                displayData(response.body().getResults());
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Snackbar.make(findViewById(android.R.id.content), "Check Your Connection", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void displayData(ArrayList<MovieData> results) {
        MovieDataAdapter movieDataAdapter = new MovieDataAdapter(this, results);
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setAdapter(movieDataAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}