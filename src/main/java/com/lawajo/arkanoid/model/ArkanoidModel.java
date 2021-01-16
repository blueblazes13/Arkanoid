/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid.model;

import com.lawajo.arkanoid.ArkanoidLevels;


/**
 *
 * @author joeykoster
 */
public class ArkanoidModel {
    
    public static final int WIDTH = 11;
    public static final int HEIGHT = 10;
    
    private static int score;
    private static int maxScore;
    
    private BlockModel[][] blockField;
    private ArkanoidLevels levels;
    
    
    public ArkanoidModel() {
        this.levels = new ArkanoidLevels();
        this.blockField = this.levels.getEasy1();
        
        this.score = 0;
        this.maxScore = 0;
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
    
    
    public static void addScore(int score) {
        ArkanoidModel.score += score;
    }
    
    public static int getScore() {
        return ArkanoidModel.score;
    }
    
    public static int getMaxScore() {
        return ArkanoidModel.maxScore;
    }
    
    public static void setMaxScore(int score) {
        ArkanoidModel.maxScore = score;
    }
    
    
}
