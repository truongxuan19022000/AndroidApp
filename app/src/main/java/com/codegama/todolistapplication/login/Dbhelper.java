package com.example.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
public class Dbhelper extends SQLiteOpenHelper {
    public static final String DBName="login.db";
    private static final String Users = "Users";
    private static final String username = "username";
    private static final String password = "password";
    private static final String CREATE_User_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS " + Users + " (" +
                    username + " TEXT NOT NULL PRIMARY KEY ," +
                    password + " TEXT NOT NULL" +
                    ")";

    public Dbhelper(Context context) {
        super(context, "login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase mydb) {
        mydb.execSQL(CREATE_User_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase mydb, int i, int i1) {
        mydb.execSQL("drop Table if exists Users");
    }
    public Boolean insertUsers(String username,String password){
        SQLiteDatabase Mydb=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result=Mydb.insert("Users",null,contentValues);
        if(result==-1)return false;
        else
            return true;
    }
    public boolean checkusername(String username){
        SQLiteDatabase Mydb=this.getWritableDatabase();
        Cursor cursor=Mydb.rawQuery("Select * from Users where username= ?",new String[]{username});
        if (cursor.getCount()>0){
            return true;
        }
        else
            return false;
    }
    public boolean checkusernamePassword(String username,String password){
        SQLiteDatabase Mydb=this.getWritableDatabase();
        Cursor cursor=Mydb.rawQuery("Select * from Users where username= ? and password=?",new String[]{username,password});
        if (cursor.getCount()>0){
            return true;
        }
        else
            return false;
    }
}
