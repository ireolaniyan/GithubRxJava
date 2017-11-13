package com.example.ire.githubrxjava;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ire Olaniyan on 11/13/17.
 *
 */

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoHolder> {
    private List<GithubRepo> mGithubRepos = new ArrayList<>();

    static class RepoHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.repo_name_tv) TextView mRepoNameTv;
        @BindView(R.id.repo_description_tv) TextView mRepoDescriptionTv;
        @BindView(R.id.repo_language_tv) TextView mLanguageTv;
        @BindView(R.id.repo_stars_tv) TextView mStargazersTv;

        private RepoHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public void setGithubRepos(@Nullable List<GithubRepo> repos) {
        mGithubRepos = repos;
        if (repos == null) {
            return;
        }
        mGithubRepos.clear();
        mGithubRepos.addAll(repos);
        notifyDataSetChanged();
    }

    @Override
    public RepoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_view, parent, false);
        return new RepoHolder(v);
    }

    @Override
    public void onBindViewHolder(RepoHolder holder, int position) {
        holder.mRepoNameTv.setText(mGithubRepos.get(position).getName());
        holder.mRepoDescriptionTv.setText(mGithubRepos.get(position).getDescription());
        holder.mLanguageTv.setText(mGithubRepos.get(position).getLanguage());
        holder.mStargazersTv.setText(mGithubRepos.get(position).getStargazersCount());
    }

    @Override
    public int getItemCount() {
        return mGithubRepos.size();
    }

}
