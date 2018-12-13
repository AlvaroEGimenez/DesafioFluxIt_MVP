package com.example.alvaro.desafiofluxit;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Movie> movieList;
    private MovieItemClick movieItemClick;

    public static final int TYPE_FIRST_ITEM = 0;
    public static final int TYPE_ITEM = 1;

    public MovieAdapter(List<Movie> movieList, MovieItemClick movieItemClick) {
        this.movieList = movieList;
        this.movieItemClick = movieItemClick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {



        switch (viewType) {
            case TYPE_FIRST_ITEM:
                LayoutInflater layoutInflaterBig = LayoutInflater.from(viewGroup.getContext());
                final View view1 = layoutInflaterBig.inflate(R.layout.vista_completa, viewGroup, false);
                return new MovieViewHolderBig(view1);
            case TYPE_ITEM:
                LayoutInflater layoutInflaterNormal = LayoutInflater.from(viewGroup.getContext());
                final View view2 = layoutInflaterNormal.inflate(R.layout.vista_2_columnas, viewGroup, false);
                return new MovieViewHolderNormal(view2);
            default:
                return null;
        }



    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        switch (viewHolder.getItemViewType()){
            case  TYPE_FIRST_ITEM:
                MovieViewHolderBig movieViewHolderBig = (MovieViewHolderBig) viewHolder;
                movieViewHolderBig.BindMovieBig(movieList.get(i));
                break;
            case TYPE_ITEM:
                MovieViewHolderNormal movieViewHolderNormal = (MovieViewHolderNormal) viewHolder;
                movieViewHolderNormal.BindMovieNormal(movieList.get(i));
                break;
        }

    }


    @Override
    public int getItemCount() {
        return movieList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_FIRST_ITEM;
        } else return TYPE_ITEM;
    }

    final  class MovieViewHolderBig extends RecyclerView.ViewHolder {

        ImageView imageViewBig;
        TextView textViewTitleBig;
        TextView textViewReleaseBig;

        public MovieViewHolderBig(@NonNull final View itemView) {
            super(itemView);
            imageViewBig = itemView.findViewById(R.id.ivPortadaPeliculaBig);
            textViewReleaseBig = itemView.findViewById(R.id.tv_ReleaseBig);
            textViewTitleBig = itemView.findViewById(R.id.tv_TitleBig);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), movieList.get(getAdapterPosition()).getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void BindMovieBig(Movie movie) {
            String url = "https://image.tmdb.org/t/p/w780/" + movie.getBackdropPath();
            Glide.with(itemView.getContext()).load(url).into(imageViewBig);
            textViewTitleBig.setText(movie.getTitle());
            textViewReleaseBig.setText(movie.getReleaseDate());
        }
    }

    final class MovieViewHolderNormal extends RecyclerView.ViewHolder {

        ImageView imageViewNormal;
        TextView textViewTitleNormail;
        TextView textViewReleaseNormal;
        TextView textViewRatingNormal;

        public MovieViewHolderNormal(@NonNull final View itemView) {
            super(itemView);
            imageViewNormal = itemView.findViewById(R.id.ivPortadaPeliculaNormal);
            textViewTitleNormail = itemView.findViewById(R.id.tv_TitleNormal);
            textViewReleaseNormal = itemView.findViewById(R.id.tv_ReleaseNormal);
            textViewRatingNormal = itemView.findViewById(R.id.tv_RatingNormal);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), movieList.get(getAdapterPosition()).getTitle(), Toast.LENGTH_SHORT).show();
                }
            });

        }

        public void BindMovieNormal(Movie movie) {
            String url = "https://image.tmdb.org/t/p/w300/" + movie.getPosterPath();
            Glide.with(itemView.getContext()).load(url).into(imageViewNormal);
            textViewTitleNormail.setText(movie.getTitle());
            textViewReleaseNormal.setText(movie.getReleaseDate());
            textViewRatingNormal.setText(movie.getVoteCount().toString());

        }
    }


    public interface MovieItemClick {
        void OnMovieClick(Movie movie);
    }
}
