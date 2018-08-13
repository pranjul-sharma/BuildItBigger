package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.udacity.gradle.builditbigger.jokedisplayer.JokeDisplayActivity;
import com.udacity.gradle.builditbigger.utils.EndpointsAsyncTask;

public class MainActivityFragment extends Fragment {

    ProgressBar progressBar;
    Button btnTellJoke;
    EndpointsAsyncTask asyncTask;

    public MainActivityFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = getLayoutInflater().inflate(R.layout.fragment_main, container, false);
        progressBar = root.findViewById(R.id.progress_indicator);
        btnTellJoke = root.findViewById(R.id.btn_joke);

        btnTellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tellJoke();
            }
        });
        return root;
    }

    public void tellJoke() {
        if (asyncTask != null)
            asyncTask.cancel(true);
        asyncTask = new EndpointsAsyncTask() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                if (!progressBar.isShown())
                    progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (progressBar.isShown())
                    progressBar.setVisibility(View.INVISIBLE);

                Intent intent = new Intent(getContext(), JokeDisplayActivity.class);
                intent.putExtra(JokeDisplayActivity.JOKE_TO_DISPLAY, s);
                getContext().startActivity(intent);
            }
        };
        asyncTask.execute();
    }

}
