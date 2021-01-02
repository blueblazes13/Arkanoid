/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid.view;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author joeyk
 */
public class Ball extends Region {
    
    public final static int RADIUS = 10;
    
    private Circle circle;
    private int x;
    private int y;
    
    
    /**
     * Initializes a new ball.
     * 
     * @param x The x coordinate of the ball.
     * @param y The y coordinate of the ball.
     */
    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        
        this.circle = new Circle();
        this.circle.setRadius(Ball.RADIUS);
        this.circle.setFill(Color.WHITE);
        
        update();
        this.getChildren().add(this.circle);
    }
    
    
    // Setters
    
    /**
     * Updates the current ball to the newest coordinates.
     */
    private void update() {
        this.circle.setLayoutX(this.x);
        this.circle.setLayoutY(this.y);
    }
}
