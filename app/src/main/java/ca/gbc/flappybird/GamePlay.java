package ca.gbc.flappybird;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GamePlay extends SurfaceView implements SurfaceHolder.Callback {

    MainThread mainThread;
    public GamePlay(Context context) {
        super(context);
        SurfaceHolder myHolder = getHolder();
        myHolder.addCallback(this);
        mainThread = new MainThread(myHolder);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        if (!mainThread.isAlive()) { // Start the thread only if it's not already running
            mainThread.setIsRunning(true);
            mainThread.start();
        }
    }


    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

        boolean retry = true;
        while (retry){
            try{
                mainThread.setIsRunning(false);
                mainThread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        AppHolder.getGameManager().gameState = 1;
        AppHolder.getGameManager().bird.setVelocity(AppHolder.JUMP_VELOCITY);
        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw background and bird animations
        AppHolder.getGameManager().backgroundAnimation(canvas);
        AppHolder.getGameManager().birdAnimation(canvas);

        // Continuously check for Game Over condition
        if (AppHolder.getGameManager().gameState == 2) {
            // Navigate to GameOver activity
            Intent gameOverIntent = new Intent(getContext(), GameOver.class);
            getContext().startActivity(gameOverIntent);

            // End the game thread
            mainThread.setIsRunning(false);
            ((Activity) getContext()).finish(); // Ensure current activity is finished
        }
    }


}
