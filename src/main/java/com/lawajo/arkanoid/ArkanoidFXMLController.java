	package com.lawajo.arkanoid;

import com.lawajo.arkanoid.model.ArkanoidModel;
import com.lawajo.arkanoid.model.BallModel;
import com.lawajo.arkanoid.model.BoostModel;
import com.lawajo.arkanoid.model.Direction;
import com.lawajo.arkanoid.model.SliderModel;
import com.lawajo.arkanoid.view.ArkanoidView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.paint.Color;
        
/**
 * FXML Controller class
 *
 * @author Ward
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
    private SliderModel slider;
    private BallModel ball;
    private BoostModel boost;
    private ArkanoidView view;
    
    /**
     * Initializes the ArkanoidFXML controller class.
     */
    @FXML
    void initialize() {
        model = new ArkanoidModel();
        ball = new BallModel(300,250);
        slider = new SliderModel(300,270);
        boost = new BoostModel(300,200);
        view = new ArkanoidView(model);
        view.addSlider(slider);
        view.addBall(ball);
        view.addBoost(boost);
        view.setFocusTraversable(true);
        
        apPlayField.getChildren().add(view);
        btnMenu.setOnAction(this::toMenu);  
        btnReset.setOnAction(this::reset);
        apPlayField.setOnKeyReleased(this::stopMoving);
        apPlayField.setOnKeyPressed(this::keyPressed);
        ball.startMoving(this, model, slider);
        boost.startMoving(this, model, slider);
        slider.setSpeed(5);
    }  
    
    /**
     * Stops the slider from moving upon key release
     * @param e KeyEvent e
     */
    private void stopMoving(KeyEvent e){
        switch(e.getCode()){
            case LEFT:
                slider.stopLeft();
                update();
                break;
            case RIGHT:
                slider.stopRight();
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
                slider.toLeft(this);
                update();
                break;
            case RIGHT:
                slider.toRight(this);
                update();
                break;
        }
    }
    
    /**
     * Updates the view.
     */
    public void update() {
        view.update();
    }
    
    private void toMenu(ActionEvent t) {
        try{
        App.setRoot("GameMenuFXML");
        }
        catch (IOException exception) {
            System.out.println("Error while loading" + exception.getMessage());
        }
    }
    
    public void reset(ActionEvent t) {
        try{
        App.setRoot("ArkanoidFXML");
        }
        catch (IOException exception) {
            System.out.println("Error while loading" + exception.getMessage());
        }
        lblScore.setText(Integer.toString(ArkanoidModel.getScore()));
    }
    
    /**
     * Sets the score on the label
     */
    public void setScore(){
        lblScore.setText(Integer.toString(ArkanoidModel.getScore()));
    }
    
    /**
     * Sets the new value of the life bar
     */
    public void setLifeBar(){
        if (ArkanoidModel.lifes == 3){
            pbLifes.setProgress(100.00);
        }
        if (ArkanoidModel.lifes == 2){
            pbLifes.setProgress(66.66);
        }
        if (ArkanoidModel.lifes == 1){
            pbLifes.setProgress(33.33);
        }
    }
    
    /**
     * 
     */
    public void setBestScore(){
        lblBestScore.setText("Effe testen");
    }
}