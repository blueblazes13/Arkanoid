
package com.lawajo.arkanoid.model;

/**
 *
 * @author Lander
 */
public class BlockModel {
    
    public final static int WIDTH = 45;
    public final static int HEIGHT = 15;
    
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
        this.isDeleted = false;
        this.lifes = 2;
    }
    
    
    /**
     * Initializes a new BlockModel.
     * 
     * @param lifes Total lifes of the block.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public BlockModel(int lifes, int x, int y) {
        this.lifes = lifes;
        this.x = x;
        this.y = y;
        this.isDeleted = false;
    }
    
    
    
    // Setters
    
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
