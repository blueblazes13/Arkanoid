package com.lawajo.arkanoid.view;

import com.lawajo.arkanoid.model.BlockModel;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author joeyk
 */
public class Block extends ViewObject {
    
    public final static int WIDTH = 45;
    public final static int HEIGHT = 15;
    
    private int lifes;
    
    private Rectangle rect;
    private BlockModel model;
   
    
    /**
     * Initializes a new block.
     *
     * @param model
     */
    public Block(BlockModel model) {
        this.model = model;
        
        this.rect = new Rectangle(model.WIDTH, model.HEIGHT);
        this.rect.setFill(Color.rgb(18, 255, 151));
        this.rect.setStroke(Color.BLACK);
        this.rect.setStrokeWidth(0.4);
        
        this.getChildren().add(this.rect);
        update();
    }
    
    
    // Setters
    
    /**
     * Updates the current block to the newest coordinates.
     */
    @Override
    public void update() {
        if (this.model.isDeleted()) {
            this.getChildren().clear();
            return;
        }
        
        this.rect.setLayoutX(this.model.getX());
        this.rect.setLayoutY(this.model.getY());
        this.rect.setFill(this.model.calcColor());
    }
    
    
    /**
     * Sets the color of the current block. By default the color is green.
     * 
     * @param color The color you want the block to be.
     */
    public void setColor(Color color) {
        this.rect.setFill(color);
    }
    



    
    
}   