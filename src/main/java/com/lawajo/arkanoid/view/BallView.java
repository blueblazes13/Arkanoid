/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid.view;

import com.lawajo.arkanoid.model.BallModel;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author joeyk
 */
public class BallView extends ViewObject {
    
    private Circle circle;
    private BallModel model;
    
    
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
        update();
    }
    
    
    // Setters
    
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
    
    
    @Override
    public BallModel getModel() {
        return this.model;
    }
    
}
