package com.example.assignmenteight;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HighscoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        //Get list of strings
        SharedPreferences p = getApplicationContext().getSharedPreferences("scores", MODE_PRIVATE);
        String first, firstName, second, secondName, third, thirdName;
        first = Integer.toString(p.getInt("first", 0));
        firstName = p.getString("firstName", "Unknown");
        second = Integer.toString(p.getInt("second", 0));
        secondName = p.getString("secondName", "Unknown");
        third = Integer.toString(p.getInt("third", 0));
        thirdName = p.getString("thirdName", "Unknown");

        String[] strings = {(firstName + ": " + first),
                (secondName + ": " + second),
                (thirdName + ": " + third)};
        //Create adapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strings);
        //Bind to listview
        ListView listView = findViewById(R.id.highscoresList);
        listView.setAdapter(adapter);

        //Intent intent = getIntent();
        //String name = intent.getStringExtra("name");
        //int score = intent.getIntExtra("score", -1);
        //saveScore(name, score);
        //SharedPreferences p = getApplicationContext().getSharedPreferences("scores", MODE_PRIVATE);
        Log.i("SCORE", p.getString("firstName", ""));
    }

    public void saveScore(String name, int score){
        SharedPreferences preferences = getSharedPreferences("scores", MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();

        if(preferences.getInt("first", -1) < score){
            edit.putInt("first", score);
            edit.putString("firstName", name);
        }else if(preferences.getInt("second", -1) < score){
            edit.putInt("second", score);
            edit.putString("secondName", name);
        }else if(preferences.getInt("third", -1) < score){
            edit.putInt("third", score);
            edit.putString("thirdName", name);
        }
    }
}
