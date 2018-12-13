package com.example.alvaro.desafiofluxit;

import retrofit2.Call;
import retrofit2.http.GET;


public interface GetMovieDataService {



    @GET("discover/movie?primary_release_date.gte=2018-01-01&primary_release_date.lte=2018-12-13&api_key=3e7e5997eadf227426c7a06be0cd5067")
    Call<ResponseModel> getMoviesYear();
}
