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
    private ArkanoidModel arkanoidModel;
    
    
    public BallTask(BallModel ballModel, ArkanoidModel arkanoidModel) {
        this.ballModel = ballModel;
        this.arkanoidModel = arkanoidModel;
    }

    
    
    @Override
    public void run() {
        this.ballModel.move(this.arkanoidModel.checkCollision());
        ballModel.checkDeath();
    }

    
    
    
}
