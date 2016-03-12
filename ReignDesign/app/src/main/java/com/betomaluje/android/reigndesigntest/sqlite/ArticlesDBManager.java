package com.betomaluje.android.reigndesigntest.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.betomaluje.android.reigndesigntest.models.Article;
import com.betomaluje.android.reigndesigntest.retrofit.models.Hit;

import java.util.ArrayList;

/**
 * Created by betomaluje on 3/12/16.
 */
public class ArticlesDBManager {

    private SQLiteDatabase database;
    private DBSQLiteHelper dbHelper;

    private String[] allColumns = {DBSQLiteHelper.COLUMN_ID,
            DBSQLiteHelper.COLUMN_TITLE, DBSQLiteHelper.COLUMN_AUTHOR, DBSQLiteHelper.COLUMN_DATE, DBSQLiteHelper.COLUMN_ACTIVE, DBSQLiteHelper.COLUMN_URL};

    public ArticlesDBManager(Context context) {
        dbHelper = new DBSQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Article createArticle(Hit hit) {
        Article article = Article.convertHitToArticle(hit);
        return createArticle(article.getId(), article.getTitle(), article.getAuthor(), article.getDate(), article.getUrl());
    }

    private Article createArticle(long id, String title, String author, String date, String url) {

        Article oldArticle = getArticle(id);

        //to avoid duplicate entries
        if (oldArticle == null) {
            ContentValues values = new ContentValues();
            values.put(DBSQLiteHelper.COLUMN_ID, id);
            values.put(DBSQLiteHelper.COLUMN_TITLE, title);
            values.put(DBSQLiteHelper.COLUMN_AUTHOR, author);
            values.put(DBSQLiteHelper.COLUMN_DATE, date);
            values.put(DBSQLiteHelper.COLUMN_URL, url);
            values.put(DBSQLiteHelper.COLUMN_ACTIVE, 0);

            long insertId = database.insert(DBSQLiteHelper.TABLE_ARTICLES, null, values);

            Cursor cursor = database.query(DBSQLiteHelper.TABLE_ARTICLES,
                    allColumns, DBSQLiteHelper.COLUMN_ID + " = " + insertId, null,
                    null, null, null);

            cursor.moveToFirst();

            Article newArticle = cursorToArticle(cursor);
            cursor.close();

            return newArticle;
        } else {
            return oldArticle;
        }

    }

    public Article getArticle(long id) {
        Cursor cursor = database.query(DBSQLiteHelper.TABLE_ARTICLES,
                allColumns, DBSQLiteHelper.COLUMN_ID + " = " + id, null, null, null, null);

        if (cursor.moveToFirst()) {
            Article article = cursorToArticle(cursor);
            cursor.close();
            return article;
        } else {
            cursor.close();
            return null;
        }
    }

    public void deleteArticle(Article article) {
        long id = article.getId();

        ContentValues values = new ContentValues();
        values.put(DBSQLiteHelper.COLUMN_ACTIVE, 1);

        //we do a partial delete because if the user refreshes the list, we don't want to make the same article visible again
        database.update(DBSQLiteHelper.TABLE_ARTICLES, values, DBSQLiteHelper.COLUMN_ID + " = " + id, null);
        //database.delete(DBSQLiteHelper.TABLE_ARTICLES, DBSQLiteHelper.COLUMN_ID + " = " + id, null);
    }

    public ArrayList<Article> getAllArticles() {
        ArrayList<Article> articles = new ArrayList<Article>();

        Cursor cursor = database.query(DBSQLiteHelper.TABLE_ARTICLES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Article article = cursorToArticle(cursor);

            if (article.isActive())
                articles.add(article);

            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return articles;
    }

    private Article cursorToArticle(Cursor cursor) {
        Article article = new Article();
        article.setId(cursor.getLong(cursor.getColumnIndex(DBSQLiteHelper.COLUMN_ID)));
        article.setTitle(cursor.getString(cursor.getColumnIndex(DBSQLiteHelper.COLUMN_TITLE)));
        article.setAuthor(cursor.getString(cursor.getColumnIndex(DBSQLiteHelper.COLUMN_AUTHOR)));
        article.setDate(cursor.getString(cursor.getColumnIndex(DBSQLiteHelper.COLUMN_DATE)));
        article.setActive(cursor.getInt(cursor.getColumnIndex(DBSQLiteHelper.COLUMN_ACTIVE)));
        article.setUrl(cursor.getString(cursor.getColumnIndex(DBSQLiteHelper.COLUMN_URL)));
        return article;
    }
}
