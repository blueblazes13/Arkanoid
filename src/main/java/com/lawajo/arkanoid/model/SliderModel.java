/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid.model;

/**
 *
 * @author lande
 */
public class SliderModel extends BlockModel {
   
    private int speed;
    
    
    
    /**
     * Initializes a new slider.
     * 
     * @param x The x coordinate of the slider.
     * @param y The y coordinate of the slider.
     */
    public SliderModel(int x, int y) {
        super(x, y);
        
        this.speed = 1;
    }
    
    
    /**
     * Moves the slider to the left.
     */
    public void moveLeft() {
        super.setX(super.getX() - speed);
    }
    
    
    /**
     * Moves the slider to the right.
     */
    public void moveRight() {
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
    
}
