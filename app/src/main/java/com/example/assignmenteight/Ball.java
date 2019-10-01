package com.example.assignmenteight;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Ball extends GameObject{
    //Constants
    //Ball parameters
    public static final int BALL_RADIUS = 40;
    public static final int BALL_MASS = 256;
    public static final int BALL_TERMINAL_VELOCITY = 20;
    public static final double BOUNCINESS = 0.6;
    //Datafields
    //Ball speed
    public float dx = 0;
    public float dy = 0;
    //Ball options
    public boolean isFlingable = true;
    //Ball color
    public Paint color;


    //Constructor
    public Ball(float startX, float startY, Paint p){
        x = startX;
        y = startY;
        color = p;

    }

    @Override
    public void Draw(Canvas canvas, float width, float height) {
        //If the ball will still be on the screen...
        if (x + dx > BALL_RADIUS && x + dx < width - BALL_RADIUS)
            x = x + dx; //Move the ball
        else //Else if we are hitting a wall, bounce!
            dx = (float) (-dx * BOUNCINESS);
        if (y + dy > BALL_RADIUS && y + dy < height - BALL_RADIUS)
            y = y + dy;
        else
            dy = (float) (-dy * BOUNCINESS);
        //Draw the ball in the new position
        canvas.drawCircle(x, y, BALL_RADIUS, color);
    }
}
