package com.example.android.movies.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable{

    //Declare Variables
    private String id;
    private String vote_count;
    private String title;
    private String popularity;
    private String poster_path;
    private String overview;
    private String release_date;

    //default constructor
    public Movie() {
        this.id = "0";
        this.vote_count = "0";
        this.title = "empty";
        this.popularity = "0";
        this.poster_path = "empty";
        this.overview = "empty";
        this.release_date = "1/1/1";
    }
    public  Movie(String id, String vote_count, String title,String popularity,String poster_path, String overview,String release_date){
        this.id = id;
        this.vote_count = vote_count;
        this.title = title;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
    }

    protected  Movie(Parcel in){
        this.id = in.readString();
        this.vote_count = in.readString();
        this.title = in.readString();
        this.popularity = in.readString();
        this.poster_path = in.readString();
        this.overview = in.readString();
        this.release_date = in.readString();
    }


    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getVote_count() {
        return vote_count;
    }

    public String getTitle() {
        return title;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(vote_count);
        parcel.writeString(title);
        parcel.writeString(popularity);
        parcel.writeString(poster_path);
        parcel.writeString(overview);
        parcel.writeString(release_date);
    }
}
