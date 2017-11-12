package com.example.ire.githubrxjava;

import android.support.annotation.NonNull;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ire Olaniyan on 11/12/17.
 */

public class GithubClient {

    private static final String GITHUB_BASE_URL = "https://api.github.com/";

    private static GithubClient instance;
    private static GithubService githubService;

    private GithubClient() {
        final Gson gson =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

        final Retrofit retrofit =
                new Retrofit.Builder().baseUrl(GITHUB_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        githubService = retrofit.create(GithubService.class);
    }

    private static GithubClient getInstance() {
        if (instance == null) {
            instance = new GithubClient();
        }
        return instance;
    }

    public Observable<List<GithubRepo>> getStarredRepos(@NonNull String userName) {
        return githubService.getStarredRepositories(userName);
    }
}
