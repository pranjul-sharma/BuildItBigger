package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.jokedisplayer.JokeDisplayActivity;
import com.udacity.gradle.builditbigger.utils.EndpointsAsyncTask;

public class MainActivityFragment extends Fragment {

    private static final String TAG = MainActivityFragment.class.getSimpleName();
    ProgressBar progressBar;
    EndpointsAsyncTask asyncTask;
    Button btnTellJoke;

    private InterstitialAd interstitialAd;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        progressBar = root.findViewById(R.id.progress_indicator);
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        btnTellJoke = root.findViewById(R.id.btn_joke);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        Log.v("MAIN","I am here.");
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        interstitialAd = new InterstitialAd(getContext());
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.loadAd(new AdRequest.Builder().build());

        interstitialAd.setAdListener(new AdListener(){

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                tellJoke();
                //Load ad for next time
                interstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });

        btnTellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    Toast.makeText(getContext(),R.string.failed_to_load,Toast.LENGTH_LONG).show();
                    tellJoke();
                }
            }
        });
        return root;

    }

    public void tellJoke() {

        if (asyncTask != null)
            asyncTask.cancel(true);
         asyncTask = new EndpointsAsyncTask(){
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
                intent.putExtra(JokeDisplayActivity.JOKE_TO_DISPLAY,s);
                getContext().startActivity(intent);
            }
        };
        asyncTask.execute();
    }
}
