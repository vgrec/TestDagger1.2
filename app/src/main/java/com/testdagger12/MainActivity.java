package com.testdagger12;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import dagger.ObjectGraph;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ObjectGraph objectGraph;

    @Inject
    RestClient restClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view);

        objectGraph = ObjectGraph.create(new MainModule(this));
        objectGraph.inject(this);

        loadRepos();
    }

    private void loadRepos() {
        restClient.getRepos(new Callback<List<Repo>>() {
            @Override
            public void success(List<Repo> repos, Response response) {
                displayRepos(repos);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(MainActivity.this, "Could not get the repos", Toast.LENGTH_LONG).show();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void displayRepos(List<Repo> repos) {
        GithubListAdapter adapter = objectGraph.get(GithubListAdapter.class);
        adapter.setRepos(repos);
        listView.setAdapter(adapter);
        findViewById(R.id.progress_bar).setVisibility(View.GONE);
    }
}
