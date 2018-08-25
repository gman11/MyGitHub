package com.example.android.movies;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.android.movies.model.Movie;

import com.squareup.picasso.Picasso;

import java.util.List;




public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    //Data Structures
    private List<Movie> mMovies ;
    private Context  mContext;

    public RecyclerViewAdapter(List<Movie> mMovies, Context mContext) {
        this.mMovies = mMovies;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, final int position) {
        Log.d(TAG,"onBindViewHolder: called.");
        Log.i(TAG,"IMAGE path " + mMovies.get(position).getPoster_path() );
        Picasso.with(mContext)
                .load(mMovies.get(position).getPoster_path())
                .placeholder(R.drawable.movie_image)
                .error(R.drawable.movie_image)
                .fit()
                .into(holder.image);


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"OnClick: clicked on: " + mMovies.get(position).getTitle());

                Toast.makeText(mContext,mMovies.get(position).getTitle(),Toast.LENGTH_SHORT).show();

                launchDetailActivity(mMovies.get(position));

            }
        });

    }

    @Override
    public int getItemCount() {
        return mMovies.size(); // get number of movies.
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        //This holds our widges for recycle
        //Thus, we need to create all of our widges here.
        ImageView image;
       // TextView title;
        CardView parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.movie_item);
            parentLayout =  itemView.findViewById(R.id.parent_layout);
        }
    }
    //Launch the details activity for the selected movie
    private void launchDetailActivity(Movie movie) {
        Intent intent = new Intent(mContext,MovieDetailsActivity.class);
        intent.putExtra(MovieDetailsActivity.EXTRA_POSITION, movie);
        mContext.startActivity(intent);

    }
}



