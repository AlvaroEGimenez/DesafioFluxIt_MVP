package com.example.alvaro.desafiofluxit;



import java.util.List;

public interface MainContract {

    interface presenter{
        void onDestroy();
        void onRefreshButtonClick();
        void requestDataForServer();

    }

    interface MainView{
        void showProgress();
        void hideProgress();
        void setDataRecyclerView(List<Movie> movieList);
        void onResponseFailure(Throwable throwable);
    }

    interface GetMovieInteractor{

        interface OnFinishedListener{
            void onFinished(List<Movie> movieList);
            void onFailure(Throwable throwable);
        }

        void getMovieList(OnFinishedListener onFinishedListener);
    }
}
