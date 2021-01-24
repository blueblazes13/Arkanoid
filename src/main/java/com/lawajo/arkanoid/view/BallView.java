
package com.lawajo.arkanoid.view;

import com.lawajo.arkanoid.model.BallModel;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


/**
 * @author Joey Koster
 */
public class BallView extends ViewObject {
    
    //Datamembers
    private final Circle circle;
    private final BallModel model;
    
    
    /**
     * Initializes a new ball object.
     * 
     * @param model The model to control the ball.
     */
    public BallView(BallModel model) {
        this.model = model;
        
        this.circle = new Circle(this.model.RADIUS);
        this.circle.setFill(Color.GRAY);
        
        this.getChildren().add(this.circle);
    }
    
    
    /**
     * Updates the current ball object with the newest parameters.
     */
    @Override
    public void update() {
        this.circle.setCenterX(model.getX());
        this.circle.setCenterY(model.getY());
        this.circle.setRadius(model.RADIUS);
        this.circle.setFill(Color.GRAY);
    }
    
    
    /**
     * Gets the model of the ball.
     *
     * @return The ballmodel that controls the ballview.
     */
    @Override
    public BallModel getModel() {
        return this.model;
    }
    
}
