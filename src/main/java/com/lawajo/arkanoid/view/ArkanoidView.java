/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid.view;

import com.lawajo.arkanoid.model.ArkanoidModel;
import com.lawajo.arkanoid.model.BlockModel;
import javafx.collections.ObservableList;
import javafx.scene.layout.Region;

/**
 *
 * @author joeykoster
 */
public class ArkanoidView extends Region {
    
    
    
    private ArkanoidModel model;
    
    public ArkanoidView(ArkanoidModel model) {
        this.model = model;
        
        
        for (int i = 0; i < model.WIDTH; i++) {
            for (int j = 0; j < model.HEIGHT; j++) {
                Block blockView = new Block(model.getBlock(i, j));
                model.getBlock(i, j).setX(30 + Block.WIDTH * i);
                model.getBlock(i, j).setY(30 + Block.HEIGHT * j);
                this.getChildren().add(blockView);
            }
        }
    }
    
    
    public void update() {
        for (Object obj: this.getChildren()) {
            if (obj instanceof Block) {
                Block block = (Block) obj;
                block.update();
            }
        }
    }
    
    
}
