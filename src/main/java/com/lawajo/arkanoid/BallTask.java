/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid;

import com.lawajo.arkanoid.model.ArkanoidModel;
import com.lawajo.arkanoid.model.BallModel;
import java.util.TimerTask;

/**
 * @author Lander Ketelbuters
 */
public class BallTask extends TimerTask {
    private final BallModel ballModel;
    private final ArkanoidModel arkanoidModel;
    
    
    public BallTask(BallModel ballModel, ArkanoidModel arkanoidModel) {
        this.ballModel = ballModel;
        this.arkanoidModel = arkanoidModel;
    }

    
    
    @Override
    public void run() {
        this.ballModel.move(this.arkanoidModel.checkCollision(this.ballModel));
        ballModel.checkDeath();
    }

    
    
    
}
