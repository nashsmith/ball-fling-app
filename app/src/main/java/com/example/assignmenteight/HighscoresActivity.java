package com.example.assignmenteight;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class HighscoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        //Set screen orientation
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Make fullscreen
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //Get list of strings
        SharedPreferences p = getApplicationContext().getSharedPreferences("scores", MODE_PRIVATE);
        String first, firstName, second, secondName, third, thirdName, fourth, fourthName, fifth, fifthName;

        //Get the highscore names and scores
        first = Integer.toString(p.getInt("first", 0));
        firstName = p.getString("firstName", "Unknown");
        second = Integer.toString(p.getInt("second", 0));
        secondName = p.getString("secondName", "Unknown");
        third = Integer.toString(p.getInt("third", 0));
        thirdName = p.getString("thirdName", "Unknown");
        fourth = Integer.toString(p.getInt("fourth", 0));
        fourthName = p.getString("fourthName", "Unknown");
        fifth = Integer.toString(p.getInt("fifth", 0));
        fifthName = p.getString("fifthName", "Unknown");

        String[] strings = new String[5];

        //if the score isnt set, the string will be empty, otherwise it will contain name and score
        strings[0] = firstName.equals("Unknown") ? "" : (firstName + ": \t" + first);
        strings[1] = secondName.equals("Unknown") ? "" : (secondName + ": \t" + second);
        strings[2] = thirdName.equals("Unknown") ? "" : (thirdName + ": \t" + third);
        strings[3] = fourthName.equals("Unknown") ? "" : (fourthName + ": \t" + fourth);
        strings[4] = fifthName.equals("Unknown") ? "" : (fifthName + ": \t" + fifth);

        //Create adapter
        final ArrayAdapter<String> adapter = new HighscoreAdapter(this, android.R.layout.simple_list_item_1, strings);
        //Bind to listview
        ListView listView = findViewById(R.id.highscoresList);
        listView.setAdapter(adapter);
    }

    public void onclickButtonMenu(View view){
        //Create intent
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void onclickButtonStart(View view) {
        //Create intent
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
