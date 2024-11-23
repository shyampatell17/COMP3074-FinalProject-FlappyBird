package ca.gbc.flappybird;

import android.graphics.Canvas;
import android.os.SystemClock;
import android.view.SurfaceHolder;

public class MainThread extends Thread {

    SurfaceHolder mySurfaceHolder;
    long timeSpent;
    long kickOffTime;
    long WAIT = 31; // The time taken by the frame to refresh in milliseconds
    boolean Running;
    private static Canvas canvas;

    public MainThread(SurfaceHolder SurfaceHolder) {
        this.mySurfaceHolder = SurfaceHolder;
        Running = true;
    }

    @Override
    public void run() {
        while (Running) {
            kickOffTime = SystemClock.uptimeMillis();
            canvas = null;
            try {
                canvas = mySurfaceHolder.lockCanvas();
                // Wrapping in synchronized tab so that specific method is called
                synchronized (mySurfaceHolder) {
                    AppHolder.getGameManager().backgroundAnimation(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        mySurfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            // Amount of time spent in milliseconds
            timeSpent = SystemClock.uptimeMillis() - kickOffTime;
            if (timeSpent < WAIT) {
                try {
                    Thread.sleep(WAIT - timeSpent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public boolean isRunning() {
        return Running;
    }

    public void setIsRunning(boolean state){
        Running = state;
    }
}