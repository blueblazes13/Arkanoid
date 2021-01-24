
package com.lawajo.arkanoid.view;

import com.lawajo.arkanoid.model.BlockModel;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 *
 * @author Lander Ketelbuters, Joey Koster, Ward Vanmuysen
 */
public class BlockView extends ViewObject {
    
    //Statics
    public final static int WIDTH = 45;
    public final static int HEIGHT = 15;
    
    //Datamembers
    private final Rectangle rect;
    private final BlockModel model;
   
    
    /**
     * Initializes a new block.
     * 
     * @param model Is the blockmodel that controls the view.
     */
    public BlockView(BlockModel model) {
        this.model = model;
        
        this.rect = new Rectangle(model.WIDTH, model.HEIGHT);
        this.rect.setStroke(Color.BLACK);
        this.rect.setStrokeWidth(0.4);
        
        this.getChildren().add(this.rect);
    }
    
    
    /**
     * Updates the current block to the newest coordinates from the model.
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
    
    
    /**
     * Gets the model of the block.
     * 
     * @return The blockmodel that controls the blockview.
     */
    @Override
    public BlockModel getModel() {
        return this.model;
    }

    
    
}   