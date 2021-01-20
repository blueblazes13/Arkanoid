/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid.model;

import com.google.gson.Gson;
import com.lawajo.arkanoid.ArkanoidFXMLController;
import com.lawajo.arkanoid.ArkanoidLevels;
import com.lawajo.arkanoid.gameTickTask;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;


/**
 *
 * @author joeykoster
 */
public class ArkanoidModel {
    
    public static final int WIDTH = 11;
    public static final int HEIGHT = 11;
   
    public static ArkanoidModel controllingModel;
    
    private int savedLifes;
    private int savedScore;
    private int savedMaxScore;
    
    public static int lifes;
    private static int score;
    private static int maxScore;
    
    private BlockModel[][] blockField;
    private transient BallModel ball;
    private transient SliderModel slider;
    private static Difficulty difficulty = Difficulty.EASY;
    
    public ArkanoidModel() {
        this.blockField = getLevel();
        
        newSlider(255, 270);
        newBall(280, 250);
        setSliderSpeed(5);
        
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
    
    
    public SliderModel getSlider() {
        return this.slider;
    }
    
    
    public BallModel getBall() {
        return this.ball;
    }
    
    
    public Boolean isDeath() {
        return this.ball.checkDeath();
    }
    
    
    /**
     * Gets the difficulty to be used for the level.
     * @return difficulty
     */
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
    private Boolean check(BlockModel block) {
        int blockX = block.getX();
        int blockY = block.getY();
        
        double ballX = this.ball.getX();
        double ballY = this.ball.getY();
        
        if (this.ball instanceof BoostModel && !(block instanceof SliderModel)) return false;
        
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
     * @return a block or returns nothing
     */
    public BlockModel checkCollision() {
        
        BlockModel block = this.slider;
        if(block != null){
            if (check(block)) {
                System.out.println("collided!!!!!!");
                return this.slider;
            }
        }
            
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                
                block = getBlock(i, j);
                if(block == null || block.isDeleted()) continue;
                if(check(block)){
                    if (block.hit(this.ball.getDamage())){
                        block.setDeleted(true);
                        //blockField[i][j] = null;
                    } 
                return block;
                }     
            }
        }
        
        return null;
    }
    
    
    public void newGameTick(ArkanoidFXMLController controller) {
        Timer t = new Timer(true);
        gameTickTask gameTick = new gameTickTask(controller);
        t.scheduleAtFixedRate(gameTick, 0, 10);
    }
    
    
    public void newBall(int x, int y) {
        System.out.println("new ball spawned!");
        this.ball = new BallModel(x, y);
        this.ball.startMoving(this);
    }
    
    
    public void newSlider(int x, int y) {
        this.slider = new SliderModel(x, y);
    }
    
    
    public void moveSlider(Direction dir) {
        switch (dir) {
            case LEFT:
                this.slider.toLeft();
                break;
            case RIGHT:
                this.slider.toRight();
                break;
        }
    }
    
    
    public void stopSlider(Direction dir) {
        switch (dir) {
            case LEFT:
                this.slider.stopLeft();
                break;
            case RIGHT:
                this.slider.stopRight();
                break;
        }
    }
    
    
    public void setBallSpeed(int speed) {
        this.ball.setSpeed(speed);
    }
    
    
    public void setSliderSpeed(int speed) {
        this.slider.setSpeed(speed);
    }
    
    
    public void setSliderWidth(int width) {
        this.slider.setWidth(width);
    }
    
    
    public void setBallDamage(int damage) {
        this.ball.setDamage(damage);
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
     * set the number of lifes 
     * @param lifes 
     */
    public static void setLifes(int lifes){
        ArkanoidModel.lifes = lifes;
        System.out.println(lifes);
        System.out.println(ArkanoidModel.lifes);
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
