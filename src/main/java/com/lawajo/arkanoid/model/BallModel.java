/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid.model;

import com.lawajo.arkanoid.ArkanoidFXMLController;
import com.lawajo.arkanoid.BallTask;
import java.util.Random;
import java.util.Timer;

/**
 *
 * @author lande
 */
public class BallModel {
 
    public final static int RADIUS = 10;
    private final Random random = new Random();
    private Timer t;
    
    private double x;
    private double y;
    
    private double  prevX;
    private double  prevY;
    
    private double  dx;
    private double  dy;  
    
    private int  damage;
    
    
    
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
        double tempX = this.x;
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
        double tempY = this.y;
        this.y = this.prevY;
        this.prevY = tempY;
        this.dy *= -1;
        addX();
    }
    
    /**
     * Hits a horizontal object. The ball moves and bounces back.
     * This is automaticly done by the move function.
     */
    private void hitHorizontal(BlockModel block) {
        if (block instanceof SliderModel) {
            double tempY = this.y;
            this.y = this.prevY;
            this.prevY = tempY;
            Double randomDouble = ((Math.PI/6.0) + (this.random.nextDouble() * (Math.PI*(4.0/6.0))));
            System.out.println(randomDouble);
            setAngle(randomDouble, this.dy < 0);
            addX();
        } else {
            double tempY = this.y;
            this.y = this.prevY;
            this.prevY = tempY;
            this.dy *= -1;
            addX();
        }
    }
    
    
    /**
     * Sets the ball in a certain angle with the same speed
     * 
     * @param angle
     * @param up 
     */
    private void setAngle(double angle, Boolean up) {
        if (up) {
            this.dx = Math.signum(this.dx) * Math.cos(angle);
            this.dy = Math.sin(angle);
        } else {
            this.dx = Math.signum(this.dx) * Math.cos(angle);
            this.dy = -Math.sin(angle);
        }
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
            if (this.x <= BallModel.RADIUS ||  this.x >= 560 - BallModel.RADIUS) {
                if (this.y <= BallModel.RADIUS) {
                    this.dx *= -1;
                    this.dy *= -1;
                    addX();
                    addY();
                } else {
                    hitVertical();
                }
            } else if (this.y <= BallModel.RADIUS) {
                hitHorizontal();
            } else {
                addX();
                addY();
            }
        } else {
            int blockX = block.getX();
            int blockY = block.getY();
            
            if (this.y + BallModel.RADIUS <= blockY + (block.HEIGHT/2)) {
                hitHorizontal(block);
            } else if (this.y - BallModel.RADIUS >= blockY + block.HEIGHT/2) {
                hitHorizontal(block);
            } else {
                if (this.x + BallModel.RADIUS >= blockX &&
                        this.x + BallModel.RADIUS <= blockX + (block.WIDTH/4)) {
                    hitVertical();
                } else if (this.x- BallModel.RADIUS >= blockX + (block.WIDTH*(3/4)) &&
                        this.x - BallModel.RADIUS <= blockX + block.WIDTH){
                    hitVertical();
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
    
    public void checkDeath(){
        if(this.y + BallModel.RADIUS >= 352){
            stopMoving();
        }
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
        this.t = new Timer(true);
        this.t.scheduleAtFixedRate(balltask, 0, 20);
    
    }
    
    public void stopMoving(){
        this.t.cancel();
        this.t = null;
    }
    
    
    // Getters
    
    /**
     * Gets the x coordinate of the center of the ball.
     * 
     * @return The x coordinate.
     */
    public double  getX() {
        return this.x;
    }
    
    
    /**
     * Gets the y coordinate of the center of the ball.
     * 
     * @return The y coordinate.
     */
    public double  getY() {
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
