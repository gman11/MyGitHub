/*
    Programmer: Jesus Guerrero
    Date: 8/18/2018
    Project: Movies 2

 */


package com.example.android.movies;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.example.android.movies.model.Movie;
import com.example.android.movies.utils.JsonUtils;
import com.example.android.movies.utils.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {


    //Declaration of variables.
    public static String TAG = "MainActivity";
    public List<Movie> mv; // holds movies  models
    public String movieList = null; // holds json string
    public String sortBy = null;

    private RecyclerView mRecyclerView;
    private Context mContext;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupSharedPreferences();
        getMovies();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);

    }

    public void getMovies(){
        // Get movies URL
        URL url = NetworkUtils.buildUrl(sortBy);
        //String moviesList
        Log.i(TAG, url.toString());
        //Async call to get movies.
        new MovieQueryTask().execute(url);

    }




    public class MovieQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String movieSearchResults = null;
            try {
                movieSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                Log.i(TAG, "ERROR something happen");
                e.printStackTrace();
            }


            return movieSearchResults;
        }

        @Override
        protected void onPostExecute(String s) {
            if(s != null && !s.equals("")){

                Log.i(TAG,s.toString());

                movieList = s.toString();
                Log.i(TAG,"Json Result: " + movieList.toString());

                //Create movies list
                mv =  JsonUtils.parseMovieJson(movieList);

                initRecyclerView();


            }
            else{Log.i(TAG, "ERROR something happen");}
        }
    }


    private void initRecyclerView(){
        Log.d(TAG,"initRecyclerView: init recyclerview.");


        mContext = this;
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use gridLayout
        mLayoutManager = new GridLayoutManager(mContext,2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify adapter
        mAdapter = new RecyclerViewAdapter(mv,mContext);
        mRecyclerView.setAdapter(mAdapter);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.pref_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings)
        {
            Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setupSharedPreferences() {
        //Shared Preferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sortBy = sharedPreferences.getString("sort_order", "popularity.desc" );
        Log.i(TAG,"Shared: " + sortBy);

        //register change listener
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

    }
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        sortBy = sharedPreferences.getString("sort_order", "popularity.desc");
        Log.i(TAG,"Shared  Changed: " + sortBy);
        getMovies();



    }
}
