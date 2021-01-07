/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid.view;

import com.lawajo.arkanoid.model.BoostModel;

/**
 *
 * @author joeyk
 */
public class Boost extends ViewObject {

    BoostModel model;
    
    public Boost(BoostModel model) {
        this.model = model;
    }
    
    
    /**
     * Updates the current boost object with the newest parameters.
     */
    @Override
    public void update() {
        
    }
    
}
