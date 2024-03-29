package com.example.assignmenteight;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Target extends GameObject{

    private Paint brush = new Paint(); //
    private float radius;
    private boolean isAlreadyColliding = false;
    private boolean isMovingRight = true;
    private static final int MOVEMENT_SPEED = 2;


    /*Constructor*/
    public Target(float X, float Y, float r){
        super(X, Y); //call GameObject constructor
        radius = r; //set radius
        brush.setColor(Color.parseColor("#FFC43D"));
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeWidth(10);
    }

    /*Draw the target*/
    @Override
    public void Draw(Canvas canvas){
        if(isMovingRight){
            if((x + MOVEMENT_SPEED) + radius > screenWidth){
            isMovingRight = false;
        }else{
            x = x + MOVEMENT_SPEED;
        }
    }
        if(!isMovingRight){
            if((x - MOVEMENT_SPEED) - radius < 0){
                isMovingRight = true;
            }else{
                x = x - MOVEMENT_SPEED;
            }
    }

        canvas.drawCircle(x, y, radius, brush);
    }

    /*Check if the ball passed in is colliding with this barrier*/
    public boolean collidesWith(Ball ball){
        //Check the distance from the circles center
        float distanceX = ball.x - x;
        float distanceY = ball.y - y;
        float distance = (float)Math.sqrt( (distanceX*distanceX) + (distanceY*distanceY));

        if(distance <= Ball.BALL_RADIUS + radius) {
            if(!isAlreadyColliding){
                isAlreadyColliding = true;
                return true;
            }else{
                return false;
            }

        }
        isAlreadyColliding = false;
        return false;
    }
}
