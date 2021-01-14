/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid.model;

import com.lawajo.arkanoid.ArkanoidFXMLController;
import com.lawajo.arkanoid.BallTask;
import java.util.Timer;

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
    
    private int damage;
    
    
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
        
        this.damage = 1;
    }
    
    
    
    // Setters
    
    /**
     * sets the total damage the ball can deal.
     * 
     * @param damage The damage the ball can deal.
     */    
    public void setDamage(int damage) {
        this.damage = damage;
    }
    
    
    /**
     * Adds dy to y.
     */
    private void addY() {
        this.prevY = this.y;
        this.y += this.dy;
    }
    
    
    /**
     * Adds dx to x.
     */
    private void addX() {
        this.prevX = this.x;
        this.x += this.dx;
    }
    
    
    /**
     * Hits a vertical object. The ball moves and bounces back.
     * This is automaticly done by the move function.
     */
    private void hitVertical() {
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
    private void hitHorizontal() {
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
     * Also checks if the ball collides with a block if that block is given as
     * a parameter. If not, the ball only checks if it collides with a wall.
     * 
     * @deprecated Toen ik dit schreef, had ik nog niet door dat we ook een ballModel gingen maken.
     *              Ik denk nu dus dat het beter is dat we dit bij het Model houden zoals het nu is.
     * 
     * @param block The block the ball hits.
     */
    @Deprecated
    public void move(BlockModel block) {
        if (block == null) {
            if (this.x == BallModel.RADIUS || this.x == 500 - BallModel.RADIUS) {
                if (this.y == BallModel.RADIUS) {
                    this.dx *= -1;
                    this.dy *= -1;
                    addX();
                    addY();
                } else {
                    hitVertical();
                }
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
     * Without a BlockModel, the function just moves the ball without checking
     * if it collides with a block.
     */
    public void move() {
        move(null);
    }
    
    
    /**
     * Starts moving the ball.
     * 
     * @param controller The ArkanoidFXMLController wich controls the view of the ball.
     * @param arkanoidModel The ArkanoidModel wich controls the game.
     * @param sliderModel The SliderModel wich controls the slider in the same view as the ball.
     */
    public void startMoving(ArkanoidFXMLController controller, ArkanoidModel arkanoidModel, SliderModel sliderModel){
        BallTask balltask = new BallTask(this, controller, arkanoidModel, sliderModel);
        Timer t = new Timer(true);
        t.scheduleAtFixedRate(balltask, 0, 50);
    
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
    
    
    /**
     * Gets the damage the ball can deal to a block
     * 
     * @return Total damage
     */
    public int getDamage() {
        return this.damage;
    }
    
}
