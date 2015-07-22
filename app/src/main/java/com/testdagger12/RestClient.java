package com.testdagger12;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * @author vgrec, created on 7/21/15.
 */
public interface RestClient {

    @GET("/repositories")
    void getRepos(Callback<List<Repo>> callback);

}
