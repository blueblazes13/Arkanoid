package com.lawajo.arkanoid;

import com.lawajo.arkanoid.model.ArkanoidModel;
import com.lawajo.arkanoid.model.Direction;
import com.lawajo.arkanoid.view.ArkanoidView;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
        
/**
 * FXML Controller class
 *
 * @author Vanmuysen Ward
 */
public class ArkanoidFXMLController {

    @FXML
    private AnchorPane apPlayField;

    @FXML
    private Label lblScore;

    @FXML
    private Button btnMenu;

    @FXML
    private Label lblBestScore;

    @FXML
    private Button btnReset;
    
    @FXML
    private ProgressBar pbLifes;
    
    //data members
    private ArkanoidModel model;
    private ArkanoidView view;
    
    /**
     * Initializes the ArkanoidFXML controller class.
     */
    @FXML
    void initialize() {
        
        if (ArkanoidModel.controllingModel == null){
            this.model = new ArkanoidModel();
            ArkanoidModel.addScore(-ArkanoidModel.getScore());
            ArkanoidModel.setLifes(3);
        }
        else {
            this.model = ArkanoidModel.controllingModel;
            ArkanoidModel.controllingModel = null;
        }
        this.model.newGameTick(this);
        view = new ArkanoidView(model);
        
        view.addSlider(model.getSlider());
        view.addBall(model.getBall());
        
        view.setFocusTraversable(true);
        apPlayField.getChildren().add(view);
        btnMenu.setOnAction(this::toMenu);  
        btnReset.setOnAction(this::reset);
        apPlayField.setOnKeyReleased(this::stopMoving);
        apPlayField.setOnKeyPressed(this::keyPressed);
        setLifeBar();
        lblBestScore.setText(Integer.toString(ArkanoidModel.getMaxScore()));
    }
    
    
    /**
     * Stops the slider from moving upon key release.
     * @param e KeyEvent e
     */
    private void stopMoving(KeyEvent e){
        switch(e.getCode()){
            case LEFT:
                model.stopSlider(Direction.LEFT);
                update();
                break;
            case RIGHT:
                model.stopSlider(Direction.RIGHT);
                update();
                break;
        }
    }
    
    
    /**
     * Checks for input of the Left and Right arrow keys.
     * @param k KeyEvent k
     */
    private void keyPressed(KeyEvent k) {
        switch(k.getCode()){
            case LEFT:
                model.moveSlider(Direction.LEFT);
                update();
                break;
            case RIGHT:
                model.moveSlider(Direction.RIGHT);
                update();
                break;
        }
    }
    
        
    /**
     * Updates the view.
     */
    public void update() {
        gameOver();
        setScore();
        setLifeBar();
        view.update();
    }
    
    /**
     * Go back to the Game menu.
     * @param t ActionEvent t
     */
    private void toMenu(ActionEvent t) {
        if(ArkanoidModel.lifes >= 0){
            try {
                ArkanoidModel.save(model);
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
        if (ArkanoidModel.getMaxScore()<=ArkanoidModel.getScore()){
            setBestScore();
        }
        switchMenu();
    }
    
    
    /**
     * Switch back to the GameMenuFXML.
     */
    public void switchMenu(){
        model.stopBall();
        try {
            App.setRoot("GameMenuFXML");
        } catch (IOException ex) {
            ex.printStackTrace();
        }   
    }
    
    
    /**
     * Checks if the game is over.
     */
    public void gameOver(){
        if(this.model.isDeath() && ArkanoidModel.getLifes() > 0) {
            view.removeBall(this.model.getBall());
            this.model.newBall(280, 250);
            view.addBall(this.model.getBall());
            ArkanoidModel.setLifes(ArkanoidModel.getLifes()-1);
        }
        else if(this.model.isDeath() && ArkanoidModel.getLifes() == 0) {
            if(ArkanoidModel.getMaxScore() >= ArkanoidModel.getScore()){
                setBestScore();
            }
            this.model = new ArkanoidModel();
            switchMenu();
        }
        else if(this.model.allBlocksBroken()){
            ArkanoidModel.controllingModel = this.model;
            this.model = new ArkanoidModel();
            setBestScore();
            switchMenu();
        }
    }
    
    
    /**
     * Sets all the parameters for a new game.
     */
    public void newGame(){
        ArkanoidModel.addScore(-ArkanoidModel.getScore());
        ArkanoidModel.setLifes(3);
        this.model = new ArkanoidModel();
        this.view = new ArkanoidView(this.model);
        this.apPlayField.getChildren().clear();
        this.apPlayField.getChildren().add(this.view);
        
        view.addSlider(model.getSlider());
        view.addBall(model.getBall());
        
        view.setFocusTraversable(true);
    }
    
    
    /**
     * Resets the current Arkanoid Game.
     * @param t ActionEvent t
     */
    public void reset(ActionEvent t) {
        this.model.stopBall();
        newGame();
        update();
    }
    
    
    /**
     * Sets the new value of the life bar.
     */
    public void setLifeBar(){
        switch (ArkanoidModel.lifes) {
            case 3:
                pbLifes.setProgress(1);
                break;
            case 2:
                pbLifes.setProgress(0.66);
                break;
            case 1:
                pbLifes.setProgress(0.33);
                break;
            case 0:
                pbLifes.setProgress(0);
                break;
            default:
                break;
        }
    }
    
    
    /**
     * Sets the score on the label.
     */
    public void setScore(){
        lblScore.setText(Integer.toString(ArkanoidModel.getScore()));
        if (ArkanoidModel.getMaxScore()<ArkanoidModel.getScore()){
            setBestScore();
        }
    }
    
    
    /**
     * Sets the best score the player has achieved so far.
     */
    public void setBestScore(){
        lblBestScore.setText(lblScore.getText());
        ArkanoidModel.setMaxScore(Integer.parseInt(lblScore.getText()));
    }
}