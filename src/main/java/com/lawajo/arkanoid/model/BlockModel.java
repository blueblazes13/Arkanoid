
package com.lawajo.arkanoid.model;

import com.lawajo.arkanoid.Deletable;
import javafx.scene.paint.Color;

/**
 *
 * @author Lander
 */
public class BlockModel implements Deletable {
    
    // Datamembers
    public final int WIDTH;
    public final int HEIGHT;
    
    private BoostModel boost;
    
    private int maxLifes;
    private int lifes;
    private int score;
    private int bonusScore;
    
    private int x;
    private int y;
    
    private boolean isDeleted;
    
    
    /**
     * Initializes a new BlockModel.
     * 
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public BlockModel(int x, int y) {
        this.WIDTH = 45;
        this.HEIGHT = 15;
        
        this.x = x;
        this.y = y;
        
        this.boost = null;
        
        this.lifes = 2;
        this.maxLifes = this.lifes;
        this.score = 5;
        this.bonusScore = 15;
        
        this.isDeleted = false;
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
        this.WIDTH = width;
        this.HEIGHT = height;
        
        this.x = x;
        this.y = y;
        
        this.boost = null;
        
        this.lifes = 2;
        this.maxLifes = this.lifes;
        this.score = 5;
        this.bonusScore = 15;
        
        this.isDeleted = false;
    }
    
    
    /**
     * Initializes a new BlockModel.
     * 
     * @param lifes Total lifes of the block.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public BlockModel(int lifes, int x, int y) {
        this.WIDTH = 45;
        this.HEIGHT = 15;
        
        this.x = x;
        this.y = y;
        
        this.boost = null;
        
        this.lifes = lifes;
        this.maxLifes = this.lifes;
        this.score = 5;
        this.bonusScore = 15;
        
        this.isDeleted = false;
    }
    
    
    
    // Setters
    
    /**
     * Calculates the color based on the number of lifes.
     * @return The calculated color.
     */
    public Color calcColor() {
        Double blockLifes;
        Double maxBlockLifes;
        
        blockLifes = 0.0 + this.lifes;
        maxBlockLifes = 0.0 + this.maxLifes;
        
        Color startColor = Color.WHITE;
        Color endColor = new Color(255.0/255, 81.0/255, 20.0/255, 1);
        
        if(maxBlockLifes == 1.0)startColor = Color.LIGHTGREEN;
        if(maxBlockLifes == 3.0)startColor =  Color.GREENYELLOW;
        if(maxBlockLifes == 5.0)startColor =  Color.GREEN;
        if(maxBlockLifes == 7.0)startColor =  Color.DARKGREEN;
        if(maxBlockLifes >= 15.0)startColor =  Color.RED;
        
        if(maxBlockLifes == 2.0)startColor =  Color.LIGHTSKYBLUE;
        if(maxBlockLifes == 4.0)startColor =  Color.LIGHTBLUE;
        if(maxBlockLifes == 6.0)startColor =  Color.LIGHTSTEELBLUE;
        if(maxBlockLifes == 8.0)startColor =  Color.STEELBLUE;
        if(maxBlockLifes >= 10.0 && maxBlockLifes < 12.0) startColor = Color.BLUE;
        if(maxBlockLifes == 12.0 && maxBlockLifes < 15.0)startColor =  Color.DARKBLUE;
        
        double newRed = endColor.getRed() + ((blockLifes/maxBlockLifes) * (startColor.getRed() - endColor.getRed()));
        double newGreen = endColor.getGreen() + ((blockLifes/maxBlockLifes) * (startColor.getGreen() - endColor.getGreen()));
        double newBlue = endColor.getBlue() + ((blockLifes/maxBlockLifes) * (startColor.getBlue() - endColor.getBlue()));
        
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
     */
    @Override
    public void setDeleted() {
        this.isDeleted = true;
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
    @Override
    public boolean isDeleted() {
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
