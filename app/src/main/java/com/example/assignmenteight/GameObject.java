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

    public GameObject(float startX, float startY){
        x = startX;
        y = startY;
    }

    public abstract void Draw(Canvas canvas);

    public abstract boolean collidesWith(Ball ball);
}
