package com.testdagger12;

import android.content.Context;
import android.view.LayoutInflater;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

/**
 * @author vgrec, created on 7/21/15.
 */

@Module(
        injects = {
                GithubListAdapter.class,
                MainActivity.class
        }
)
public class MainModule {

    private final Context context;

    public MainModule(Context context) {
        this.context = context;
    }

    @Provides
    public LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(context);
    }

    @Provides
    public RestClient provideRestClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.github.com")
                .build();
        return restAdapter.create(RestClient.class);
    }
}
