/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid.view;

import javafx.scene.layout.Region;

/**
 *
 * @author joeykoster
 */
public abstract class ViewObject extends Region {
    
    public abstract void update();
    public abstract Object getModel();
    
}
