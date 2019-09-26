package com.example.assignmenteight;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
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
    //Ball parameters
    static final int BALL_RADIUS = 70;
    static final int BALL_MASS = 8;
    static final int BALL_TERMINAL_VELOCITY = 20;
    static final double BOUNCINESS = 0.6;
    String s = "hello";

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

    //Custom View for drawing the ball
    public class GraphicsView extends View {
        //Declare variables
        Paint paint = new Paint();
        //Constructor
        public GraphicsView(Context context) {
            super(context);
            //Set color of the ball
            paint.setColor(getColor(R.color.colorPrimary));
            //Set initial position to the centre of the screen
            x = width/2;
            y = height/2;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawCircle(x, y,BALL_RADIUS,paint);
            //Redraw in the next position
            invalidate();
        }
    }
}
