/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid;

import java.util.TimerTask;
import javafx.application.Platform;

/**
 *
 * @author joeykoster
 */
public class gameTickTask extends TimerTask {

    private final ArkanoidFXMLController controller;
    
    public gameTickTask(ArkanoidFXMLController controller) {
        this.controller = controller;
    }
    
    @Override
    public void run() {
        Platform.runLater(this.controller::update);
    }
    
}
