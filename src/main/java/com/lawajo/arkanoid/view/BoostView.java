
package com.lawajo.arkanoid.view;

import com.lawajo.arkanoid.model.BoostModel;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


/**
 * @author Lander Ketelbuters, Joey Koster, Ward Vanmuysen
 */
public class BoostView extends ViewObject {
    
    //Datamenbers
    private final Circle circle;
    private final BoostModel model;
    
    /**
     * Initializes a new boost.
     * 
     * @param model The boostmodel that controls the view.
     */
    public BoostView(BoostModel model) {
        this.model = model;
        
        this.circle = new Circle(this.model.RADIUS);
        this.circle.setFill(Color.RED);
        this.circle.setStroke(Color.BLACK);
        this.circle.setStrokeWidth(0.4);
        
        this.getChildren().add(this.circle);
    }
    
    
    
    /**
     * Updates the current boost object with the newest parameters from the model.
     */
    @Override
    public void update() {
        this.circle.setCenterX(model.getX());
        this.circle.setCenterY(model.getY());
        this.circle.setRadius(model.RADIUS);
        this.circle.setFill(Color.RED);
    }
    
    
    /**
     * Gets the model of the boost.
     * 
     * @return The boostmodel that controls the boostview.
     */
    @Override
    public BoostModel getModel() {
        return this.model;
    }
    
}
