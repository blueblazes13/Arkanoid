/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid.model;

import com.lawajo.arkanoid.view.Block;

/**
 *
 * @author joeykoster
 */
public class ArkanoidModel {
    
    public static final int WIDTH = 5;
    public static final int HEIGHT = 30;
    
    private BlockModel[][] blockField;
    
    
    public ArkanoidModel() {
        this.blockField = new BlockModel[10][30];
    }
    
    
    /**
     * Gives the BlockModel of the specified location.
     * 
     * @param x
     * @param y
     * @return 
     */
    public BlockModel getBlock(int x, int y) {
        
        // Lander dit moet jij nog ff fixen, maar ik had de functie ff nodig in ik wou jouw werk niet afpakken.
        
        return null;
    }
    
}
