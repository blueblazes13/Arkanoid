
package com.lawajo.arkanoid.model;

import javafx.scene.paint.Color;

/**
 *
 * @author Lander
 */
public class BlockModel {
    
    public final int WIDTH;
    public final int HEIGHT;
    
    
    
    private int maxLifes;
    private Boolean isDeleted;
    private int lifes;
    private int score;
    private int bonusScore;
    private BoostModel boost;
    private Boolean usedBoost;
    
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
        this.boost = null;
        this.usedBoost = false;
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
        this.boost = null;
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
        this.boost = null;
    }
    
    // Setters
    
    
    /**
     * calculates the color based on the number of lives
     * @return a new color  
     */
    public Color calcColor() {
        Double lifes;
        Double maxLifes;
        
        Color startColor = Color.WHITE;
        Color endColor = new Color(255.0/255, 81.0/255, 20.0/255, 1);
        
//        Color red = new Color(255.0/255, 81.0/255, 20.0/255, 1);
//        Color green = new Color(18.0/255, 255.0/255, 151.0/255, 1);
        
        lifes = 0.0 + this.lifes;
        maxLifes = 0.0 + this.maxLifes;

        
        if(maxLifes == 1.0)startColor = Color.LIGHTGREEN;
        if(maxLifes == 3.0)startColor =  Color.GREENYELLOW;
        if(maxLifes == 5.0)startColor =  Color.GREEN;
        if(maxLifes == 7.0)startColor =  Color.DARKGREEN;
        if(maxLifes == 15.0)startColor =  Color.RED;
        
        if(maxLifes == 2.0)startColor =  Color.LIGHTSKYBLUE;
        if(maxLifes == 4.0)startColor =  Color.LIGHTBLUE;
        if(maxLifes == 6.0)startColor =  Color.LIGHTSTEELBLUE;
        if(maxLifes == 8.0)startColor =  Color.STEELBLUE;
        if(maxLifes == 10.0)startColor =  Color.BLUE;
        if(maxLifes == 12.0)startColor =  Color.DARKBLUE;
        
        double newRed = endColor.getRed() + ((lifes/maxLifes) * (startColor.getRed() - endColor.getRed()));
        double newGreen = endColor.getGreen() + ((lifes/maxLifes) * (startColor.getGreen() - endColor.getGreen()));
        double newBlue = endColor.getBlue() + ((lifes/maxLifes) * (startColor.getBlue() - endColor.getBlue()));
        
        Color newColor = new Color(newRed, newGreen, newBlue, 1);
        
        return newColor;
    }
    
    /**
     * Puts a boost in the block.
     * 
     * @param boost The controller of the boost that has to drop out of the block.
     */
    public void setBoost(BoostModel boost) {
        this.boost = boost;
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
    
    
    /**
     * Checks if the block has a boost inside.
     * 
     * @return true if there is a boost, false if not.
     */
    public Boolean hasBoost() {
        return this.boost != null;
    }
    
    
    /**
     * Gives the boost thats currently inside the block.
     * 
     * @return BoostModel if there is one, else null
     */
    public BoostModel getBoost() {
        return this.boost;
    }
    
}
