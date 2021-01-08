/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid;

import com.lawajo.arkanoid.model.BallModel;
import java.util.TimerTask;
import javafx.application.Platform;

/**
 *
 * @author lande
 */
public class BallTask extends TimerTask {
    private BallModel model;
    private ArkanoidFXMLController controller;

    public BallTask(BallModel model, ArkanoidFXMLController controller) {
        this.model = model;
        this.controller = controller;
    }

    
    
    @Override
    public void run() {
    
        model.move();
        Platform.runLater(controller :: update);
        
    }

    
    
    
}
