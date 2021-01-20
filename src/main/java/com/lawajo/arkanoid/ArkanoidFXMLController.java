package com.lawajo.arkanoid;

import com.lawajo.arkanoid.model.ArkanoidModel;
import com.lawajo.arkanoid.model.BallModel;
import com.lawajo.arkanoid.model.BoostModel;
import com.lawajo.arkanoid.model.SliderModel;
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
    private SliderModel slider;
    private BallModel ball;
    private BoostModel boost;
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
        
        ball = new BallModel(280,250);
        slider = new SliderModel(280,270);
        boost = new BoostModel(280,200);
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
        if(ball.checkDeath()) { 
            newBall();
        }
        if(boost.isMoving()) {
            view.removeBoost(boost);
            //activateBoost();
        }
        setScore();
        setLifeBar();
        view.update();
    }
    
    /**
     * Go back to the Game menu.
     * @param t Action Event t
     */
    private void toMenu(ActionEvent t) {
        try {
            ArkanoidModel.save(model);
        } catch (IOException ex){
            ex.printStackTrace();
        }
        try {
            App.setRoot("GameMenuFXML");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Resets the current Arkanoid Game.
     * @param t 
     */
    public void reset(ActionEvent t) {
        //#TODO
        model = new ArkanoidModel();
        //newBall();
        update();
        
        /*try {
            ArkanoidModel.save(model);
        } catch (IOException ex){
            ex.printStackTrace();
        }*/
    }
    
    /**
     * Spawns new ball when last ball got past the slider.
     */
    public void newBall(){
        if(ArkanoidModel.lifes>=0){
            view.removeBall(ball);
            this.ball = new BallModel(300,250);
            view.addBall(ball);
            ball.startMoving(this, model, slider);
            update();
            
            System.out.println(ArkanoidModel.getLifes());
            ArkanoidModel.setLifes(ArkanoidModel.getLifes()-1);
            System.out.println(ArkanoidModel.getLifes());
        }   
        else {
            view.removeBall(ball);
            update();
        }
    }
    
    /**
     * Activates a random boost.
     */
    public void activateBoost(){    
        int Num = ThreadLocalRandom.current().nextInt(1,4);
        switch(Num){
            case 1:
                ball.setDamage(3);
                update();
                break;
            case 2:
                //slider.setWidth(dqdq);
                update();
                break;
            case 3:
                slider.setSpeed(5);
                update();
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
    }
    
    /**
     * Sets the best score the player has achieved so far.
     */
    public void setBestScore(){
        String score = lblScore.getText();
        if(Integer.parseInt(lblScore.getText())>Integer.parseInt(lblBestScore.getText())){
            lblBestScore.setText(score);
        }
        else;
    }
}