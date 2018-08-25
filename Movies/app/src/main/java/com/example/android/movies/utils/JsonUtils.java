package com.example.android.movies.utils;

import android.util.Log;

import com.example.android.movies.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    //Declare variables
    public static final String TAG = "JsonUtils";

    public static final String JSON_MAIN_KEY = "results";
    public static final String JSON_ID_KEY= "id";
    public static final String JSON_VOTE_COUNT_KEY= "vote_count";
    public static final String JSON_TITLE_KEY= "title";
    public static final String JSON_POPULARITY_KEY = "popularity";
    public static final String JSON_POSTER_PATH_KEY = "poster_path";
    public static final String JSON_OVERVIEW_KEY = "overview";
    public static final String JSON_RELEASE_DATE_KEY = "release_date";

    public static final String imagePath = "http://image.tmdb.org/t/p/";
    public static final String imageSize = "w185/" ;


    public static List<Movie> parseMovieJson (String json){

        List<Movie> movies = new ArrayList<>();


        try {
            JSONObject reader =  new JSONObject(json);
            JSONArray jsonArray = reader.getJSONArray(JSON_MAIN_KEY);


            for (int i =0; i < jsonArray.length();i++){
                JSONObject json_details = jsonArray.getJSONObject(i);
                String id = json_details.optString(JSON_ID_KEY);
                String voteCount = json_details.optString(JSON_VOTE_COUNT_KEY);
                String title = json_details.optString(JSON_TITLE_KEY);
                String popularity = json_details.optString(JSON_POPULARITY_KEY);
                String poster_path =  imagePath + imageSize + json_details.optString(JSON_POSTER_PATH_KEY);
                String overview = json_details.optString(JSON_OVERVIEW_KEY);
                String release_date = json_details.optString(JSON_RELEASE_DATE_KEY);

                // create movie
                Movie mv = new Movie(id,voteCount,title,popularity,poster_path,overview,release_date);
                //Add new movie to array
                movies.add(mv);
            }


            Log.i(TAG, "Successfully created movies");
            return  movies;
        } catch (JSONException e) {
            Log.i(TAG, "Could not get JSON");
            e.printStackTrace();
        }

        return null;
    }
}

