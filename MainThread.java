package com.example.monicapandey.hm12_pandey;

/**
 * Created by monicapandey on 8/12/16.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.Toast;

import java.util.Random;

public class MainThread extends Thread {
    MenuActivity m;
    private SurfaceHolder holder;
    private Handler handler;		// required for running code in the UI thread
    private boolean isRunning = false;
    Context context;
    Paint paint,paint2;
    int touchx, touchy;	// x,y of touch event
    boolean touched;	// true if touch happened
    private static final Object lock = new Object();

    public MainThread (SurfaceHolder surfaceHolder, Context context) {
        holder = surfaceHolder;
        this.context = context;
        handler = new Handler();
        touched = false;
    }

    public void setRunning(boolean b) {
        isRunning = b;	// no need to synchronize this since this is the only line of code to writes this variable
    }

    // Set the touch event x,y location and flag indicating a touch has happened
    public void setXY (int x, int y) {
        synchronized (lock) {
            touchx = x;
            touchy = y;
            this.touched = true;
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            // Lock the canvas before drawing
            Canvas canvas = holder.lockCanvas();
            if (canvas != null) {
                // Perform drawing operations on the canvas
                render(canvas);
                // After drawing, unlock the canvas and display it
                holder.unlockCanvasAndPost (canvas);
            }
        }
    }

    // Loads graphics, etc. used in game
    private void loadData (Canvas canvas) {
        Bitmap bmp;
        int newWidth, newHeight;
        float scaleFactor;

        // Create a paint object for drawing vector graphics
        paint = new Paint();

        // Load score bar
        // ADD CODE HERE

        // Load food bar
        bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.foodbar);
        // Compute size of bitmap needed (suppose want height = 10% of screen height)
        newHeight = (int)(canvas.getHeight() * 0.1f);
        // Scale it to a new size
        Assets.foodbar = Bitmap.createScaledBitmap (bmp, canvas.getWidth(), newHeight, false);
        // Delete the original
        bmp = null;


        // Load food bar
        bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.scorebar);
        // Compute size of bitmap needed (suppose want height = 10% of screen height)
        newHeight = (int)(canvas.getHeight() * 0.075f);
        // Scale it to a new size
        Assets.scorebar = Bitmap.createScaledBitmap (bmp, canvas.getWidth(), newHeight, false);
        // Delete the original
        bmp = null;


        // Load roach1
        bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.roach1_250);
        // Compute size of bitmap needed (suppose want width = 20% of screen width)
        newWidth = (int)(canvas.getWidth() * 0.2f);
        // What was the scaling factor to get to this?
        scaleFactor = (float)newWidth / bmp.getWidth();
        // Compute the new height
        newHeight = (int)(bmp.getHeight() * scaleFactor);
        // Scale it to a new size
        Assets.roach1 = Bitmap.createScaledBitmap (bmp, newWidth, newHeight, false);
        // Delete the original
        bmp = null;


        // Load roach2
        bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.roach2_250);
        // Compute size of bitmap needed (suppose want width = 20% of screen width)
        newWidth = (int)(canvas.getWidth() * 0.2f);
        // What was the scaling factor to get to this?
        scaleFactor = (float)newWidth / bmp.getWidth();
        // Compute the new height
        newHeight = (int)(bmp.getHeight() * scaleFactor);
        // Scale it to a new size
        Assets.roach2 = Bitmap.createScaledBitmap (bmp, newWidth, newHeight, false);
        // Delete the original
        bmp = null;

        bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.ladybug);
        // Compute size of bitmap needed (suppose want width = 20% of screen
        // width)
        newWidth = (int) (canvas.getWidth() * 0.2f);
        // What was the scaling factor to get to this?
        scaleFactor = (float) newWidth / bmp.getWidth();
        // Compute the new height
        newHeight = (int) (bmp.getHeight() * scaleFactor);
        // Scale it to a new size
        Assets.ladybug = Bitmap.createScaledBitmap(bmp, newWidth, newHeight,
                false);
        // Delete the original
        bmp = null;







        // Load the other bitmaps similarly
        // Load the other bitmaps similarly
        // Load roach1
        bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.bigbug1);
        // Compute size of bitmap needed (suppose want width = 20% of screen width)
        newWidth = (int)(canvas.getWidth() * 0.2f);
        // What was the scaling factor to get to this?
        scaleFactor = (float)newWidth / bmp.getWidth();
        // Compute the new height
        newHeight = (int)(bmp.getHeight() * scaleFactor);
        // Scale it to a new size
        Assets.superbug1 = Bitmap.createScaledBitmap (bmp, newWidth, newHeight, false);
        // Delete the original
        bmp = null;

        // Load roach1
        bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.bigbug2);
        // Compute size of bitmap needed (suppose want width = 20% of screen width)
        newWidth = (int)(canvas.getWidth() * 0.2f);
        // What was the scaling factor to get to this?
        scaleFactor = (float)newWidth / bmp.getWidth();
        // Compute the new height
        newHeight = (int)(bmp.getHeight() * scaleFactor);
        // Scale it to a new size
        Assets.superbug2 = Bitmap.createScaledBitmap (bmp, newWidth, newHeight, false);
        // Delete the original
        bmp = null;

        // Load roach3 (dead bug)
        bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.bigbug);
        // Compute size of bitmap needed (suppose want width = 20% of screen width)
        newWidth = (int)(canvas.getWidth() * 0.2f);
        // What was the scaling factor to get to this?
        scaleFactor = (float)newWidth / bmp.getWidth();
        // Compute the new height
        newHeight = (int)(bmp.getHeight() * scaleFactor);
        // Scale it to a new size
        Assets.deadbigbug = Bitmap.createScaledBitmap (bmp, newWidth, newHeight, false);
        // Delete the original
        bmp = null;



        // Load roach3 (dead bug)
        bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.roach3_250);
        // Compute size of bitmap needed (suppose want width = 20% of screen width)
        newWidth = (int)(canvas.getWidth() * 0.2f);
        // What was the scaling factor to get to this?
        scaleFactor = (float)newWidth / bmp.getWidth();
        // Compute the new height
        newHeight = (int)(bmp.getHeight() * scaleFactor);
        // Scale it to a new size
        Assets.roach3 = Bitmap.createScaledBitmap (bmp, newWidth, newHeight, false);
        // Delete the original
        bmp = null;

        // Create a bug
        Assets.bug1 = new Bug();
        Assets.bug2 = new Bug();
        Assets.bug3 = new Bug();
        Assets.ladybug1 = new LadyBug();
        Assets.superBug1 = new SuperBug();


    }

    // Load specific background screen
    private void loadBackground (Canvas canvas, int resId) {
        // Load background
        Bitmap bmp = BitmapFactory.decodeResource (context.getResources(), resId);
        // Scale it to fill entire canvas
        Assets.background = Bitmap.createScaledBitmap (bmp, canvas.getWidth(), canvas.getHeight(), false);
        // Delete the original
        bmp = null;
    }

    private void render (Canvas canvas) {
        int i, x, y;

        switch (Assets.state) {
            case GettingReady:
                // Load a special "getting ready screen"
                loadBackground (canvas, R.drawable.scaredperson);
                // Load data and other graphics needed by game
                loadData(canvas);
                // Draw the background screen
                canvas.drawBitmap (Assets.background, 0, 0, null);
                // Play a sound effect
                Assets.soundPool.play(Assets.sound_getready, 1, 1, 1, 0, 1);
                // Start a timer
                Assets.gameTimer = System.nanoTime() / 1000000000f;
                // Go to next state
                Assets.state = Assets.GameState.Starting;
                break;
            case Starting:
                // Draw the background screen
                canvas.drawBitmap (Assets.background, 0, 0, null);
                // Has 3 seconds elapsed?
                float currentTime = System.nanoTime() / 1000000000f;
                if (currentTime - Assets.gameTimer >= 3) {
                    // Load game play background
                    loadBackground (canvas, R.drawable.wood);
                    // Goto next state
                    Assets.state = Assets.GameState.Running;
                }
                break;
            case Running:
                // Draw the background screen
                canvas.drawBitmap (Assets.background, 0, 0, null);
                // Draw the score bar at top of screen

                canvas.drawBitmap(Assets.scorebar, canvas.getWidth() - Assets.scorebar.getWidth(), 0, null);
                paint.setColor(Color.WHITE); // Text Color
                // paint.setStrokeWidth(12); // Text Size
                paint.setTextSize(50);
                canvas.drawText(Assets.scorenow, 30, 50, paint);



                // ADD CODE HERE
                // Draw the foodbar at bottom of screen
                canvas.drawBitmap (Assets.foodbar, 0, canvas.getHeight()-Assets.foodbar.getHeight(), null);
                // Draw one circle for each life at top right corner of screen
                // Let circle radius be 5% of width of screen
                int radius = (int)(canvas.getWidth() * 0.05f);
                int spacing = 8; // spacing in between circles
                x = canvas.getWidth() - radius - spacing;	// coordinates for rightmost circle to draw
                y = radius + spacing;
                for (i=0; i<Assets.livesLeft; i++) {
                    paint.setColor(Color.GREEN);
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawCircle(x, y, radius, paint);
                    paint.setColor(Color.BLACK);
                    paint.setStyle(Paint.Style.STROKE);
                    canvas.drawCircle(x, y, radius, paint);
                    // Reposition to draw the next circle to the left
                    x -= (radius*2 + spacing);
                }

                Assets.mp.start();
                Assets.mp.setLooping(true);



                // Process a touch
                if (touched) {
                    // Set touch flag to false since we are processing this touch now
                    touched = false;
                    // See if this touch killed a bug
                    boolean bugKilled1 = Assets.bug1.touched(canvas, touchx, touchy);
                    if(bugKilled1){
                        Assets.score++;
                        Assets.scorenow ="Score: "+Assets.score;
                    }

                    boolean bugKilled2 = Assets.bug2.touched(canvas, touchx, touchy);
                    if(bugKilled2){
                        Assets.score++;
                        Assets.scorenow ="Score: "+Assets.score;
                    }

                    boolean bugKilled3 = Assets.bug3.touched(canvas, touchx, touchy);
                    if(bugKilled3){
                        Assets.score++;
                        Assets.scorenow ="Score: "+Assets.score;
                    }

                    boolean superbugKilled = Assets.superBug1.touched(canvas, touchx, touchy);
                    if(superbugKilled){
                        Assets.soundPool.play(Assets.sound_superbug, 1, 1, 1, 0, 1);
                        Assets.score = Assets.score + 10;
                        Assets.scorenow = "score: " + Assets.score;
                    }

                    boolean ladybugKilled = Assets.ladybug1.touched(canvas, touchx, touchy);
                    if (bugKilled1 || bugKilled2 || bugKilled3) {
                        Random rand = new Random();
                        int randomNum = rand.nextInt((3 - 1) + 1) + 1;
                        if (randomNum == 1) {
                            Assets.soundPool.play(Assets.sound_squish1, 1, 1, 1, 0, 1);
                        }else if (randomNum == 2) {
                            Assets.soundPool.play(Assets.sound_squish2, 1, 1, 1, 0, 1);
                        }else{
                            Assets.soundPool.play(Assets.sound_squish3, 1, 1, 1, 0, 1);}

                    }else {
                        Assets.soundPool.play(Assets.sound_thump, 1, 1, 1, 0, 1);
                    }

                    if (ladybugKilled) {
                        Assets.soundPool.play(Assets.sound_ladybug, 1, 1, 1,
                                0, 1);
                        Assets.state = Assets.GameState.GameEnding;
                    }



                }


                // Draw dead bugs on screen
                Assets.bug1.drawDead(canvas);
                Assets.bug2.drawDead(canvas);
                Assets.bug3.drawDead(canvas);
                Assets.ladybug1.drawDead(canvas);

                // Move bugs on screen
                Assets.bug1.move(canvas);
                Assets.bug2.move(canvas);
                Assets.bug3.move(canvas);
                Assets.ladybug1.move(canvas);

                // Bring a dead bug to life?
                Assets.bug1.birth(canvas);
                Assets.bug2.birth(canvas);
                Assets.bug3.birth(canvas);
                Assets.ladybug1.birth(canvas);
                // ADD MORE CODE HERE TO PLAY GAME

                Assets.superBug1.drawDead(canvas);
                Assets.superBug1.move(canvas);
                Assets.superBug1.birth(canvas);



                // Are no lives left?
                if (Assets.livesLeft == 0)
                    // Goto next state
                    Assets.state = Assets.GameState.GameEnding;
                break;
            case GameEnding:
                // Show a game over message
                handler.post(new Runnable() {
                    public void run() {
                        Toast.makeText(context, "Game Over", Toast.LENGTH_SHORT).show();
                    }
                });
                if (Assets.mp != null) {
                    Assets.mp.stop();
                    Assets.mp.release();
                    Assets.mp = null;

                }
                // Goto next state
                Assets.state = Assets.GameState.GameOver;
                break;
            case GameOver:
                // Fill the entire canvas' bitmap with 'black'
                paint2 = new Paint();
                canvas.drawColor(Color.WHITE);

                int xPos = (canvas.getWidth() / 2);
                int yPos = (int) ((canvas.getHeight() / 2) - ((paint2.descent() + paint2.ascent()) / 2)) ;
                //((textPaint.descent() + textPaint.ascent()) / 2) is the distance from the baseline to the center.

                paint2.setTextAlign(Paint.Align.CENTER);

                paint2.setStyle(Paint.Style.STROKE);

                paint2.setAntiAlias(true);

                paint2.setColor(Color.RED);
                paint2.setTextSize(100);
                canvas.drawText("Game Over", 350, 500, paint2);


                paint.setColor(Color.RED);
                paint.setTextSize(50);
                canvas.drawText(Assets.scorenow, xPos, yPos, paint);





                // Get the current score from preferences
                SharedPreferences sharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(context);
                int highscore = sharedPreferences.getInt("prefs_highscore", 0);

                // Check for the new high score has been reached or not
                if (Assets.score > highscore) {
                    // save the new high score
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("prefs_highscore", Assets.score);
                    editor.commit();

                    // Show a new high score message
                    handler.post(new Runnable() {
                        public void run() {
                            Assets.soundPool.play(Assets.sound_highscore, 1, 1, 1, 0, 1);
                            Toast.makeText(context,
                                    "New High Score has been reached",
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }
                break;





        }
    }
}
