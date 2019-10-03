package com.example.assignmenteight;

import android.graphics.Color;

public class DestroyBarrier extends Barrier {

    public DestroyBarrier(float startX, float startY, int rectWidth, int rectLength){
        super(startX, startY, rectWidth, rectLength, Color.RED);
    }

    @Override
    public void collisionAction(Ball ball, boolean isSideCollision, boolean isVertCollision, boolean isTop){
        ball.Reset();
    }
}
