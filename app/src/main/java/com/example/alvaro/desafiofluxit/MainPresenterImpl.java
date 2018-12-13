package com.example.alvaro.desafiofluxit;

import java.util.List;

public class MainPresenterImpl implements MainContract.presenter, MainContract.GetMovieInteractor.OnFinishedListener {

    private MainContract.MainView mainView;
    private MainContract.GetMovieInteractor getMovieInteractor;
    public static final String API_KEY = "3e7e5997eadf227426c7a06be0cd5067";

    public MainPresenterImpl(MainContract.MainView mainView, MainContract.GetMovieInteractor getMovieInteractor) {
        this.mainView = mainView;
        this.getMovieInteractor = getMovieInteractor;
    }

    @Override
    public void onDestroy() {
        mainView = null;

    }

    @Override
    public void onRefreshButtonClick() {
        if (mainView != null){
            mainView.showProgress();
        }
        getMovieInteractor.getMovieList(this);

    }

    @Override
    public void requestDataForServer() {
        getMovieInteractor.getMovieList(this);
    }


    @Override
    public void onFinished(List<Movie> movieList) {
        if (mainView != null){
            mainView.setDataRecyclerView(movieList);
            mainView.hideProgress();
        }

    }

    @Override
    public void onFailure(Throwable throwable) {
        if (mainView != null){
            mainView.onResponseFailure(throwable);
            mainView.hideProgress();
        }

    }
}
