/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid.model;


/**
 *
 * @author joeykoster
 */
public class ArkanoidModel {
    
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    
    private BlockModel[][] blockField;
    
    
    public ArkanoidModel() {
        this.blockField = new BlockModel[WIDTH][HEIGHT];
        
           
        for(int i = 0; i < HEIGHT ; i++ ) {
            for(int j = 0; j < WIDTH ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                this.blockField[i][j] = block;    
            }    
        }    
    }
    
    
    /**
     * Gives the BlockModel of the specified location.
     * 
     * @param x
     * @param y
     * @return 
     */
    public BlockModel getBlock(int x, int y) {
        
        return this.blockField[x][y];
    }
    
    
    private Boolean check(BallModel ball, BlockModel block) {
        int blockX = block.getX();
        int blockY = block.getY();
        
        double ballX = ball.getX();
        double ballY = ball.getY();
        
        
        if (blockX <= ballX + BallModel.RADIUS &&
                blockX + block.WIDTH >= ballX - BallModel.RADIUS) {
            if (blockY <= ballY + BallModel.RADIUS &&
                    blockY + block.HEIGHT >= ballY - BallModel.RADIUS) {
                return true;
            }
        }
        
        return false;
    }
    
    
    public BlockModel checkCollision(BallModel ball, SliderModel slider) {
        
        BlockModel block = slider;
        if(block != null){
            if (check(ball, block)) return slider;
        }
            
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                
                block = getBlock(i, j);
                if(block == null) continue;
                if(check(ball, block)){
                    if (block.hit(ball.getDamage())){
                        block.setDeleted(true);
                        blockField[i][j] = null;
                    } 
                return block;
                }     
            }
        }
        
        return null;
    }
}
