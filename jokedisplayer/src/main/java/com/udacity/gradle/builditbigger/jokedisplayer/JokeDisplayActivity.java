package com.udacity.gradle.builditbigger.jokedisplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    public static final String JOKE_TO_DISPLAY = "joke";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);
        TextView textView = findViewById(R.id.tv_joke_display);
        textView.setText(getIntent().getStringExtra(JOKE_TO_DISPLAY));
    }
}
