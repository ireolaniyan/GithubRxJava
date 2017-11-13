package com.example.ire.githubrxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RepoAdapter mRepoAdapter = new RepoAdapter();


    @BindView(R.id.search_bar_et) EditText mSearchBarEt;
    @BindView(R.id.repo_recycler_view) RecyclerView mRecyclerView;

    @OnClick(R.id.search_button)
    public void search() {
        final String username = mSearchBarEt.getText().toString();
        if (!TextUtils.isEmpty(username)) {
            getStarredRepos(username);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setAdapter(mRepoAdapter);

    }


    private void getStarredRepos(String username) {
        GithubClient.getInstance()
                .getStarredRepos(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<GithubRepo>>() {
                    @Override public void onComplete() {
                        Log.d(TAG, "In onCompleted()");
                    }

                    @Override public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d(TAG, "In onError()");
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "In onSubscribe()");
                    }

                    @Override public void onNext(List<GithubRepo> githubRepos) {
                        Log.d(TAG, "In onNext()");
                        mRepoAdapter.setGithubRepos(githubRepos);
                    }
                });
    }


    @Override protected void onDestroy() {
        super.onDestroy();
    }
}
