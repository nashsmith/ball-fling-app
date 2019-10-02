package com.example.assignmenteight;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Barrier extends GameObject{

    private static final int THICKNESS = 60; //thickness of the barrier
    private Paint paint = new Paint();
    private int length;
    private boolean isAlreadyColliding = false;

    /*Constructor*/
    public Barrier(float startX, float startY, int rectLength){
        super(startX, startY); //call GameObject constructor
        length = rectLength; //set length
        paint.setColor(Color.WHITE);
    }

    /*Draw the barrier*/
    @Override
    public void Draw(Canvas canvas){
        int left = (int)x; //Distance from left side of screen to left wall
        int top = (int)y; //Distance from top of screen to top wall
        int right = left + length; //Distance from left side of screen to right wall
        int bottom = top + THICKNESS; //Distance from top of screen to bottom wall

        Rect rec = new Rect(left, top, right, bottom);
        canvas.drawRect(rec, paint); //Draw the barrier
    }

    /*Check if the ball passed in is colliding with this barrier*/
    public boolean collidesWith(Ball ball){
        float testX = ball.x;
        float testY = ball.y;
        boolean isSideCollison = false;
        boolean isVertCollision = false;
        boolean isTop = false;


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
            isTop = true;
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

        if(distance <= Ball.BALL_RADIUS) {
            if(isTop){
                ball.dy -= 0.21; //Equal and opposite force against gravity
            }
            if(!isAlreadyColliding){
                isAlreadyColliding = true;
                if(isSideCollison && isVertCollision){
                    if(Math.abs(ball.dx)> Math.abs(ball.dy))
                        ball.dy = -ball.dy*Ball.BOUNCINESS;
                    else
                        ball.dx = -ball.dx*Ball.BOUNCINESS;


                }
                else if(isSideCollison){
                    ball.dx = -ball.dx*Ball.BOUNCINESS;

                }
                else if(isVertCollision){
                    ball.dy = -ball.dy*Ball.BOUNCINESS;
                }
                return true;
            }else{
                return false;
            }
        }
        isAlreadyColliding = false;
        return false;
    }
}
