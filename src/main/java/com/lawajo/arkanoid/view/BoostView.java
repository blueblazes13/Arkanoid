/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid.view;

import com.lawajo.arkanoid.model.BoostModel;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author FrietStoofvlees
 */
public class BoostView extends ViewObject {

    private Circle circle;
    private BoostModel model;
    
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
    
    @Override
    public BoostModel getModel() {
        return this.model;
    }
    
}
