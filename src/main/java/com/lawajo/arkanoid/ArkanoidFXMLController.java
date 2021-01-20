package com.lawajo.arkanoid;

import com.lawajo.arkanoid.model.ArkanoidModel;
import com.lawajo.arkanoid.model.BoostModel;
import com.lawajo.arkanoid.model.Direction;
import com.lawajo.arkanoid.view.ArkanoidView;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
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
            model = new ArkanoidModel();
        }
        else {
            model = ArkanoidModel.controllingModel;
            ArkanoidModel.controllingModel = null;
        }
        model.newGameTick(this);
        view = new ArkanoidView(model);
        ArkanoidModel.setLifes(1);
        
        view.addSlider(model.getSlider());
        view.addBall(model.getBall());
        
        view.setFocusTraversable(true);
        apPlayField.getChildren().add(view);
        btnMenu.setOnAction(this::toMenu);  
        btnReset.setOnAction(this::reset);
        apPlayField.setOnKeyReleased(this::stopMoving);
        apPlayField.setOnKeyPressed(this::keyPressed);
        setLifeBar();
        System.out.println(ArkanoidModel.lifes);
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
     * @param k Keyevent k
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
        if(model.isDeath()&&ArkanoidModel.getLifes()>0) {
            view.removeBall(model.getBall());
            model.newBall(280, 250);
            view.addBall(model.getBall());
            activateBoost();
            ArkanoidModel.setLifes(ArkanoidModel.getLifes()-1);
        }
//        if(boost.isMoving()) {
//            view.removeBoost(boost);
//            activateBoost();
//        }
        setScore();
        setLifeBar();
        view.update();
    }
    
    /**
     * Go back to the Game menu.
     * @param t Action Event t
     */
    private void toMenu(ActionEvent t) {
        if(ArkanoidModel.lifes > 0){
            try {
                ArkanoidModel.save(model);
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
        model.stopBall();
        switchMenu();
    }
    
    
    /**
     * Resets the current Arkanoid Game.
     * @param t 
     */
    public void reset(ActionEvent t) {
        //#TODO
        model.stopBall();
        this.model = new ArkanoidModel();
        this.view = new ArkanoidView(this.model);
        this.apPlayField.getChildren().clear();
        this.apPlayField.getChildren().add(this.view);
        
        view.addSlider(model.getSlider());
        view.addBall(model.getBall());
        
        view.setFocusTraversable(true);
        update();
        
        /*try {
            ArkanoidModel.save(model);
        } catch (IOException ex){
            ex.printStackTrace();
        }*/
    }
    
    
    /**
     * Activates a random boost.
     * Generates random number from 1 to 5.
     */
    public void activateBoost(){    
        int Num = ThreadLocalRandom.current().nextInt(1,6);
        switch(Num){
            case 1:
                System.out.println("1");
                model.setBallDamage(3);
                update();
                break;
            case 2:
                System.out.println("2");
                model.setSliderWidth(50);
                update();
                break;
            case 3:
                System.out.println("3");
                model.setSliderSpeed(10);
                update();
                break;
            case 4:
                System.out.println("4");
                model.setBallSpeed(3);
                update();
                break;
            case 5:
                System.out.println("5");
                ArkanoidModel.setLifes(ArkanoidModel.getLifes()+1);
                update();
                break;
            default:
                break;
        }
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
        if(model.isDeath()&&ArkanoidModel.getLifes() == 0){
            if(ArkanoidModel.getMaxScore() < ArkanoidModel.getScore()){
            setBestScore();
            }
        }
    }
    
    
    /**
     * Sets the best score the player has achieved so far.
     */
    public void setBestScore(){
        String score = lblScore.getText();
        if(Integer.parseInt(lblScore.getText())>Integer.parseInt(lblBestScore.getText())){
            lblBestScore.setText(score);
            ArkanoidModel.setMaxScore(Integer.parseInt(score));
        }
        switchMenu();
    }
    
    /**
     * Switch back to the GameMenuFXML.
     */
    public void switchMenu(){
        try {
            App.setRoot("GameMenuFXML");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}