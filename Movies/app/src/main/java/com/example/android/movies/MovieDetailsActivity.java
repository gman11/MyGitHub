package com.example.android.movies;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.movies.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {
    private static final String TAG = "MovieDetailsActivity";
    public static final String EXTRA_POSITION = "MovieItem";
    private static final String DEFAULT_POSITION = null;
    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datails_movie);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError("intent == null");
        }

        mMovie = intent.getParcelableExtra(EXTRA_POSITION);


        if (mMovie == null) {
            // EXTRA_POSITION not found in intent
            closeOnError("mMovie == null");
            return;
        }


        ImageView movieImage = findViewById(R.id.imageView);
        //Setup GUI
        populateUI();
        Picasso.with(this) //load

                .load(mMovie.getPoster_path())
                .placeholder(R.drawable.movie_image)
                .error(R.drawable.movie_image)
                .into(movieImage);


    }
    private void closeOnError(String errorAT) {
        finish();
        Toast.makeText(this,"Error at " + errorAT, Toast.LENGTH_SHORT).show();
    }


    // Setup GUI
    private void populateUI(){
        TextView tiTle = findViewById(R.id.movie_title);
        tiTle.setText(mMovie.getTitle());

        TextView synopsis = findViewById(R.id.synopsis_movie);
        synopsis.setText(mMovie.getOverview());

        //TextView ratingLabel = findViewById(R.id.rating_movie_label);

        TextView rating = findViewById(R.id.rating_movie);
        rating.setText(mMovie.getVote_count());

        TextView date = findViewById(R.id.date_release);
        date.setText(mMovie.getRelease_date());

    }




}
