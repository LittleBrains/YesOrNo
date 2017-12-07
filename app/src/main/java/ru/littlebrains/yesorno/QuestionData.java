package ru.littlebrains.yesorno;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgeny on 18.05.2016.
 */
public class QuestionData {

    private Activity mActivity;

    public QuestionData(Activity activity){
        this.mActivity = activity;
    }

    public List<QuestionModel> get(){
        List<QuestionModel> result = null;
        DBHelper dbHelper = new DBHelper(mActivity);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c;
        c = db.query(DBHelper.TABLE, null, null, null, null, null, "timestamp DESC");
        if (c.moveToFirst()) {
            result = new ArrayList<>();
            int timestampCI = c.getColumnIndex("timestamp");
            int questionCI = c.getColumnIndex("question");
            int answer = c.getColumnIndex("answer");
            do {
                QuestionModel model = new QuestionModel();
                model.question = c.getString(questionCI);
                model.answer = c.getInt(answer);
                model.timestamp = c.getInt(timestampCI);
                result.add(model);
            } while (c.moveToNext());
        } else {
            result = null;
        }
        c.close();
        return result;
    }

    public void save(QuestionModel model){
        DBHelper dbHelper = new DBHelper(mActivity);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("timestamp", model.timestamp);
        cv.put("question", model.question);
        cv.put("answer", model.answer);
        db.insert(DBHelper.TABLE, null, cv);
    }

    public void clearBD() {
        DBHelper dbHelper = new DBHelper(mActivity);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DBHelper.TABLE, null, null);
    }
}
