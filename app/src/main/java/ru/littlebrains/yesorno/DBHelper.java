package ru.littlebrains.yesorno;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import trikita.log.Log;

/**
 * Created by Evgeny on 19.05.2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE = "question";

    public DBHelper(Context context) {
        super(context, "YesOrNo", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("--- onCreate database ---");
        // создаем таблицу с полями
        db.execSQL("create table  " + TABLE + "  ("
                + "id integer primary key autoincrement,"
                + "timestamp int,"
                + "question text,"
                + "answer boolean"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}