package com.example.assignmenteight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.GestureDetector;
import android.view.MotionEvent;

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
    //Gesture Detector
    GestureDetector gestureDetector;



    //Constructor
    public Ball(Context context, float startX, float startY, Paint p){
        //x = startX;
        //y = startY;
        super(startX, startY);
        color = p;


        //Create gesture detector
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float velocityX, float velocityY) {
                dx = velocityX / BALL_MASS;
                dy = velocityY / BALL_MASS;
                isFlingable = false;
                return true;
            }
        });

    }

    public boolean Move(MotionEvent event) {
        if (isFlingable) {
            if (event.getX() > BALL_RADIUS && event.getX() < screenWidth - BALL_RADIUS)
                x = event.getX();
            if (gestureDetector.onTouchEvent(event)) {
                return true;
            }
            return false;
        }
        return true;
    }

    @Override
    public void Draw(Canvas canvas) {
        //If the ball will still be on the screen...
        if (x + dx > BALL_RADIUS && x + dx < screenWidth - BALL_RADIUS)
            x = x + dx; //Move the ball
        else //Else if we are hitting a wall, bounce!
            dx = (float) (-dx * BOUNCINESS);
        if (y + dy > BALL_RADIUS && y + dy < screenHeight - BALL_RADIUS)
            y = y + dy;
        else
            dy = (float) (-dy * BOUNCINESS);
        //Draw the ball in the new position
        canvas.drawCircle(x, y, BALL_RADIUS, color);
    }
}
