package ca.gbc.flappybird;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapControl {
    Bitmap background;
    Bitmap[] FlyingBird; // Initalizing Array for the flying birds

    public BitmapControl(Resources res) {
        background = BitmapFactory.decodeResource(res, R.drawable.background);
        background = imageScale(background);
        FlyingBird = new Bitmap[3];
        // Accesing all the birds through Bitmap
        FlyingBird[0] = BitmapFactory.decodeResource(res, R.drawable.bird1);
        FlyingBird[1] = BitmapFactory.decodeResource(res, R.drawable.bird2);
        FlyingBird[2] = BitmapFactory.decodeResource(res, R.drawable.bird3);
    }

    // Get Method for FlyingBird
    public Bitmap getBird(int frame){
        return FlyingBird[frame];
    }

    // Get Methods for Flying Bird Height and Width which is used in "FlyingBird.java"
    public int getBirdWidth(){
        return FlyingBird[0].getWidth();
    }
    public int getBirdHeight(){
        return FlyingBird[0].getHeight();
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
