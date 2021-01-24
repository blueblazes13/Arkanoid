/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid;

import com.lawajo.arkanoid.model.Direction;
import com.lawajo.arkanoid.model.SliderModel;
import java.util.TimerTask;

/**
 *
 * @author joeykoster
 */
public class MoveSliderTask extends TimerTask {

    private final SliderModel slider;
    private final Direction dir;
    
    
    public MoveSliderTask(Direction dir, SliderModel slider) {
        this.slider = slider;
        this.dir = dir;
    }
    
    
    @Override
    public void run() {
        switch (this.dir) {
            case LEFT:
                slider.moveLeft();
                break;
            case RIGHT:
                slider.moveRight();
                break;
        }
    }
    
}
