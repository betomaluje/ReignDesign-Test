package com.betomaluje.android.reigndesigntest.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by betomaluje on 3/12/16.
 */
public class DBSQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_ARTICLES = "articles";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_URL = "url";
    public static final String COLUMN_ACTIVE = "active";

    private static final String DATABASE_NAME = "articles.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_ARTICLES + "("
            + COLUMN_ID + " integer primary key, "
            + COLUMN_TITLE + " text not null, "
            + COLUMN_AUTHOR + " text, "
            + COLUMN_DATE + " text, "
            + COLUMN_ACTIVE + " integer default 0, " //0 active, 1 inactive
            + COLUMN_URL + " text );";

    public DBSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARTICLES);
        onCreate(db);
    }
}
