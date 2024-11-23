package ca.gbc.flappybird;

public class FlyingBird {

    private int birdX;
    private int birdY;
    private int currentFrame;
    private int velocity;
    public static int maximumFrame;
    public FlyingBird() {

        // Logic for keeping the bird in centre at the start of the screen
        birdX = AppHolder.SCRN_WIDTH_X/2 - AppHolder.getBitmapControl().getBirdWidth()/2;
        birdY = AppHolder.SCRN_HEIGHT_Y/2 - AppHolder.getBitmapControl().getBirdHeight()/2;

        // Starting Frame
        currentFrame = 0;
        // Maximum Frame
        maximumFrame = 2;
    }

    // Getter and Setter method for Current Frame
    public int getCurrentFrame(){
        return currentFrame;
    }
    public void setCurrentFrame(int currentFrame){
        this.currentFrame = currentFrame;
    }

    // Getter Method for X and Y axis
    public int getX(){
        return birdX;
    }
    public int getY(){
        return birdY;
    }

    // Setter Method for X and Y axis
    public void setX(int birdX){
        this.birdX = birdX;
    }
    public void setY(int birdY){
        this.birdY = birdY;
    }

    // Getter and Setter Method for speed
    public int getVelocity(){
        return velocity;
    }
    public void setVelocity(int velocity){
        this.velocity = velocity;
    }
}
