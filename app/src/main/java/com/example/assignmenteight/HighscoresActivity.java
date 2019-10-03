package com.example.assignmenteight;

import android.content.Intent;
import android.os.Bundle;
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
        //String[] strings = getResources().getStringArray(R.array.listArray);
        String strings[] = {"hi","hi"};
        //Create adapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strings);
        //Bind to listview
        ListView listView = findViewById(R.id.highscoresList);
        listView.setAdapter(adapter);

    }
}
