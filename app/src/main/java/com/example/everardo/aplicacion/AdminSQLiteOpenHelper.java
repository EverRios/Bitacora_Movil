package com.example.everardo.aplicacion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Everardo on 20/abr/2015.
 */
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuarios (id_usuario integer primary key unique, user text unique, password text, nombre text, apellido_p text, apellido_m text) ");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists usuarios");
        db.execSQL("create table usuarios (id_usuario integer primary key unique, user text unique, password text, nombre text, apellido_p text, apellido_m text) ");



    }
}
