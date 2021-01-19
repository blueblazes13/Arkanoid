/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid.view;

import com.lawajo.arkanoid.model.ArkanoidModel;
import com.lawajo.arkanoid.model.BallModel;
import com.lawajo.arkanoid.model.BlockModel;
import com.lawajo.arkanoid.model.BoostModel;
import com.lawajo.arkanoid.model.SliderModel;
import javafx.scene.Node;

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
                BlockModel blockModel = model.getBlock(i, j);
                
                if (blockModel == null) continue;
                
                BlockView blockView = new BlockView(blockModel);
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
        BallView ballView = new BallView(ball);
        this.getChildren().add(ballView);
    }
    
    
    /**
     * Removes a random ball from the playfield.
     */
    public void removeBall(BallModel ball) {
        for (Node obj: this.getChildren()) {
            ViewObject viewObj = (ViewObject) obj;
            if (viewObj.getModel() == ball) {
                this.getChildren().remove(obj);
            }
        }
    }
    
    
    /**
     * adds a slider to the field
     * @param slider is a new slider 
     */
    public void addSlider(SliderModel slider) {
        SliderView sliderView = new SliderView(slider);
        this.getChildren().add(sliderView);
    }
    
    
    /**
     * adds a boost to the field
     * @param boost is new boost
     */
    public void addBoost(BoostModel boost){
        BoostView boostView = new BoostView(boost);
        this.getChildren().add(boostView);   
    }
    

    @Override
    public ArkanoidModel getModel() {
        return this.model;
    }
    
}
