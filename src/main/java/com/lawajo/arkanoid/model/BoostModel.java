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
        super.setAngle(Math.PI/2,true);
        this.arkanoidModel = arkanoidModel;
        this.activatedBoost = false;
    }
    
    
    /**
     * Activates a random boost.
     * Generates random number from 1 to 5.
     */
    public void activateBoost(){
        if (this.activatedBoost) return;
        
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
    
    
    public void stopBoost() {
        Timer timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                arkanoidModel.setBallDamage(1);
                Platform.runLater(() -> { arkanoidModel.setSliderWidth(60); });
                arkanoidModel.setSliderSpeed(5);
            }
        }, 20000);
    }
    
}