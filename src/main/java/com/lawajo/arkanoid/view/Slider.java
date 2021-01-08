/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid.view;

import com.lawajo.arkanoid.model.SliderModel;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author joeyk
 */
public class Slider extends ViewObject {
    
    private SliderModel model;
    private Rectangle rect;
    
    
    /**
     * Initializes a new slider.
     * 
     * @param model The model wich controls the slider.
     */
    public Slider(SliderModel model) {
        this.model = model;
        
        this.rect = new Rectangle(this.model.WIDTH, this.model.HEIGHT);
        this.rect.setFill(Color.CHOCOLATE);
        this.rect.setStroke(Color.BLACK);
        this.rect.setStrokeWidth(0.4);
        
        this.getChildren().add(this.rect);
        update();
    }
    
    
    /**
     * Updates the current slider object with the newest parameters.
     */
    @Override
    public void update() {
        this.rect.setLayoutX(this.model.getX());
        this.rect.setLayoutY(this.model.getY());
        this.rect.setFill(Color.CHOCOLATE);
    }
    
}
