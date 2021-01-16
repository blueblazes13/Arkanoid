/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid.view;

import com.lawajo.arkanoid.model.ArkanoidModel;
import com.lawajo.arkanoid.model.BallModel;
import com.lawajo.arkanoid.model.SliderModel;

/**
 *
 * @author joeykoster
 */
public class ArkanoidView extends ViewObject {
    
    
    
    private ArkanoidModel model;
    
    /**
     * Initializes a new ArkanoidView object to control the game views.
     * 
     * @param model The model to control the view.
     */
    public ArkanoidView(ArkanoidModel model) {
        this.model = model;
        
        for (int i = 0; i < model.WIDTH; i++) {
            for (int j = 0; j < model.HEIGHT; j++) {
                Block blockView = new Block(model.getBlock(i, j));
                this.getChildren().add(blockView);
            }
        }
    }
    
    
    /**
     * Updates all the ViewObjects with their newest parameters.
     */
    @Override
    public void update() {
        for (Object obj: this.getChildren()) {
            ViewObject vObject = (ViewObject) obj;
            vObject.update();
        }
    }
    
    
    /**
     * Adds an extra ball object to the playfield.
     * 
     * @param ball The controller of the ball.
     */
    public void addBall(BallModel ball) {
        this.getChildren().add(new Ball(ball));
    }
    
    
    /**
     * Removes a random ball from the playfield.
     */
    public void removeBall() {
        for (Object obj: this.getChildren()) {
            if (obj instanceof Ball) {
                Ball ball = (Ball) obj;
                this.getChildren().remove(ball);
            }
        }
    }
    
    
    /**
     * adds a slider to the field
     * @param slider is a new slider 
     */
    public void addSlider(SliderModel slider) {
        this.getChildren().add(new Slider(slider));
    }
    
    
    /**
     * Removes the specified block from the playfield.
     * 
     * @param x The x coordinate of the block.
     * @param y The y coordinate of the block.
     * 
     * @deprecated Functie is nog niet geschreven!
     */
    @Deprecated
    public void removeBlock(int x, int y) {
       
    }
    
}
