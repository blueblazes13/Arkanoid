/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid;

import com.lawajo.arkanoid.model.ArkanoidModel;
import com.lawajo.arkanoid.model.BallModel;
import com.lawajo.arkanoid.model.SliderModel;
import java.util.TimerTask;
import javafx.application.Platform;

/**
 *
 * @author lande
 */
public class BallTask extends TimerTask {
    private BallModel ballModel;
    private ArkanoidFXMLController controller;
    private ArkanoidModel arkanoidModel;
    private SliderModel sliderModel;
    
    
    public BallTask(BallModel ballModel, ArkanoidFXMLController controller, ArkanoidModel arkanoidModel, SliderModel sliderModel) {
        this.ballModel = ballModel;
        this.controller = controller;
        this.arkanoidModel = arkanoidModel;
        this.sliderModel = sliderModel;
    }

    
    
    @Override
    public void run() {
        this.ballModel.move(this.arkanoidModel.checkCollision(ballModel, sliderModel));
        Platform.runLater(controller :: update);
        
    }

    
    
    
}
