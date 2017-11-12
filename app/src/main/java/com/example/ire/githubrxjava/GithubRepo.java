package com.example.ire.githubrxjava;

/**
 * Created by Ire Olaniyan on 11/12/17.
 */

public class GithubRepo {

    private final int mId;
    private final int mStargazersCount;
    private final String mName;
    private final String mHtmlUrl;
    private final String mDescription;
    private final String mLanguage;


    public GithubRepo(int id, String name, String htmlUrl, String description, String language, int stargazersCount) {
        mId = id;
        mName = name;
        mHtmlUrl = htmlUrl;
        mDescription = description;
        mLanguage = language;
        mStargazersCount = stargazersCount;
    }
}
