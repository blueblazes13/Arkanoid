/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid.model;

import com.google.gson.Gson;
import com.lawajo.arkanoid.ArkanoidLevels;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 *
 * @author joeykoster
 */
public class ArkanoidModel {
    
    public static final int WIDTH = 11;
    public static final int HEIGHT = 10;
    
    public static int lifes = 3;
    private static int score;
    private static int maxScore;
    
    private BlockModel[][] blockField;
    private ArkanoidLevels levels;
    private static Difficulty difficulty = Difficulty.EASY;
    
    public ArkanoidModel() {
        this.levels = new ArkanoidLevels();
        this.blockField = this.levels.getEasy1();
        
        this.score = 0;
        this.maxScore = 0;
    }
    
    
    /**
     * Gives the BlockModel of the specified location.
     * 
     * @param x the x coordinate 
     * @param y the y coordinate
     * @return a blockfield
     */
    public BlockModel getBlock(int x, int y) {
        return this.blockField[x][y];
    }
    
    /**
     * checks if the ball touches the block 
     * @param ball is the ball
     * @param block is the slider
     * @return a block or returns nothing
     */
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
    
    
    /**
     * checks if the ball touches with the block
     * @param ball is the ball
     * @param slider is the slider
     * @return a block or returns nothing
     */
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
    
    
    
    /**
     * adds score to total score
     * @param score when the ball the block hits 
     */
    public static void addScore(int score) {
        ArkanoidModel.score += score;
    }
    
    
    /**
     * gets the score 
     * @return the score on the moment
     */
    public static int getScore() {
        return ArkanoidModel.score;
    }
    
    
    /**
     * gets the max score 
     * @return the max score 
     */
    public static int getMaxScore() {
        return ArkanoidModel.maxScore;
    }
    
    
    /**
     * gets the difficulty
     * @return the difficulty at the moment
     */
    public static Difficulty getDifficulty() {
        return ArkanoidModel.difficulty;
    }
    
    
    /**
     * sets the max score to the new max score
     * @param score 
     */
    public static void setMaxScore(int score) {
        ArkanoidModel.maxScore = score;
    }
    
    
    /**
     * set the difficulty 
     * @param dif is the diffuculty you want
     */
    public static void setDifficulty(Difficulty dif){
        ArkanoidModel.difficulty = dif;
    }
    
    
    /**
     * gets the lives
     * @return the lives
     */
    public static int getLifes(){
        return lifes;
    }

    
    public static void save(ArkanoidModel model) throws IOException {
        FileWriter dataWriter = new FileWriter("data.txt");
        Gson gsonConverter = new Gson();
        
        String jsonArkanoidModel = gsonConverter.toJson(model);
        dataWriter.write(jsonArkanoidModel);
        dataWriter.close();
        
    }
    
    
    public static ArkanoidModel load() throws FileNotFoundException {
        FileReader dataReader = new FileReader("data.txt");
        Gson gsonConverter = new Gson();
        
        ArkanoidModel model = gsonConverter.fromJson(dataReader, ArkanoidModel.class);
        
        return model;
    }

}
