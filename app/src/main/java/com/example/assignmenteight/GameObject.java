package com.example.assignmenteight;

import android.graphics.Canvas;

public abstract class GameObject {
    // Datafields
    //Object position on the screen
    float x;
    float y;

    public abstract void Draw(Canvas canvas, float screenWidth, float screenHeight);
}
