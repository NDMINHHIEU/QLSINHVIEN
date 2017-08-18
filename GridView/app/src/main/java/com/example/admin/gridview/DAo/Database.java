package com.example.admin.gridview.DAo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin on 8/16/2017.
 */

public class Database extends SQLiteOpenHelper {
    public Database(Context context) {
        super(context, "QLSV", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table sinhvien (MSSV text primary key, Ten nvarchar(100))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists sinhvien";
        db.execSQL(sql);
        onCreate(db);
    }
}
