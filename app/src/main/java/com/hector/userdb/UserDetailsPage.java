package com.hector.userdb;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.hector.userdb.DataModels.UserData;
import com.hector.userdb.SQLiteDB.DataBaseHelper;
import com.hector.userdb.SQLiteDB.DbBitmapUtility;

public class UserDetailsPage extends AppCompatActivity {

    UserData userData;
    DataBaseHelper dataBaseHelper;

    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int CAMERA_REQUEST = 1888;

    TextView name, number, address;
    ImageView imageView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_page);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_user_details_page);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataBaseHelper = new DataBaseHelper(this);
        userData = (UserData) getIntent().getSerializableExtra("userData");

        name = findViewById(R.id.nameTextView);
        number = findViewById(R.id.numberTextView);
        address = findViewById(R.id.addressTextView);
        imageView = findViewById(R.id.imageUser);
        button = findViewById(R.id.editButton);

        name.setText("Name: "+userData.getName());
        number.setText("Number: "+userData.getNumber());
        address.setText("Address: "+userData.getAddress());
        imageView.setImageBitmap(DbBitmapUtility.getImage(userData.getImage()));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(UserDetailsPage.this, new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                }else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");

            if (dataBaseHelper.updateProfilePic(userData.getId(), DbBitmapUtility.getBytes(photo))) {
                imageView.setImageBitmap(photo);
                Snackbar.make(findViewById(android.R.id.content), "Successfully Updated Profile Picture", Snackbar.LENGTH_SHORT).show();
            }
            else
                Snackbar.make(findViewById(android.R.id.content), "Can't Update Profile Picture!", Snackbar.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(UserDetailsPage.this, MainActivity.class));
        finish();
        return super.onSupportNavigateUp();
    }
}