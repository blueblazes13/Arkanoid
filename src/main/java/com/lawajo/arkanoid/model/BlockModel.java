
package com.lawajo.arkanoid.model;

import javafx.scene.paint.Color;

/**
 *
 * @author Lander
 */
public class BlockModel {
    
    public final int WIDTH;
    public final int HEIGHT;
    
    private final Color green = new Color(18.0/255, 255.0/255, 151.0/255, 1.0);
    private final Color red = new Color(255.0/255, 63.0/255, 0.0/255, 1.0);
    
    private int maxLifes;
    private Boolean isDeleted;
    private int lifes;
    private int score;
    private int bonusScore;
    
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
        this.maxLifes = this.lifes;
        this.score = 5;
        this.bonusScore = 15;
    }
    
    
    /**
     * Initializes a new BlockModel.
     * 
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param width The witdh of the block.
     * @param height The heigth of the block.
     */
    public BlockModel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        
        this.WIDTH = width;
        this.HEIGHT = height;
        
        this.isDeleted = false;
        this.lifes = 2;
        this.maxLifes = this.lifes;
        this.score = 5;
        this.bonusScore = 15;
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
        this.maxLifes = this.lifes;
        this.score = 5;
        this.bonusScore = 15;
    }
    
    
    
    // Setters
    
    
    /**
     * calculates the color based on the number of lives
     * @return a new color  
     */
    public Color calcColor() {
        Double lifes;
        Double maxLifes;
        
        if (this.lifes != 1 && this.maxLifes != 1) {
            lifes = -1.0 + this.lifes;
            maxLifes = -1.0 + this.maxLifes;
        } else {
            lifes = 0.0 + this.lifes;
            maxLifes = 0.0 + this.maxLifes;
        }
        
        
        double newRed = this.red.getRed() + ((lifes/maxLifes) * (this.green.getRed() - this.red.getRed()));
        double newGreen = this.red.getGreen() + ((lifes/maxLifes) * (this.green.getGreen() - this.red.getGreen()));
        double newBlue = this.red.getBlue() + ((lifes/maxLifes) * (this.green.getBlue() - this.red.getBlue()));
        
        Color newColor = new Color(newRed, newGreen, newBlue, 1);
        
        return newColor;
    }
    
    
    /**
     * sets the value of the block
     * @param value is true when the blocks is deleted
     */
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
        if (this.lifes - damage <= 0) {
            ArkanoidModel.addScore(this.bonusScore);
            return true;
        }
        
        ArkanoidModel.addScore(this.score);
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
        this.maxLifes = lifes;
    }
    
    
    /**
     * Sets the score when the ball hits the block.
     * @param score the new score. 
     */
    public void setScore(int score){
        this.score = score;
    }
    
    
    /**
     * Sets the bonusscore when the ball hits the block.
     * @param bonusScore the new bonusscore. 
     */
    public void setBonusScore(int bonusScore){
        this.bonusScore = bonusScore;
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

    
    /**
     * Gets the value of the block this can be true or false
     * @return true when the blocks is deleted
     */
    public Boolean isDeleted() {
        return this.isDeleted;
    }
    
}
