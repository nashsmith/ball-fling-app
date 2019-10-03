package com.example.assignmenteight;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;


public class GameActivity extends AppCompatActivity {
    //Initialize objects
    //Screen size
    float width;
    float height;
    //Score
    TextView textViewScore;
    //Timer
    TextView textViewTimer;
    //End screen
    LinearLayout endScreen;
    //GraphicsView
    GraphicsView graphicsView;
    int score = 0;
    //List of all the game objects
    List<GameObject> objectList = new ArrayList<>();
    Ball ball;

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
        GameObject.screenWidth = width;
        GameObject.screenHeight = height;

        //Get view
        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayoutRoot);
        //Create GraphicsView object
        graphicsView = new GraphicsView(this);
        //Add graphics view to layout
        constraintLayout.addView(graphicsView);

        //Set textview score to 0 at the start
        textViewScore = findViewById(R.id.score);
        textViewScore.setText(String.format("Score: %s", String.valueOf(score)));
        //Set textview time
        textViewTimer = findViewById(R.id.timer);
        //Set endscreen to be gone at the start
        endScreen = findViewById(R.id.endScreen);
        endScreen.setVisibility(View.GONE);

    }

    public void onclickButtonReset(View view) {
        ball.Reset();
    }

    public void onclickButtonAgain(View view){
        recreate();
    }

    public void onclickButtonMenu(View view){
        //Create intent
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void onclickButtonHighScores(View view){
        //Create intent
        Intent intent = new Intent(this, HighscoresActivity.class);
        //got to highscore page
        startActivity(intent);

    }

    public void onclickButtonHighScoresSave(View view){
        //save the score
        EditText et = findViewById(R.id.username);
        String name = et.getText().toString();
        saveScore(name, score);

        //Create intent
        Intent intent = new Intent(this, HighscoresActivity.class);
        //got to highscore page
        startActivity(intent);

    }

    /*Save score and username to sharedpreferences*/
    public void saveScore(String name, int score){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("scores", MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();

        if(preferences.getInt("first", -1) < score){
            edit.putInt("first", score);
            edit.putString("firstName", name);
            edit.apply();
        }else if(preferences.getInt("second", -1) < score){
            edit.putInt("second", score);
            edit.putString("secondName", name);
            edit.apply();
        }else if(preferences.getInt("third", -1) < score){
            edit.putInt("third", score);
            edit.putString("thirdName", name);
            edit.apply();
        }else if(preferences.getInt("fourth", -1) < score){
            edit.putInt("fourth", score);
            edit.putString("fourthName", name);
            edit.apply();
        }
    }

    //Custom View for drawing the ball
    public class GraphicsView extends View {
        //Declare variables
        Paint paint = new Paint();
        boolean isOver = false;


        //Constructor
        public GraphicsView(final Context context) {
            super(context);
            //Create objects
            paint.setColor(getColor(R.color.colorPrimary));
            ball = new Ball(context, width / 2, height - 100, paint);
            //Create a countdown timer
            CountDownTimer timer = new CountDownTimer(10000, 1000) {
                @Override
                public void onTick(long l) {
                    textViewTimer.setText(String.format("Time: %d", l / 1000));

                }

                @Override
                public void onFinish() {
                    //Should exit the game and add the score to the highscore screen here
                    textViewTimer.setText("Time Up!");
                    //Save score somehow??

                    //make end screen visible
                    endScreen.setVisibility(View.VISIBLE);
                    isOver = true;
                }
            }.start();

            //Add objects to the objectsList

            /*Obstacles*/
            objectList.add(new Barrier(250, 900, 800));
            objectList.add(new Target(100, 400, 60));
            objectList.add(ball);
        }

        @Override
        protected void onDraw(Canvas canvas) {

            if(!isOver){
                for (GameObject object : objectList) {
                    object.Draw(canvas);
                    if(object.collidesWith(ball)){
                        if (object.getClass().equals(Target.class)){
                            score++;
                            textViewScore.setText(String.format("Score: %s", String.valueOf(score)));
                            ball.Reset();
                            for (GameObject b : objectList){
                                if (b.getClass().equals(Barrier.class)){
                                    ((Barrier)b).randomise();
                                }
                            }

                        }

                    }
                }

                //Redraw in the next position
                invalidate();
            }

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (ball.Move(event) && !isOver)
                return true;
            return super.onTouchEvent(event);
        }



    }
}
