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
public class BallModel {
 
    public final static int RADIUS = 10;
    
    private int x;
    private int y;
    
    private int prevX;
    private int prevY;
    
    private int dx;
    private int dy;  
    
    
    
    
    private void addY() {
        this.prevY = this.y;
        this.y += this.dy;
    }
    
    
    private void addX() {
        this.prevX = this.x;
        this.x += this.dx;
    }
    
    
    /**
     * Hits a vertical object. The ball moves and bounces back.
     * This is automaticly done by the move function.
     */
    public void hitVertical() {
        int tempX = this.x;
        this.x = this.prevX;
        this.prevX = tempX;
        this.dx *= -1;
        addY();
    }
    
    
    /**
     * Hits a horizontal object. The ball moves and bounces back.
     * This is automaticly done by the move function.
     */
    public void hitHorizontal() {
        int tempY = this.y;
        this.y = this.prevY;
        this.prevY = tempY;
        this.dy *= -1;
        addX();
    }
    
    /**
     * Sets the position of the ball.
     * 
     * @param x The x coordinate of the center of the ball.
     * @param y The y coordinate of the center of the ball.
     */
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    // Getters
    
    
    
    /**
     * Gets the x coordinate of the center of the ball.
     * 
     * @return The x coordinate.
     */
    public int getX() {
        return this.x;
    }
    
    
    /**
     * Gets the y coordinate of the center of the ball.
     * 
     * @return The y coordinate.
     */
    public int getY() {
        return this.y;
    }
}
