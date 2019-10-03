package com.example.assignmenteight;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

public abstract class Barrier extends GameObject{

    private int length; //thickness of the barrier
    private Paint paint = new Paint();
    private int width;
    protected boolean isMovingRight = true;
    protected boolean isAlreadyColliding = false;
    private static final int MOVEMENT_SPEED = 3;

    /*Constructor*/
    public Barrier(float startX, float startY, int rectWidth, int rectLength, int color){
        super(startX, startY); //call GameObject constructor
        width = rectWidth; //set width
        length = rectLength;
        paint.setColor(color);
    }

    /*Draw the barrier*/
    @Override
    public void Draw(Canvas canvas){
        if(isMovingRight){
            if((x + MOVEMENT_SPEED) + width > screenWidth){
                isMovingRight = false;
            }else{
                x = x + MOVEMENT_SPEED;
            }
        }
        if(!isMovingRight){
            if((x - MOVEMENT_SPEED) < 0){
                isMovingRight = true;
            }else{
                x = x - MOVEMENT_SPEED;
            }
        }
        int left = (int)x; //Distance from left side of screen to left wall
        int top = (int)y; //Distance from top of screen to top wall
        int right = left + width; //Distance from left side of screen to right wall
        int bottom = top + length; //Distance from top of screen to bottom wall

        Rect rec = new Rect(left, top, right, bottom);
        canvas.drawRect(rec, paint); //Draw the barrier
    }

    /*Check if the ball passed in is colliding with this barrier*/
    public boolean collidesWith(Ball ball){
        float testX = ball.x;
        float testY = ball.y;
        boolean isSideCollision = false;
        boolean isVertCollision = false;
        boolean isTop = false;


        //Check which side ball is closest to
        //left wall
        if(ball.x < this.x) {
            testX = this.x;
            isSideCollision = true;
        }
        //right wall
        else if(ball.x > this.x + this.width) {
            testX = this.x + this.width;
            isSideCollision = true;
        }
        //top wall
        if(ball.y < this.y) {
            testY = this.y;
            isVertCollision = true;
            isTop = true;
        }
        //bottom wall
        else if(ball.y > this.y + length){
            testY = this.y + length;
            isVertCollision = true;
        }


        //Check the distance from the circles center
        float distanceX = ball.x - testX;
        float distanceY = ball.y - testY;
        float distance = (float)Math.sqrt( (distanceX*distanceX) + (distanceY*distanceY));

        if(distance <= Ball.BALL_RADIUS) {
            if(isTop){
                ball.dy -= 0.21; //Equal and opposite force against gravity
            }
            if(!isAlreadyColliding){
                isAlreadyColliding = true;
                collisionAction(ball, isSideCollision, isVertCollision, isTop);
                return true;
            }else{
                return false;
            }
        }
        isAlreadyColliding = false;
        return false;
    }

    public abstract void collisionAction(Ball ball, boolean isSideCollision, boolean isVertCollision, boolean isTop);

    /*Changes position and size, multiple levels*/
    public void randomise(){
        Random ran = new Random();
        int max = (int)(GameObject.screenWidth - (Ball.BALL_RADIUS * 4));
        int min = (int)(GameObject.screenWidth/2);
        this.width = ran.nextInt((max - min) + 1) + min; //random int between max and min (min and max inclusive)
        this.x = ran.nextInt((int)(GameObject.screenWidth - width));
    }
}
