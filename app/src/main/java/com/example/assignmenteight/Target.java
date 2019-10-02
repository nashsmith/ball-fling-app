package com.example.assignmenteight;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Target extends GameObject{

    private Paint brush = new Paint(); //
    private float radius;
    private boolean isAlreadyColliding = false;


    /*Constructor*/
    public Target(float X, float Y, float r){
        super(X, Y); //call GameObject constructor
        radius = r; //set radius
        brush.setColor(Color.GREEN);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeWidth(7);
    }

    /*Draw the target*/
    @Override
    public void Draw(Canvas canvas){

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
