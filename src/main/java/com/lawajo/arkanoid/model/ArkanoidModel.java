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
 * @author Lander Ketelbuters, Joey Koster, Ward Vanmuysen
 */
public class ArkanoidModel {
    
    // Statics
    public static final int WIDTH = 11;
    public static final int HEIGHT = 11;
    
    public static int lifes = 3;
    private static int score = 0;
    private static int maxScore = 0;
    
    public static ArkanoidModel controllingModel;
    private static Difficulty difficulty = Difficulty.EASY;
    
    // Variables to help saving the game
    private int savedLifes;
    private int savedScore;
    private int savedMaxScore;
    
    // Datamembers
    private BlockModel[][] blockField;
    private transient BallModel ball;
    private double ballSpeed;
    private transient SliderModel slider;
    private transient ArkanoidLevels levels;
    
    
    /**
     * Initializes a new ArkanoidModel to control the game.
     */
    public ArkanoidModel() {
        this.blockField = getLevel();
        
        newSlider(255, 270);
        newBall(280, 250);
        
        
    }
    
    // Getters
    
    
    /**
     * Gives the BlockModel of the specified location.
     *  
     * @param x the x coordinate 
     * @param y the y coordinate
     * 
     * @return a blockfield
     */
    public BlockModel getBlock(int x, int y) {
        return this.blockField[x][y];
    }
    
    
    /**
     * Gets the slider used in the current game.
     * 
     * @return SliderModel that controlls the slider.
     */
    public SliderModel getSlider() {
        return this.slider;
    }
    
    
    /**
     * Gets the ball used in the current game.
     * 
     * @return BallModel that controlls the ball.
     */
    public BallModel getBall() {
        return this.ball;
    }
    
    
    /**
     * Checks if the ball is on the ground.
     * Stops moving the ball if the ball is on the ground.
     * 
     * @return true if on the ground, else false.
     */
    public Boolean isDeath() {
        return this.ball.checkDeath();
    }
    
    
    /**
     * Gets the difficulty to be used for the level.
     * 
     * @return The level that matches with the selected difficulty.
     */
    private BlockModel[][] getLevel() {
        this.levels = new ArkanoidLevels(this);
        
        switch (ArkanoidModel.difficulty) {
            case EASY:
                this.ballSpeed = 1.0;
                return this.levels.getEasy1();
            case NORMAL:
                this.ballSpeed = 1.1;
                return this.levels.getNormal1();
            case HARD:
                this.ballSpeed = 1.2;
                return this.levels.getHard1();
            case EXPERT:
                this.ballSpeed = 1.3;
                return this.levels.getExpert1();
            default:
                return null;
        }
    }
    
    
    /**
     * Checks if the ball touches the block.
     * 
     * @param ball The ball that has to be checked.
     * @param block The block that has to be checked.
     * 
     * @return True if collides, else false.
     */
    private Boolean check(BlockModel block, BallModel ball) {
        int blockX = block.getX();
        int blockY = block.getY();
        
        double ballX = ball.getX();
        double ballY = ball.getY();
        
        if (ball instanceof BoostModel && !(block instanceof SliderModel)) {
            return false;
        }
        
        if (blockX <= ballX + ball.RADIUS &&
                blockX + block.WIDTH >= ballX - ball.RADIUS) {
            if (blockY <= ballY + ball.RADIUS &&
                    blockY + block.HEIGHT >= ballY - ball.RADIUS) {
                return true;
            }
        }
        
        return false;
    }
    
    
    /**
     * Checks if the ball collides with any block in the current playfield.
     * 
     * @param ballModel The model of the ball you wanna check a collision for.
     * 
     * @return BlockModel of the block that the ball collides with. Null if there is no collision.
     */
    public BlockModel checkCollision(BallModel ballModel) {
        
        BlockModel block = this.slider;
        if(block != null){
            if (check(block, ballModel)) {
                return this.slider;
            }
        }
            
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                
                block = getBlock(i, j);
                if(block == null || block.isDeleted()) {
                    continue;
                }
                if(check(block, ballModel)){
                    if (block.hit(this.ball.getDamage())){
                        block.setDeleted();
                    } 
                return block;
                }     
            }
        }
        
        return null;
    }
    
    
    /**
     * Checks if the player has broken all the blocks.
     * @return true of false
     */
    public boolean allBlocksBroken(){
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                
                BlockModel block = getBlock(i, j);
                if(block != null && block.isDeleted() == false) {
                    return false;
                }
            }
        }
        return true;
    }
    
    
    /**
     * Gets the current score of the game.
     * 
     * @return The current score.
     */
    public static int getScore() {
        return ArkanoidModel.score;
    }
    
    
    /**
     * Gets the best score of the game.
     * 
     * @return The current best score.
     */
    public static int getMaxScore() {
        return ArkanoidModel.maxScore;
    }
    
    
    /**
     * Gets the selected difficulty.
     * 
     * @return The difficulty thats currently selected.
     */
    public static Difficulty getDifficulty() {
        return ArkanoidModel.difficulty;
    }
    
    
    /**
     * Gets the current lifes of the player.
     * 
     * @return Current lifes of the player.
     */
    public static int getLifes(){
        return lifes;
    }
    
    
    /**
     * Loads the currently saved ArkanoidModel with all game data.
     * 
     * @return The ArkanoidModel.
     * 
     * @throws java.io.FileNotFoundException
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
    
    // Setters
    
    
    /**
     * Creates a new tick clock to constantly update the views of the game.
     * 
     * @param controller The controller that handles the views.
     */
    public void newGameTick(ArkanoidFXMLController controller) {
        Timer t = new Timer(true);
        gameTickTask gameTick = new gameTickTask(controller);
        t.scheduleAtFixedRate(gameTick, 0, 10);
    }
    
    
    /**
     * Creates a new ball for the current game.
     * 
     * @param x The x coordinate of the center of the ball.
     * @param y The y coordinate of the center of the ball.
     */
    public void newBall(int x, int y) {
        this.ball = new BallModel(x, y, this);
        this.ball.startMoving(this);
        this.ball.setSpeed(this.ballSpeed);
    }
    
    
    /**
     * Creates a new slider for the current game.
     * 
     * @param x The x coordinate of the left top corner of the slider.
     * @param y The y coordinate of the left top corner of the slider.
     */
    public void newSlider(int x, int y) {
        this.slider = new SliderModel(x, y);
    }
    
    
    /**
     * Starts moving the slider to a direction.
     * The slider keeps moving until it's stopped by the stopSlider() method.
     * 
     * @param dir The direction the slider has to move to.
     */
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
    
    
    /**
     * Stops moving the ball
     */
    public void stopBall() {
        this.ball.stopMoving();
    }
    
    
    /**
     * Stops moving the slider in a direction.
     * This means that if the slider is moving in both directions and one
     * direction is stopped, the slider will continue moving in the opposite direction.
     * 
     * @param dir The direction the slider has to stop moving to.
     */
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
    
    
    /**
     * Sets the speed of the ball of the game.
     * 
     * @param speed The speed the ball has to get.
     */
    public void setBallSpeed(double speed) {
        this.ball.setSpeed(speed);
    }
    
    
    /**
     * Sets the speed of the slider of the game.
     * 
     * @param speed The speed the slider has to get.
     */
    public void setSliderSpeed(int speed) {
        this.slider.setSpeed(speed);
    }
    
    
    /**
     * Sets the width of the slider of the game.
     * 
     * @param width The width the slider has to get.
     */
    public void setSliderWidth(int width) {
        this.slider.setWidth(width);
    }
    
    
    /**
     * Sets the damage of the ball of the game.
     * 
     * @param damage The damage the ball has to deal to a block.
     */
    public void setBallDamage(int damage) {
        this.ball.setDamage(damage);
    }
    
    
    /**
     * Adds a score to the current game score.
     * 
     * @param score The score that has to be added.
     */
    public static void addScore(int score) {
        ArkanoidModel.score += score;
    }
    
    
    /**
     * Sets the best score the player got.
     * 
     * @param score The score the player got.
     */
    public static void setMaxScore(int score) {
        ArkanoidModel.maxScore = score;
    }
    
    
    /**
     * Sets the current difficulty of the game.
     * 
     * @param dif The difficulty you want the game to be.
     */
    public static void setDifficulty(Difficulty dif){
        ArkanoidModel.difficulty = dif;
    }
    
    
    /**
     * Sets the current lifes of the player.
     * 
     * @param lifes The lifes the player has left.
     */
    public static void setLifes(int lifes){
        ArkanoidModel.lifes = lifes;
    }
    
    
    /**
     * Saves the ArkanoidModel with all the data of the game.
     * 
     * @param model The ArkanoidModel which controls the game.
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

}