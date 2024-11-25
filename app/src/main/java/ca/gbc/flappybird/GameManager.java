package ca.gbc.flappybird;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    BgImage bgImage;
    FlyingBird bird;
    static int gameState;
    ArrayList<TubeCollection> tubeCollections;
    Random rand;
    public GameManager() {
        bgImage = new BgImage();
        bird = new FlyingBird();
        gameState = 0;
        tubeCollections = new ArrayList<>();
        rand = new Random();
        generateTubeObject();
    }


    /*
      gameState == 0 : not running(rest)
      gameState == 1 : the game is running
      gameState == 2 : The game is over
   */

    public void checkGameOver() {
        // Check if bird touches the ground
        if (bird.getY() + AppHolder.getBitmapControl().getBirdHeight() >= AppHolder.SCRN_HEIGHT_Y) {
            Log.d("GameOver", "Bird hit the ground. Triggering Game Over.");
            gameState = 2; // Game over
        }

        // Optional: Check if the bird moves above the screen (if needed)
        if (bird.getY() <= 0) {
            Log.d("GameOver", "Bird hit the ground. Triggering Game Over.");
            gameState = 2; // Game over
        }
    }


    public void generateTubeObject(){
        for (int j = 0; j< AppHolder.tube_numbers; j++){
            int tubeX = AppHolder.SCRN_WIDTH_X + j*AppHolder.tubeDistance;
            int upTubeCollectionY = AppHolder.minimumTubeCollection_Y;
            rand.nextInt(AppHolder.maximumTubeCollection_Y - AppHolder.minimumTubeCollection_Y + 1);
            TubeCollection tubeCollection = new TubeCollection(tubeX,upTubeCollectionY);
            tubeCollections.add(tubeCollection);
        }
    }

    public void scrollingTube(Canvas can) {
        if (gameState == 1) {
            for (int j = 0; j < AppHolder.tube_numbers; j++) {
                if (tubeCollections.get(j).getXtube() < -AppHolder.getBitmapControl().getTubeWidth()) {
                    tubeCollections.get(j).setXtube(tubeCollections.get(j).getXtube()
                            + AppHolder.tube_numbers * AppHolder.tubeDistance);
                    int upTubeCollectionY = AppHolder.minimumTubeCollection_Y +
                            rand.nextInt(AppHolder.maximumTubeCollection_Y - AppHolder.minimumTubeCollection_Y + 1);
                    tubeCollections.get(j).setUpTubeCollection_Y(upTubeCollectionY);
                }
                tubeCollections.get(j).setXtube(tubeCollections.get(j).getXtube() - AppHolder.tubeVelocity);
                can.drawBitmap(AppHolder.getBitmapControl().getUpTube(), tubeCollections.get(j).getXtube(), tubeCollections.get(j).getUpTube_Y(), null);
                can.drawBitmap(AppHolder.getBitmapControl().getDownTube(), tubeCollections.get(j).getXtube(), tubeCollections.get(j).getDownTube_Y(), null);
            }
        }
    }



    // Method for the flyingBird Animation
    public void birdAnimation(Canvas canvas){
        if(gameState == 1){
            // If the position of bird is less that the Y axis, i.e, if bird is on the screen we will apply gravitational force
            if(bird.getY() < AppHolder.SCRN_HEIGHT_Y - AppHolder.getBitmapControl().getBirdHeight() || bird.getVelocity() < 0){
                bird.setVelocity(bird.getVelocity() + AppHolder.gravityPull);
                bird.setY(bird.getY() + bird.getVelocity());
            }
            // Check for Game Over condition
            checkGameOver();
        }
        int currentFrame = bird.getCurrentFrame();
        Log.d("Bird", "Bird Y: " + bird.getY() + ", Velocity: " + bird.getVelocity());
        canvas.drawBitmap(AppHolder.getBitmapControl().getBird(currentFrame), bird.getX(), bird.getY(), null);
        currentFrame++;
        if(currentFrame > bird.maximumFrame){
            currentFrame = 0;
        }
        bird.setCurrentFrame(currentFrame);
    }

    // This method is for moving of the background image on the screen (Scrolling Image)
    public void backgroundAnimation(Canvas canvas){
        // The background's X-coordinate (image_X_background) decreases, making it move left.
        bgImage.setX(bgImage.getX() - bgImage.getVelocity());
        if (bgImage.getX() < -AppHolder.getBitmapControl().getBackgroundWidth()){
            bgImage.setX(0);
        }
        // When the background scrolls completely off-screen, its position resets to 0, creating an infinite loop.
        canvas.drawBitmap(AppHolder.getBitmapControl().getBackground(), bgImage.getX(), bgImage.getY(), null);
        // A second copy of the background is drawn at the edge of the first, ensuring a seamless scrolling effect.
        if (bgImage.getX() <- (AppHolder.getBitmapControl().getBackgroundWidth() - AppHolder.SCRN_WIDTH_X)){
            canvas.drawBitmap(AppHolder.getBitmapControl().getBackground(), bgImage.getX()
                    +AppHolder.getBitmapControl().getBackgroundWidth(),bgImage.getY(),null);
        }
    }
}
