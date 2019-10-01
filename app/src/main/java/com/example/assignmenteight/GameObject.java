package com.example.assignmenteight;

import android.graphics.Canvas;

public abstract class GameObject {
    // Datafields
    //Size of the screen
    public static float screenWidth;
    public static float screenHeight;
    //Object position on the screen
    float x;
    float y;

    public GameObject(float _x, float _y){
        x = _x;
        y = _y;
    }

    public abstract void Draw(Canvas canvas);
}
