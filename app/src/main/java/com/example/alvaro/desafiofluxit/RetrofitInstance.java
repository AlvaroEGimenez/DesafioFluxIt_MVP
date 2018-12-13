package com.example.alvaro.desafiofluxit;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
    public static final String BASE_URL = "https://api.themoviedb.org/3/";

    public static OkHttpClient getOKClient(){
        OkHttpClient client;
        OkHttpClient.Builder clientBuldier = new OkHttpClient.Builder();
        clientBuldier.followRedirects(true);

        if (BuildConfig.DEBUG){
            clientBuldier.addNetworkInterceptor(new StethoInterceptor());

        }
        client = clientBuldier.build();
        return client;
    }

    public static Retrofit getRetrofitInstance(){
        if (retrofit == null){
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getOKClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit;
    }
}
