package com.lawajo.arkanoid.view;

import com.lawajo.arkanoid.model.BlockModel;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author joeyk
 */
public class Block extends Region {
    
    public final static int WIDTH = 45;
    public final static int HEIGHT = 15;
    
    private int lifes;
    
    private Rectangle rect;
    private BlockModel model;
   
    
    /**
     * Initializes a new block.
     * 

     */
    public Block(BlockModel model) {
        
        this.rect = new Rectangle();
        
        update();
    }
    
    
    // Setters
    
    /**
     * Updates the current block to the newest coordinates.
     */
    public void update() {
        this.rect.setLayoutX(model.getX());
        this.rect.setLayoutY(model.getY());
    }
    
    
    /**
     * Sets the color of the current block. By default the color is green.
     * 
     * @param color The color you want the block to be.
     */
    public void setColor(Color color) {
        this.rect.setFill(color);
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
     * Gets the total damage the ball must deal to the block before it breaks.
     * 
     * @return Number of lifes.
     */
    public int getLifes() {
        return this.lifes;
    }
}
