package ca.gbc.flappybird;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapControl {
    Bitmap background;
    public BitmapControl(Resources res) {
        background = BitmapFactory.decodeResource(res, R.drawable.background);
        background = imageScale(background);
    }


    public Bitmap getBackground(){
        return background;
    }
    public int getBackgroundWidth(){
        return background.getWidth();
    }
    public int getBackgroundHeight(){
        return background.getHeight();
    }

    // To make the image Scalable on the screen and fit the whole screen
    public Bitmap imageScale(Bitmap bitmap){
        float width_heightRatio = getBackgroundWidth() / getBackgroundHeight();
        int bgScaleWidth = (int)width_heightRatio * AppHolder.SCRN_WIDTH_X;

        return Bitmap.createScaledBitmap(bitmap, bgScaleWidth, AppHolder.SCRN_HEIGHT_Y, false);
    }
}
