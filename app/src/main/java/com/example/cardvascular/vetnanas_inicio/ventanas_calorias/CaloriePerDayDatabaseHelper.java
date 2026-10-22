package com.example.cardvascular.vetnanas_inicio.ventanas_calorias;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CaloriePerDayDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "calories_per_day.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_CALORIES_PER_DAY = "calories_per_day";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_FOOD_NAME = "food_name";
    public static final String COLUMN_TOTAL_CALORIES = "total_calories";

    private static final String CREATE_TABLE_CALORIES_PER_DAY = "CREATE TABLE " + TABLE_CALORIES_PER_DAY + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USERNAME + " TEXT,"
            + COLUMN_DATE + " TEXT,"
            + COLUMN_FOOD_NAME + " TEXT,"
            + COLUMN_TOTAL_CALORIES + " REAL"
            + ")";

    public CaloriePerDayDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CALORIES_PER_DAY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CALORIES_PER_DAY);
        onCreate(db);
    }

    public long insertCalories(String username, String date, String foodName, float totalCalories) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_FOOD_NAME, foodName);
        values.put(COLUMN_TOTAL_CALORIES, totalCalories);

        long newRowId = db.insert(TABLE_CALORIES_PER_DAY, null, values);
        db.close();

        return newRowId;
    }
}