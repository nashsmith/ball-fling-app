package com.example.assignmenteight;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


public class GameActivity extends AppCompatActivity {
    //Initialize objects
    //Screen size
    int width;
    int height;
    //Ball position
    float x;
    float y;
    //Ball speed
    float dx = 0;
    float dy = 0;
    //Ball options
    boolean isFlingable = true;
    //Ball parameters
    static final int BALL_RADIUS = 35;
    static final int BALL_MASS = 256;
    static final int BALL_TERMINAL_VELOCITY = 20;
    static final double BOUNCINESS = 0.6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Set screen orientation
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Make fullscreen
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);

        //Get screen size
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;

        //Get view
        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayoutRoot);
        //Create Graphicsview object
        GraphicsView graphicsView = new GraphicsView(this);
        //Add graphics view to layout
        constraintLayout.addView(graphicsView);
    }

    public void onclickButtonReset(View view) {
        recreate();
    }


    //Custom View for drawing the ball
    public class GraphicsView extends View {
        //Declare variables
        Paint paint = new Paint();
        GestureDetector gestureDetector;
        //Constructor
        public GraphicsView(Context context) {
            super(context);
            //Create gesture detector
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDown(MotionEvent motionEvent) {
                    return true;
                }

                @Override
                public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float velocityX, float velocityY) {
                    dx = velocityX/BALL_MASS;
                    dy = velocityY/BALL_MASS;
                    isFlingable = false;
                    return true;
                }
            });
            //Set color of the ball
            paint.setColor(getColor(R.color.colorPrimary));
            //Set initial position to the centre of the screen
            x = width/2;
            y = height - 50;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //If the ball will still be on the screen...
            if(x + dx > BALL_RADIUS && x + dx < width - BALL_RADIUS)
                x = x + dx; //Move the ball
            else //Else if we are hitting a wall, bounce!
                dx = (float)(-dx*BOUNCINESS);
            if(y + dy > BALL_RADIUS && y + dy < height - BALL_RADIUS)
                y = y + dy;
            else
                dy = (float)(-dy*BOUNCINESS);
            //Draw the ball in the new position
            canvas.drawCircle(x, y,BALL_RADIUS,paint);
            //Redraw in the next position
            invalidate();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if(isFlingable){
                if(event.getX() > BALL_RADIUS && event.getX() < width - BALL_RADIUS)
                    x = event.getX();
                if(event.getY() > BALL_RADIUS && event.getY() < height - BALL_RADIUS)
                    y = event.getY();
                if(gestureDetector.onTouchEvent(event)){
                    return true;
                }
                return super.onTouchEvent(event);
            }
            return true;
        }

    }
}
