package com.lawajo.arkanoid.view;

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
    
    private Rectangle rect;
    private int lifes;
    private int x;
    private int y;
    
    
    /**
     * Initializes a new block.
     * 
     * @param x The x coordinate of the block.
     * @param y The y coordinate of the block.
     */
    public Block(int x, int y) {
        this.x = x;
        this.y = y;
        this.lifes = 1;
        
        this.rect = new Rectangle();
        this.rect.setWidth(Block.WIDTH);
        this.rect.setHeight(Block.HEIGHT);
        this.rect.setFill(Color.GREEN);
        this.rect.setStroke(Color.BLACK);
        this.rect.setStrokeWidth(1);
        
        update();
        this.getChildren().add(this.rect);
    }
    
    
    // Setters
    
    /**
     * Updates the current block to the newest coordinates.
     */
    private void update() {
        this.rect.setLayoutX(x);
        this.rect.setLayoutY(y);
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
     * Sets the x coordinate of the current block.
     * 
     * @param x The new x coordinate of the block.
     */
    public void setX(int x) {
        this.x = x;
        update();
    }
    
    
    /**
     * Sets the x coordinate of the current block.
     * 
     * @param x The new x coordinate of the block.
     */
    public void setY(int y) {
        this.y = y;
        update();
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
        update();
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
     * Gets the x coordinate of the current block.
     * 
     * @return The x coordinate of the block.
     */
    public int getX() {
        return this.x;
    }
    
    
    /**
     * Gets the y coordinate of the current block.
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
}
