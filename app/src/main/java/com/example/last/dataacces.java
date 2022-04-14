package com.example.last;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class dataacces extends SQLiteOpenHelper {
    public dataacces(@Nullable Context context) {
        super(context, "userdata", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table user(iduser varchar(100) primary key,email varchar(100),pass varchar(100),name varchar(100),tel varchar(100),daten date)");
        sqLiteDatabase.execSQL("create table panier(codevoyage varchar(100) primary key,heuredepart time,villedepart varchar(100), heurearrivee time, villearrivee varchar(100), prixvoyage varchar(100), dated date, nbrplaces varchar(100),img varchar(100),count int)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table IF EXISTS user");
        sqLiteDatabase.execSQL("drop table IF EXISTS panier");
        this.onCreate(sqLiteDatabase);
    }







    public static void miseajour(String query){
        dataacces da = new dataacces(Globalapp.getAppcontext());
        SQLiteDatabase db = da.getWritableDatabase();
        db.execSQL(query);
    }

    public static void delete(){
        dataacces da = new dataacces(Globalapp.getAppcontext());
        SQLiteDatabase db = da.getWritableDatabase();
        db.execSQL("drop table IF EXISTS user");
        db.execSQL("drop table IF EXISTS panier");
        db.execSQL("create table user(iduser varchar(100) primary key,email varchar(100),pass varchar(100),name varchar(100),tel varchar(100),daten date)");
        db.execSQL("create table panier(codevoyage varchar(100) primary key,heuredepart time,villedepart varchar(100), heurearrivee time, villearrivee varchar(100), prixvoyage varchar(100), dated date, nbrplaces varchar(100),img varchar(100),count varchar(100))");
    }

    public static Cursor selection(String query){
        dataacces da = new dataacces(Globalapp.getAppcontext());
        SQLiteDatabase db = da.getWritableDatabase();
        Cursor cur = db.rawQuery(query,null);
        return cur;
    }
}
