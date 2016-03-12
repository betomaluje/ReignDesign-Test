package com.betomaluje.android.reigndesigntest.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.betomaluje.android.reigndesigntest.retrofit.models.Hit;

/**
 * Created by betomaluje on 3/12/16.
 */
public class Article implements Parcelable {

    private long id;
    private String title;
    private String author;
    private String date;
    private String url;
    private int active = 0;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public boolean isActive() {
        return active == 0;
    }

    public Article() {
    }

    public static Article convertHitToArticle(Hit hit) {
        Article article = new Article();
        article.setId(hit.getStoryId());
        article.setTitle(hit.getTrueTitle());
        article.setAuthor(hit.getAuthor());
        article.setDate(hit.getCreatedAt());
        article.setUrl(hit.getTrueUrl());
        return article;
    }

    protected Article(Parcel in) {
        id = in.readLong();
        title = in.readString();
        author = in.readString();
        date = in.readString();
        url = in.readString();
        active = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(date);
        dest.writeString(url);
        dest.writeInt(active);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Article> CREATOR = new Parcelable.Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };
}
