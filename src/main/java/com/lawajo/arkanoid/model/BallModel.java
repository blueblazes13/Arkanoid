
package com.lawajo.arkanoid.model;

import com.lawajo.arkanoid.BallTask;
import java.util.Random;
import java.util.Timer;

/**
 *
 * @author Lander
 */
public class BallModel {
 
    public final int RADIUS;
    
    private transient final Random random = new Random();
    private transient Timer t;
    private int speed;
    private Boolean moves;
    
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
        this.moves = false;
        this.speed = 1;
        this.x = x;
        this.y = y;
        
        this.prevX = x;
        this.prevY = y;
        
        this.dx = 0.01;
        this.dy = -1;
        
        this.damage = 1;
        this.RADIUS = 10;
    }
    
    
    /**
     * Initializes a new ball.
     * 
     * @param x The x coordinate of the ball.
     * @param y The y coordinate of the ball.
     */
    public BallModel(int x, int y, int radius) {
        this.moves = false;
        this.speed = 1;
        this.x = x;
        this.y = y;
        
        this.prevX = x;
        this.prevY = y;
        
        this.dx = 0.01;
        this.dy = -1;
        
        this.damage = 1;
        this.RADIUS = radius;
    }
    
    
    
    // Setters
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    
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
            if (this instanceof BoostModel) {
                stopMoving();
                return;
            }
            
            double tempY = this.y;
            this.y = this.prevY;
            this.prevY = tempY;
            Double randomDouble = ((Math.PI/6.0) + (this.random.nextDouble() * (Math.PI*(4.0/6.0))));
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
     * @param angle The angle of the traject of the ball.
     * @param up Will the ball bounce up or not. (This means that the ball is currently moving down)
     */
    public void setAngle(double angle, Boolean up) {
        if (up) {
            this.dx = Math.signum(this.dx) * Math.cos(angle) * this.speed;
            this.dy = Math.sin(angle) * this.speed;
        } else {
            this.dx = Math.signum(this.dx) * Math.cos(angle) * this.speed;
            this.dy = -Math.sin(angle) * this.speed;
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
     * @param block The block the ball hits.
     */
    
    public void move(BlockModel block) {
        if (block == null) {
            if (this.x <= this.RADIUS ||  this.x >= 560 - this.RADIUS) {
                if (this.y <= this.RADIUS) {
                    this.dx *= -1;
                    this.dy *= -1;
                    addX();
                    addY();
                } else {
                    hitVertical();
                }
            } else if (this.y <= this.RADIUS) {
                hitHorizontal();
            } else {
                addX();
                addY();
            }
        } else {
            int blockX = block.getX();
            int blockY = block.getY();
            
            if (this.y + this.RADIUS <= blockY + (block.HEIGHT/2)) {
                hitHorizontal(block);
            } else if (this.y - this.RADIUS >= blockY + block.HEIGHT/2) {
                if (block instanceof SliderModel) {
                    move();
                } else {
                    hitHorizontal(block);
                }
            } else {
                if (this.x + this.RADIUS >= blockX &&
                        this.x + this.RADIUS <= blockX + (block.WIDTH/4)) {
                    hitVertical();
                } else if (this.x- this.RADIUS >= blockX + (block.WIDTH*(3/4)) &&
                        this.x - this.RADIUS <= blockX + block.WIDTH){
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
    
    public boolean checkDeath(){
        if(this.y + this.RADIUS >= 352){
            stopMoving();
            return true;
        }
        return false;
    }
    
    /**
     * Starts moving the ball.
     * 
     * @param arkanoidModel The ArkanoidModel wich controls the game.
     */
    public void startMoving(ArkanoidModel arkanoidModel){
        BallTask balltask = new BallTask(this, arkanoidModel);
        this.t = new Timer(true);
        this.t.scheduleAtFixedRate(balltask, 0, 15);
        this.moves = true;
    }
    
    
    /**
     * the balls stops with moving
     */
    public void stopMoving(){
        this.t.cancel();
        this.moves = false;
        
        
//        // activeerboost if this.boost != 
//        this.t.schedule(() -> {
//            // stopboost
//        }, tijdOmTeStoppen);
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
    
    /**
     * Is the ball moving.
     * @return moving Boolean moving
     */
    public Boolean isMoving() {
        return this.moves;
    }
}