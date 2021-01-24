/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid.model;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;
import javafx.application.Platform;

/**
 *
 * @author Vanmuysen Ward
 */
public class BoostModel extends BallModel {

    private transient ArkanoidModel arkanoidModel;
    private Boolean activatedBoost;
    
    
/**
     * Initializes a new boost
     * 
     * @param x The x coordinate of the boost.
     * @param y The y coordinate of the boost.
     */
    public BoostModel(int x, int y, ArkanoidModel arkanoidModel) {
        super(x, y, arkanoidModel, 5);
        this.arkanoidModel = arkanoidModel;
        super.setAngle(Math.PI/2,true);
        this.activatedBoost = false;
    }
    
    
    /**
     * Activates a random boost.
     * Generates random number from 1 to 5.
     */
    public void activateBoost(){
        if (this.activatedBoost) return;
        if (super.arkanoidModel == null) super.arkanoidModel = ArkanoidModel.controllingModel;
        
        int Num = ThreadLocalRandom.current().nextInt(1,6);
        
        switch(Num){
            case 1:
                super.arkanoidModel.setBallDamage(3);
                break;
            case 2:
                super.arkanoidModel.setSliderWidth(30);
                break;
            case 3:
                super.arkanoidModel.setSliderSpeed(8);
                break;
            case 4:
                super.arkanoidModel.setBallSpeed(1.0);
                break;
            case 5:
                ArkanoidModel.setLifes(ArkanoidModel.getLifes()+1);
                break;
            default:
                break;
        }
        
        this.activatedBoost = true;
        stopBoost();
    }
    
    
    /**
     * Stops the effect from the boost after 20 seconds.
     */
    public void stopBoost() {
        Timer timer = new Timer(true);
        // Credits voor new TimerTask(){}: https://stackoverflow.com/questions/37970682/passing-lambda-to-a-timer-instead-of-timertask
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (arkanoidModel == null) arkanoidModel = ArkanoidModel.controllingModel;
                
                arkanoidModel.setBallDamage(1);
                Platform.runLater(() -> { arkanoidModel.setSliderWidth(60); });
                arkanoidModel.setSliderSpeed(5);
            }
        }, 20000);
    }
    
}