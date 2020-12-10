package com.hector.userdb;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hector.userdb.DataModels.UserData;
import com.hector.userdb.RecyclerViewRequirements.UserDataAdapter;
import com.hector.userdb.SQLiteDB.DataBaseHelper;
import com.hector.userdb.SQLiteDB.DbBitmapUtility;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;
    ArrayList<UserData> userDataList;

    RecyclerView recyclerView;
    ProgressBar progressBar;

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);

        userDataList = new ArrayList<>();
        dataBaseHelper = new DataBaseHelper(this);
        progressBar = findViewById(R.id.progressBar);
        floatingActionButton = findViewById(R.id.moviePageButton);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchData();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MoviePage.class));
            }
        });
    }

    private void fetchData() {
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM userTable", null);

        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String number = cursor.getString(cursor.getColumnIndex("number"));
                String address = cursor.getString(cursor.getColumnIndex("address"));
                byte[] image = cursor.getBlob(cursor.getColumnIndex("profile_img"));

                userDataList.add(new UserData(id, name, number, address, image));

                cursor.moveToNext();
            }
            displayData();
        } else{
            addUser();
        }
    }

    private void displayData() {
        UserDataAdapter userDataAdapter = new UserDataAdapter(this, userDataList);
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(userDataAdapter);
    }

    private void addUser() {
        Bitmap profileIconBoy = BitmapFactory.decodeResource(getResources(), R.drawable.boy);
        Bitmap profileIconGirl = BitmapFactory.decodeResource(getResources(), R.drawable.girl);


        dataBaseHelper.addUser("Aslam1", "89454565411", "Ep1", DbBitmapUtility.getBytes(profileIconBoy));
        dataBaseHelper.addUser("Aslam2", "89454565412", "Ep2", DbBitmapUtility.getBytes(profileIconBoy));
        dataBaseHelper.addUser("Aslam3", "89454565413", "Ep3", DbBitmapUtility.getBytes(profileIconGirl));
        dataBaseHelper.addUser("Aslam4", "89454565414", "Ep4", DbBitmapUtility.getBytes(profileIconGirl));
        dataBaseHelper.addUser("Aslam5", "89454565415", "Ep5", DbBitmapUtility.getBytes(profileIconBoy));

        dataBaseHelper.addUser("Aslam6", "89454565416", "Ep6", DbBitmapUtility.getBytes(profileIconBoy));
        dataBaseHelper.addUser("Aslam7", "89454565417", "Ep7", DbBitmapUtility.getBytes(profileIconBoy));
        dataBaseHelper.addUser("Aslam8", "89454565418", "Ep8", DbBitmapUtility.getBytes(profileIconGirl));
        dataBaseHelper.addUser("Aslam9", "89454565419", "Ep9", DbBitmapUtility.getBytes(profileIconGirl));
        dataBaseHelper.addUser("Aslam10", "89454565410", "Ep0", DbBitmapUtility.getBytes(profileIconBoy));

        fetchData();
    }
}