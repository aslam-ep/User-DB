package com.hector.userdb.SQLiteDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "user_db";
    private static final int DB_VERSION = 1;

    public DataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String userTable = "CREATE TABLE userTable(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, number VARCHAR, address VARCHAR, profile_img BLOB);";

        db.execSQL(userTable);
    }


    public boolean addUser(String name, String number, String address, byte[] image){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("number", number);
        contentValues.put("address", address);
        contentValues.put("profile_img", image);

        db.insert("userTable", null, contentValues);
        db.close();

        return true;
    }

    public boolean updateProfilePic(int id, byte[] image){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("profile_img", image);

        db.update("userTable", contentValues, "id = "+id, null);

        return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String userTable = "DROP TABLE IF EXISTS userTable";

        db.execSQL(userTable);

        onCreate(db);
    }
}
