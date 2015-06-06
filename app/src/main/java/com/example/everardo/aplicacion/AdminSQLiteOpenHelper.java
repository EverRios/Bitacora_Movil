package com.example.everardo.aplicacion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Everardo on 20/abr/2015.
 */
// Crear la base de datos.
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    // Crear la tabla en la base de datos.
    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("crate table registros (id integer primary key unique, nombre text, apellidos text, examen text, nivel text, modulo text, fecha text)");
       db.execSQL("create table usuarios (id_usuario integer primary key unique, nombre text unique, apellidos text, tipo text, nivel text, fecha text, modulo text) ");

    }
    // Actualiza la tabla en la base de datos.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("drop table if exists registros");
        //db.execSQL("create table registros (id integer primary key unique, nombre text, apellidos text, examen text, nivel text, modulo text, fecha text) ");
        db.execSQL("drop table if exists usuarios");
        db.execSQL("create table usuarios (id_usuario integer primary key unique, nombre text unique, apellidos text, tipo text, nivel text, fecha text, modulo text) ");

    }
}
