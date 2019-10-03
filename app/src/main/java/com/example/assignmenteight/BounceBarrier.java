package com.example.assignmenteight;

import android.graphics.Color;

public class BounceBarrier extends Barrier {

    public BounceBarrier(float startX, float startY, int rectWidth, int rectLength){
        super(startX, startY, rectWidth, rectLength, Color.WHITE);
    }

    @Override
    public void collisionAction(Ball ball, boolean isSideCollision, boolean isVertCollision, boolean isTop){
        if(isTop){
            ball.dy -= 0.21; //Equal and opposite force against gravity
        }
        if(isSideCollision && isVertCollision){
            if(Math.abs(ball.dx)> Math.abs(ball.dy))
                ball.dy = -ball.dy*Ball.BOUNCINESS;
            else
                ball.dx = -ball.dx*Ball.BOUNCINESS;
        }
        else if(isSideCollision){
            ball.dx = -ball.dx*Ball.BOUNCINESS;

        }
        else if(isVertCollision){
            ball.dy = -ball.dy*Ball.BOUNCINESS;
        }
    }
}
