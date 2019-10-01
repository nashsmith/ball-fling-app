package com.example.assignmenteight;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Barrier extends GameObject{

    private static final int THICKNESS = 60; //thickness of the barrier
    private static final Paint COLOR = new Paint(android.R.color.black); //
    private int length;
    private boolean isAlreadyColliding = false;

    /*Constructor*/
    public Barrier(float startX, float startY, int rectLength){
        super(startX, startY); //call GameObject constructor
        length = rectLength; //set length
    }

    /*Draw the barrier*/
    @Override
    public void Draw(Canvas canvas){
        int left = (int)x; //Distance from left side of screen to left wall
        int top = (int)y; //Distance from top of screen to top wall
        int right = left + length; //Distance from left side of screen to right wall
        int bottom = top + THICKNESS; //Distance from top of screen to bottom wall

        Rect rec = new Rect(left, top, right, bottom);
        canvas.drawRect(rec, COLOR); //Draw the barrier
    }

    /*Check if the ball passed in is colliding with this barrier*/
    public boolean collidesWith(Ball ball){
        float testX = ball.x;
        float testY = ball.y;
        boolean isSideCollison = false;
        boolean isVertCollision = false;


        //Check which side ball is closest to
        //left wall
        if(ball.x < this.x) {
            testX = this.x;
            isSideCollison = true;
        }
        //right wall
        else if(ball.x > this.x + this.length) {
            testX = this.x + this.length;
            isSideCollison = true;
        }
        //top wall
        if(ball.y < this.y) {
            testY = this.y;
            isVertCollision = true;
        }
        //bottom wall
        else if(ball.y > this.y + Barrier.THICKNESS){
            testY = this.y + Barrier.THICKNESS;
            isVertCollision = true;
        }


        //Check the distance from the circles center
        float distanceX = ball.x - testX;
        float distanceY = ball.y - testY;
        float distance = (float)Math.sqrt( (distanceX*distanceX) + (distanceY*distanceY));

        if(distance <= Ball.BALL_RADIUS  && !isAlreadyColliding) {
            if(isSideCollison){
                ball.dx = -ball.dx*Ball.BOUNCINESS;

            }
            if(isVertCollision){
                ball.dy = -ball.dy*Ball.BOUNCINESS;
            }

            isAlreadyColliding = true;
            return true;

        }
        isAlreadyColliding = false;
        return false;
    }
}
