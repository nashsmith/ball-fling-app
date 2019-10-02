package com.example.assignmenteight;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Make fullscreen
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void onclickButtonStart(View view) {
        //Create intent
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void onclickButtonHighScores(View view) {
        //Create intent
        Intent intent = new Intent(this, HighscoresActivity.class);
        startActivity(intent);
    }
}
