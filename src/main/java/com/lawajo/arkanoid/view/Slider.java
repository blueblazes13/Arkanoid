/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid.view;

import com.lawajo.arkanoid.model.SliderModel;

/**
 *
 * @author joeyk
 */
public class Slider extends ViewObject {
    
    private SliderModel model;
    
    
    
    /**
     * Initializes a new slider.
     * 
     * @param model The model wich controls the slider.
     */
    public Slider(SliderModel model) {
        this.model = model;
    }
    
    
    /**
     * Updates the current slider object with the newest parameters.
     */
    @Override
    public void update() {
        
    }
    
}
