package com.example.android.movies.utils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    //Declare  Variables.
    final static String apiKey = ""; //Enter your api key here
    final static String PARAM_LANGUAGE = "language";
    final static String language = "en-US";
    final static String PARAM_SORTBY ="sort_by";
    final static String PARAM_ADULT = "include_adult";
    final static String includeADULT ="false";
    final static String PARAM_VIDEO = "include_video";
    final static String includeVideo = "false";
    final static String PARAM_PAGE = "page";
    final static String page = "1";

    final static String MOVIE_BASE_URL = "https://api.themoviedb.org/3/discover/movie?api_key=" + apiKey;



    /**
     * Builds the URL used to query Movie DB.
     *
     * @param movieSearchQuery The keyword that will be queried for.
     * @return The URL to use to query the weather server.
     */

    public static URL buildUrl(String sortBy){



        Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_LANGUAGE, language)
                .appendQueryParameter(PARAM_SORTBY,sortBy )
                .appendQueryParameter(PARAM_ADULT, includeADULT)
                .appendQueryParameter(PARAM_VIDEO, includeVideo)
                .appendQueryParameter(PARAM_PAGE, page)
                .build();

        URL  url = null;

        try{
            if(apiKey.equals("")) {
                Log.i("Network Utils","Missing api key");
                throw new MalformedURLException();
            }

            url = new URL(builtUri.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */

    public static String getResponseFromHttpUrl(URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try{
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if(hasInput){
                return scanner.next();
            }
            else{
                return  null;
            }


        }finally {
            urlConnection.disconnect();
        }
        

    }
}


