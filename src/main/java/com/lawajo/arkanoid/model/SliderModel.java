/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid.model;

import com.lawajo.arkanoid.ArkanoidFXMLController;
import com.lawajo.arkanoid.MoveSliderTask;
import java.util.Timer;

/**
 *
 * @author lande
 */
public class SliderModel extends BlockModel {
   
    private int speed;
    public final static int WIDTH = 60;
    public final static int HEIGHT = 5;
    private Timer t;
    
    
    /**
     * Initializes a new slider.
     * 
     * @param x The x coordinate of the slider.
     * @param y The y coordinate of the slider.
     */
    public SliderModel(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
        this.speed = 1;
    }
    
    
    /**
     * Moves the slider to the left.
     * @deprecated toLeft(), toRight(), stopMoving()
     */
    @Deprecated
    public void moveLeft() {
        if(super.getX() == 0) return;
        else{
            super.setX(super.getX() - speed);
        }
    }
    
    
    /**
     * Moves the slider to the right.
     * @deprecated toLeft(), toRight(), stopMoving()
     */
    @Deprecated
    public void moveRight() {
        if(super.getX() == 560 - WIDTH) return;
        else{
            super.setX(super.getX() + speed);
        }
    }
    
    
    /**
     * Sets the speed of the slider.
     * 
     * @param speed The speed of the slider.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    
    /**
     * Moves the slider to the left.
     */
    public void toLeft(ArkanoidFXMLController controller){
        if(super.getX() == 0) return;
        MoveSliderTask slidertask = new MoveSliderTask(Direction.LEFT,this, controller);
        
        this.t = new Timer(true);
        this.t.scheduleAtFixedRate(slidertask, 0, 20);
    }
    
    
    /**
     * Moves the slider to the right.
     */
    public void toRight(ArkanoidFXMLController controller){
        if(super.getX() == 560 - WIDTH) return;
        MoveSliderTask slidertask = new MoveSliderTask(Direction.RIGHT,this, controller);
        
        this.t = new Timer(true);
        this.t.scheduleAtFixedRate(slidertask, 0, 20);
    }
    
    
    /**
     * Stops the slider.
     */
    public void stopMoving(){
        this.t.cancel();
        this.t = null;
    }
}
