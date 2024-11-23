package ca.gbc.flappybird;

import android.graphics.Canvas;

public class GameManager {
    BgImage bgImage;
    public GameManager() {
        bgImage = new BgImage();
    }

    // This is for moving of the background image on the screen
    public void backgroundAnimation(Canvas canvas){
        bgImage.setX(bgImage.getX() - bgImage.getVelocity());
        if (bgImage.getX() < -AppHolder.getBitmapControl().getBackgroundWidth()){
            bgImage.setX(0);
        }
        canvas.drawBitmap(AppHolder.getBitmapControl().getBackground(), bgImage.getX(), bgImage.getY(), null);
        if (bgImage.getX() < -(AppHolder.getBitmapControl().getBackgroundWidth() - AppHolder.SCRN_WIDTH_X)){
            canvas.drawBitmap(AppHolder.getBitmapControl().getBackground(), bgImage.getX()
                    +AppHolder.getBitmapControl().getBackgroundWidth(),bgImage.getY(),null);
        }
    }
}
