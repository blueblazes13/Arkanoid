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
    private Timer rightTimer;
    private Timer leftTimer;
    private Boolean isMovingLeft;
    private Boolean isMovingRight;
    
    
    /**
     * Initializes a new slider.
     * 
     * @param x The x coordinate of the slider.
     * @param y The y coordinate of the slider.
     */
    public SliderModel(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
        this.speed = 1;
        this.isMovingLeft = false;
        this.isMovingRight = false;
    }
    
    
    /**
     * Moves the slider to the left.
     * @deprecated toLeft(), toRight(), stopMoving()
     */
    @Deprecated
    public void moveLeft() {
        if(super.getX() <= 0) return;
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
        if(super.getX() >= 560 - WIDTH) return;
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
        if(super.getX() <= 0 || this.isMovingLeft) return;
        MoveSliderTask slidertask = new MoveSliderTask(Direction.LEFT,this, controller);
        
        this.leftTimer = new Timer(true);
        this.leftTimer.scheduleAtFixedRate(slidertask, 0, 20);
        this.isMovingLeft = true;
    }
    
    
    /**
     * Moves the slider to the right.
     */
    public void toRight(ArkanoidFXMLController controller){
        if(super.getX() >= 560 - WIDTH || this.isMovingRight) return;
        MoveSliderTask slidertask = new MoveSliderTask(Direction.RIGHT,this, controller);
        
        this.rightTimer = new Timer(true);
        this.rightTimer.scheduleAtFixedRate(slidertask, 0, 20);
        this.isMovingRight = true;
    }
    
    
    /**
     * Stops the slider.
     */
    public void stopLeft(){
        if (!isMovingLeft) return;
        
        this.leftTimer.cancel();
        this.isMovingLeft = false;
    }
    
    
    /**
     * Stops the slider.
     */
    public void stopRight(){
        if (!isMovingRight) return;
        
        this.rightTimer.cancel();
        this.isMovingRight = false;
    }
}