package com.hwys.android10midexam.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.hwys.android10midexam.db.model.PostModel;
import com.hwys.android10midexam.db.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class PostDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "PostDb";
    private static final int DB_VERSION = 1;
    public PostDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tbl_User(u_id INTEGER PRIMARY KEY, u_name TEXT, password TEXT)");
        db.execSQL("CREATE TABLE tbl_Post(p_id INTEGER PRIMARY KEY, u_id INTEGER, status)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean register(UserModel model){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("u_name", model.getU_name());
        cv.put("password", model.getPassword());

        try {
            db.insert("tbl_User", null, cv);
            db.close();
            return true;
        }catch (Exception e){
            db.close();
            return false;
        }
    }

    public UserModel getLogInUser(String userName, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        UserModel model= null;
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_User WHERE u_name  ='"+userName+"' AND password ='"+password+"'", null);
        if(cursor.moveToFirst())
            model = new UserModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        cursor.close();
        db.close();
        return model;
    }

    public boolean postStatus(PostModel model){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("u_id", model.getU_id());
        cv.put("status", model.getStatus());
        try {
            db.insert("tbl_Post", null, cv);
            db.close();
            return true;
        }catch (Exception e){
            db.close();
            return false;
        }
    }

    public List<PostModel> getAllPosts(){
        List<PostModel> postModelList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql;
        Cursor cursor = db.rawQuery("SELECT p.p_id, p.u_id, u.u_name, p.status FROM tbl_Post p INNER JOIN tbl_User u ON p.u_id = u.u_id", null);
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                PostModel model = new PostModel(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3));
                postModelList.add(model);
                cursor.moveToNext();

            }
        }
        cursor.close();
        db.close();

        return postModelList;
    }
}
