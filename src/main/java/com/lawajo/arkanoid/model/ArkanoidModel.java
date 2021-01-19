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
    public static final int HEIGHT = 11;
    
    private int savedLifes;
    private int savedScore;
    private int savedMaxScore;
    
    public static int lifes;
    private static int score;
    private static int maxScore;
    
    private BlockModel[][] blockField;
    //private ArkanoidLevels levels;
    private static Difficulty difficulty = Difficulty.EASY;
    
    public ArkanoidModel() {
        //this.levels = new ArkanoidLevels();
        this.blockField = getLevel();
        
        this.score = 0;
        this.maxScore = 0;
        this.lifes = 3;
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
    
    
    private BlockModel[][] getLevel() {
        switch (ArkanoidModel.difficulty) {
            case EASY:
                return ArkanoidLevels.getEasy1();
            case NORMAL:
                return ArkanoidLevels.getNormal1();
            case HARD:
                return ArkanoidLevels.getHard1();
            case EXPERT:
                return ArkanoidLevels.getExpert1();
            default:
                return null;
        }
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
        
        if (ball instanceof BoostModel && !(block instanceof SliderModel)) return false;
        
        
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
                if(block == null || block.isDeleted()) continue;
                if(check(ball, block)){
                    if (block.hit(ball.getDamage())){
                        block.setDeleted(true);
//                        blockField[i][j] = null;
                    } 
                return block;
                }     
            }
        }
        
        return null;
    }
    
    
    
    /**
     * adds score to total score
     * @param score when the ball hits the block
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
     * gets the lives
     * @return the lives
     */
    public static int getLifes(){
        return lifes;
    }
    
//    public void setLevels(ArkanoidLevels levels){
//        this.levels = levels;
//    }
    
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
     * Saves the ArkanoidModel with all the data of the game.
     * 
     * @param model The ArkanoidModel wich controls the game.
     * 
     * @throws IOException 
     */
    public static void save(ArkanoidModel model) throws IOException {
        FileWriter dataWriter = new FileWriter("data.txt");
        Gson gsonConverter = new Gson();
        
        model.savedLifes = ArkanoidModel.lifes;
        model.savedScore = ArkanoidModel.score;
        model.savedMaxScore = ArkanoidModel.maxScore;
        
        String jsonArkanoidModel = gsonConverter.toJson(model);
        dataWriter.write(jsonArkanoidModel);
        dataWriter.close();
        
    }
    
    
    /**
     * Loads the currently saved ArkanoidModel with all game data.
     * 
     * @return The ArkanoidModel.
     * 
     * @throws java.io.FileNotFoundException----------------------------------------- mss onze eigen exception throwen
     */
    public static ArkanoidModel load() throws FileNotFoundException {
        FileReader dataReader = new FileReader("data.txt");
        Gson gsonConverter = new Gson();
        
        ArkanoidModel model = gsonConverter.fromJson(dataReader, ArkanoidModel.class);
        
        ArkanoidModel.lifes = model.savedLifes;
        ArkanoidModel.score = model.savedScore;
        ArkanoidModel.maxScore = model.savedMaxScore;
        
        return model;
    }

}
