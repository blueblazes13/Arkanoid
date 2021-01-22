/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid.model;

/**
 *
 * @author Vanmuysen Ward
 */
public class BoostModel extends BallModel {

    
/**
     * Initializes a new boost
     * 
     * @param x The x coordinate of the boost.
     * @param y The y coordinate of the boost.
     */
    public BoostModel(int x, int y) {
        super(x, y, 5);
        super.setAngle(Math.PI/2,true);
    }
}