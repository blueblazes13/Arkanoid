
package com.lawajo.arkanoid.view;

import com.lawajo.arkanoid.model.BoostModel;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


/**
 *
 * @author Lander Lander Ketelbuters, Joey Koster, Ward Vanmuysen
 */
public class BoostView extends ViewObject {
    
    //datamenbers
    private Circle circle;
    private BoostModel model;
    
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
        update();
    }
    
    
    
    /**
     * Updates the current boost object with the newest parameters.
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
