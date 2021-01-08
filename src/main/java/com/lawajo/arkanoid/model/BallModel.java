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
    
    
    /**
     * Initializes a new ball.
     * 
     * @param x The x coordinate of the ball.
     * @param y The y coordinate of the ball.
     */
    
    public BallModel(int x, int y) {
        this.x = x;
        this.y = y;
        
        this.prevX = x;
        this.prevY = y;
        
        this.dx = -1;
        this.dy = -1;    
    }
    
   
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
    
     /**
     * Moves the ball to the next location.
     * 
     * @deprecated Ik denk dat het berekenen van welke kant de bal op moet als hij een blok raakt beter in de controller gebeurt.
     *             De code werkt wel 100% dus in principe kan je dit zo overnemen. Dit werkt ook direct voor de slider aangezien
     *             die block extend. Gebruik het voor nu misschien even zo en als we nog eens bellen hebben we het er wel over.
     * 
     * @param block The block the ball hits.
     */
    @Deprecated
    public void move(BlockModel block) {
        if (block == null) {
            if (this.x == BallModel.RADIUS || this.x == 500 - BallModel.RADIUS) {
                hitVertical();
            } else if (this.y == BallModel.RADIUS) {
                hitHorizontal();
            } else {
                addX();
                addY();
            }
        } else {
            int blockX = block.getX();
            int blockY = block.getY();
            
            if (this.x + BallModel.RADIUS == blockX) {
                hitVertical();
            } else if (this.x - BallModel.RADIUS == blockX + BlockModel.WIDTH) {
                hitVertical();
            } else {
                if (y + BallModel.RADIUS == blockY) {
                    hitHorizontal();
                } else if (y - BallModel.RADIUS == blockY + BlockModel.HEIGHT) {
                    hitHorizontal();
                } else {
                    move();
                }
            }
        }
    }
    
     /**
     * Moves the ball to the next location.
     */
    public void move() {
        move(null);
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
