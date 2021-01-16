
package com.lawajo.arkanoid.model;

import javafx.scene.paint.Color;

/**
 *
 * @author Lander
 */
public class BlockModel {
    
    public final int WIDTH;
    public final int HEIGHT;
    
    private final Color green = new  Color(18/255, 255/255, 151/255, 1);
    private final Color red = new Color(255/255, 63/255, 0/255, 1);
    
    private final int MAX_LIFES;
    
    private Boolean isDeleted;
    private int lifes;
    
    private int x;
    private int y;
    
    
    /**
     * Initializes a new BlockModel.
     * 
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public BlockModel(int x, int y) {
        this.x = x;
        this.y = y;
        
        this.WIDTH = 45;
        this.HEIGHT = 15;
        
        this.isDeleted = false;
        this.lifes = 2;
        this.MAX_LIFES = this.lifes;
    }
    
    
    /**
     * Initializes a new BlockModel.
     * 
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public BlockModel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        
        this.WIDTH = width;
        this.HEIGHT = height;
        
        this.isDeleted = false;
        this.lifes = 2;
        this.MAX_LIFES = this.lifes;
    }
    
    
    /**
     * Initializes a new BlockModel.
     * 
     * @param lifes Total lifes of the block.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public BlockModel(int lifes, int x, int y) {
        this.x = x;
        this.y = y;
        
        this.WIDTH = 45;
        this.HEIGHT = 15;
        
        this.lifes = lifes;
        this.isDeleted = false;
        this.MAX_LIFES = this.lifes;
    }
    
    
    
    // Setters
    
    public Color calcColor() {
        double newRed = this.red.getRed() + (this.lifes/this.MAX_LIFES) * (this.green.getRed() - this.red.getRed());
        double newGreen = this.red.getGreen() + (this.lifes/this.MAX_LIFES) * (this.green.getGreen() - this.red.getGreen());
        double newBlue = this.red.getBlue() + (this.lifes/this.MAX_LIFES) * (this.green.getBlue() - this.red.getBlue());
        
        Color newColor = new Color(newRed, newGreen, newBlue, 1);
        
        return newColor;
    }
    
    
    public void setDeleted(Boolean value) {
        this.isDeleted = value;
    }
    
    
    /**
     * Sets the x coordinate of the upperleft corner of the current block.
     * 
     * @param x The new x coordinate of the block.
     */
    public void setX(int x) {
        this.x = x;
    }
    
    
    /**
     * Sets the y coordinate of the upperleft corner of the current block.
     * 
     * @param y The new y coordinate of the block.
     */
    public void setY(int y) {
        this.y = y;
    }
    
    
     /**
     * Sets the current position of the current block.
     * 
     * @param x The x coordinate of the block.
     * @param y The y coordinate of the block.
     */
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    
    /**
     * Hits the block with a certain damagelevel and checks if the
     * block is broken or not.
     * 
     * @param damage The damage to deal to the block.
     * @return True if broken, false if not broken.
     */
    public boolean hit(int damage) {
        if (this.lifes - damage <= 0) return true;
        
        this.lifes -= damage;
        return false;
    }
    
    
    /**
     * Sets the number of lifes of the block. By setting this property higher
     * than default (1), the block could last longer than one hit of the ball.
     * 
     * @param lifes Number of lifes of the block.
     */
    public void setlifes(int lifes) {
        this.lifes = lifes;
    }
    
    
    
    // Getters
    
    /**
     * Gets the x coordinate of the upperleft corner of the current block.
     * 
     * @return The x coordinate of the block.
     */
    public int getX() {
        return this.x;
    }
    
    
    /**
     * Gets the y coordinate of the upperleft corner of the current block.
     * 
     * @return The y coordinate of the block.
     */
    public int getY() {
        return this.y;
    }
    
    
    /**
     * Gets the total damage the ball must deal to the block before it breaks.
     * 
     * @return Number of lifes.
     */
    public int getLifes() {
        return this.lifes;
    }

    
    public Boolean isDeleted() {
        return this.isDeleted;
    }
    
}
