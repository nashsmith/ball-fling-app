package com.example.assignmenteight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.GestureDetector;
import android.view.MotionEvent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.lang.Math.abs;


public class Ball extends GameObject{
    //Constants
    //Ball parameters
    public static final int BALL_RADIUS = 40;
    public static final int BALL_MASS = 256;
    public static final float BOUNCINESS = (float)0.8;
    private static Random random = new Random();
    //Datafields
    //Ball speed
    public float dx = 0;
    public float dy = 0;
    //Ball options
    public boolean isFlingable = true;
    //Ball color
    public Paint color;
    //Gesture Detector
    private GestureDetector gestureDetector;
    private List<String>colorList;



    //Constructor
    public Ball(Context context, float startX, float startY, Paint p){
        super(startX, startY);
        color = p;
        colorList = new ArrayList<>(Arrays.asList("#06D6A0","#EF476F","#F8FFE5", "#0FFF95"));

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
            return gestureDetector.onTouchEvent(event);
        }
        return true;
    }

    @Override
    public void Draw(Canvas canvas) {
        //Gravity
        if(!isFlingable){
            dy += 0.2;
        }
        //If the ball will still be on the screen...
        if (x + dx > BALL_RADIUS && x + dx < screenWidth - BALL_RADIUS)
            x = x + dx; //Move the ball
        else //Else if we are hitting a wall, bounce!
            dx = (-dx * BOUNCINESS);
        if (y + dy > BALL_RADIUS && y + dy < screenHeight - BALL_RADIUS)
            y = y + dy;
        else
            dy = (-dy * BOUNCINESS);
        //Draw the ball in the new position
        canvas.drawCircle(x, y, BALL_RADIUS, color);
    }

    public boolean collidesWith(Ball ball){
        return false;
    }

    public void Reset() {
        x = screenWidth / 2;
        y = screenHeight - 100;
        dy = 0;
        dx = 0;
        isFlingable = true;
        color.setColor(Color.parseColor(colorList.get(random.nextInt(colorList.size()))));
    }
}
