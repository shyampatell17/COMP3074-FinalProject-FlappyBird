package ca.gbc.flappybird;

import java.util.Random;

public class TubeCollection {
    private int xTube; // This will hold the X coordinate
    private int upTubeCollection_Y;
    private Random rand;
    public TubeCollection(int xTube, int upTubeCollection_Y) {
        this.xTube = xTube;
        this.upTubeCollection_Y = upTubeCollection_Y;
        rand = new Random();
    }



    // Get Method for the up and down Tube in the game
    public int getUpTubeCollection_Y(){
        return upTubeCollection_Y;
    }
    public int getXtube(){
        return xTube;
    }

    // Get Methods for Up and down tubes
    public int getUpTube_Y(){
        return upTubeCollection_Y - AppHolder.getBitmapControl().getTubeHeight();
    }
    public int getDownTube_Y(){
        return upTubeCollection_Y + AppHolder.tubeGap;
    }

    // Set Methods for Up and down tubes
    public void setXtube(int x_Tube){
        this.xTube = x_Tube;
    }
    public void setUpTubeCollection_Y(int upTubeCollection_Y){
        this.upTubeCollection_Y = upTubeCollection_Y;
    }

}
