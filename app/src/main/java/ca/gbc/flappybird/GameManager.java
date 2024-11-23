package ca.gbc.flappybird;

import android.graphics.Canvas;

public class GameManager {
    BgImage bgImage;
    FlyingBird bird;
    public GameManager() {
        bgImage = new BgImage();
        bird = new FlyingBird();
    }

    // Method for the flyingBird Animation
    public void birdAnimation(Canvas canvas){
        int currentFrame = bird.getCurrentFrame();
        canvas.drawBitmap(AppHolder.getBitmapControl().getBird(currentFrame), bird.getX(), bird.getY(), null);
        currentFrame++;
        if(currentFrame > bird.maximumFrame){
            currentFrame = 0;
        }
        bird.setCurrentFrame(currentFrame);
    }

    // This method is for moving of the background image on the screen (Scrolling Image)
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
