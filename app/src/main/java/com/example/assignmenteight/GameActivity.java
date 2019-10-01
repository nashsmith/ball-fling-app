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

import java.util.ArrayList;
import java.util.List;


public class GameActivity extends AppCompatActivity {
    //Initialize objects
    //Screen size
    float width;
    float height;
    //List of all the game objects
    List<GameObject> objectList = new ArrayList<>();

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
        //Create GraphicsView object
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

        Ball ball;
        GestureDetector gestureDetector;

        //Constructor
        public GraphicsView(Context context) {
            super(context);
            //Create objects
            paint.setColor(getColor(R.color.colorPrimary));
            ball = new Ball(width / 2, height - 100, paint);

            //Add objects to the objectsList
            objectList.add(ball);
            //Create gesture detector
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDown(MotionEvent motionEvent) {
                    return true;
                }

                @Override
                public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float velocityX, float velocityY) {
                    ball.dx = velocityX / Ball.BALL_MASS;
                    ball.dy = velocityY / Ball.BALL_MASS;
                    ball.isFlingable = false;
                    return true;
                }
            });


        }

        @Override
        protected void onDraw(Canvas canvas) {
            for (GameObject object:objectList) {
                object.Draw(canvas,width,height);
                
            }
            //Redraw in the next position
            invalidate();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (ball.isFlingable) {
                if (event.getX() > Ball.BALL_RADIUS && event.getX() < width - Ball.BALL_RADIUS)
                    ball.x = event.getX();
                if (gestureDetector.onTouchEvent(event)) {
                    return true;
                }
                return super.onTouchEvent(event);
            }
            return true;
        }

    }
}
