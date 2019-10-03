package com.example.assignmenteight;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HighscoreAdapter extends ArrayAdapter<String> {

    public HighscoreAdapter(Context context, int resID, String[] items) {
        super(context, resID, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        ((TextView) v).setTextColor(Color.WHITE);
        return v;
    }

}
