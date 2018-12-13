package com.example.alvaro.desafiofluxit;

import android.support.constraint.Guideline;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.MainView, MovieAdapter.MovieItemClick{

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    private MainContract.presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initializeviews();
        presenter = new MainPresenterImpl(this,new GetMovieInteractor());
        presenter.requestDataForServer();




    }

    private void initializeviews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setIndeterminate(true);

        recyclerView = findViewById(R.id.recyclerPeliculas);
        recyclerView.setHasFixedSize(true);

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);

    }

    @Override
    public void setDataRecyclerView(List<Movie> movieList) {
        final MovieAdapter movieAdapter = new MovieAdapter(movieList,this);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false);

        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                switch (movieAdapter.getItemViewType(i)){
                    case MovieAdapter.TYPE_FIRST_ITEM:
                        return 2;
                        case MovieAdapter.TYPE_ITEM:
                            return 1;
                        default:
                            return -1;
                }
            }
        });
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(movieAdapter);



    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(MainActivity.this,
                "Something went wrong...Error message: " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void OnMovieClick(Movie movie) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.buscar) {
            presenter.onRefreshButtonClick();
        }

        return super.onOptionsItemSelected(item);
    }
}
