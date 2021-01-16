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
    public final static int WIDTH = 60;
    public final static int HEIGHT = 5; 
    
    
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
     */
    public void moveLeft() {
        if(super.getX() == 0) return;
        else{
            super.setX(super.getX() - speed);
        }
    }
    
    
    /**
     * Moves the slider to the right.
     */
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
    
}
