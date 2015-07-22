package com.testdagger12;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

/**
 * @author vgrec, created on 7/21/15.
 */
public class GithubListAdapter extends BaseAdapter {

    @Inject
    LayoutInflater layoutInflater;

    private List<Repo> repos;

    public void setRepos(List<Repo> repos) {
        this.repos = repos;
    }

    @Override
    public int getCount() {
        return repos.size();
    }

    @Override
    public Object getItem(int i) {
        return repos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.repo_row, parent, false);
        }

        TextView name = (TextView) view.findViewById(R.id.name);
        TextView description = (TextView) view.findViewById(R.id.description);

        Repo repo = (Repo) getItem(position);
        name.setText(repo.getName());
        description.setText(repo.getDescription());

        return view;
    }
}
