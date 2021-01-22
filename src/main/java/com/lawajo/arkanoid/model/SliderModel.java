/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid.model;

import com.lawajo.arkanoid.MoveSliderTask;
import java.util.Timer;

/**
 *
 * @author lande
 */
public class SliderModel extends BlockModel {
   
    private int speed;
    public int width = 60;
    public int height = 5;
    private transient Timer rightTimer;
    private transient Timer leftTimer;
    private Boolean isMovingLeft;
    private Boolean isMovingRight;
    
    
    /**
     * Initializes a new slider.
     * 
     * @param x The x coordinate of the slider.
     * @param y The y coordinate of the slider.
     */
    public SliderModel(int x, int y) {
        super(x, y, 60, 5);
        this.speed = 3;
        this.isMovingLeft = false;
        this.isMovingRight = false;
    }
    
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    
    /**
     * Moves the slider to the left.
     */
    public void moveLeft() {
        if(super.getX() <= 5) return;
        
        super.setX(super.getX() - speed);
        
    }
    
    
    /**
     * Moves the slider to the right.
     */
    public void moveRight() {
        if(super.getX() >= 560 - WIDTH) return;
        
        super.setX(super.getX() + speed);
        
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
    public void toLeft() {
        if(super.getX() <= 0 || this.isMovingLeft) return;
        MoveSliderTask slidertask = new MoveSliderTask(Direction.LEFT,this);
        
        this.leftTimer = new Timer(true);
        this.leftTimer.scheduleAtFixedRate(slidertask, 0, 20);
        this.isMovingLeft = true;
    }
    
    
    /**
     * Moves the slider to the right.
     */
    public void toRight() {
        if(super.getX() >= 560 - WIDTH || this.isMovingRight) return;
        MoveSliderTask slidertask = new MoveSliderTask(Direction.RIGHT,this);
        
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