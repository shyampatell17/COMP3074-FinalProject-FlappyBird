package ca.gbc.flappybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class AppHolder {

    static BitmapControl bitmapControl;
    static GameManager gameManager;
    static int SCRN_WIDTH_X;
    static int SCRN_HEIGHT_Y;
    static int gravityPull;


    public static void assign(Context context){
        // The mapScreenSize method should be the first in the assign method.
        mapScreenSize(context);
        holdGameVariable();
        bitmapControl = new BitmapControl(context.getResources());
        gameManager = new GameManager();
    }

    public static  void holdGameVariable(){
        AppHolder.gravityPull = 5;
    }

    public static BitmapControl getBitmapControl(){
        return bitmapControl;
    }


    public static GameManager getGameManager() {
        return gameManager;
    }

    private static void mapScreenSize(Context context){
        // WindowManager is used for organizing the screen
        WindowManager manager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        // This will provide size and density of logical display
        Display dsp = manager.getDefaultDisplay();
        DisplayMetrics dMetrics = new DisplayMetrics();
        dsp.getMetrics(dMetrics);

        int width = dMetrics.widthPixels;
        int height = dMetrics.heightPixels;

        AppHolder.SCRN_WIDTH_X = width;
        AppHolder.SCRN_HEIGHT_Y = height;
    }
}
