/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid;

import com.lawajo.arkanoid.model.BlockModel;

/**
 *
 * @author joeykoster
 */
public class ArkanoidLevels {
    
    public ArkanoidLevels() {
    }
    
    
    /**
     * First easy level
     * 
     * @return setup of the level in BlockModels
     */
    public static BlockModel[][] getEasy1() {
        BlockModel[][] playField = new BlockModel[11][10];
        
        // First (Highest) two layers
        for(int i = 0; i < 11 ; i++ ) {
            for(int j = 0; j < 2 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(5);
                playField[i][j] = block;    
            }    
        }
        
        // 3rd + 4th layer
        for(int i = 0; i < 11 ; i++ ) {
            for(int j = 2; j < 4 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(3);
                playField[i][j] = block;    
            }    
        }
        
        // 5th + 6th layer
        for(int i = 0; i < 11 ; i++ ) {
            for(int j = 4; j < 6 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(1);
                playField[i][j] = block;    
            }    
        }
        
        // filling finals
        for(int i = 0; i < 11 ; i++ ) {
            for(int j = 6; j < 10 ; j++) {
                playField[i][j] = null;    
            }    
        }
        
        
        
        
        return playField;
    }
    
}
