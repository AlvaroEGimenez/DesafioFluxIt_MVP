package com.example.alvaro.desafiofluxit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetMovieInteractor implements MainContract.GetMovieInteractor{


    @Override
    public void getMovieList(final OnFinishedListener onFinishedListener) {
        GetMovieDataService getMovieDataService = RetrofitInstance.getRetrofitInstance().create(GetMovieDataService.class);
        Call<ResponseModel> call = getMovieDataService.getMoviesYear();

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                onFinishedListener.onFinished(response.body().getResults());
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                onFinishedListener.onFailure(t);

            }
        });
    }
}
