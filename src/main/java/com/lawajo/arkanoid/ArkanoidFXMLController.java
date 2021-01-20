package com.lawajo.arkanoid;

import com.lawajo.arkanoid.model.ArkanoidModel;
import com.lawajo.arkanoid.model.BallModel;
import com.lawajo.arkanoid.model.BoostModel;
import com.lawajo.arkanoid.model.Direction;
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
        model.newGameTick(this);
//        boost = new BoostModel(280,200);///// 5.1-5.3
        view = new ArkanoidView(model);
        
        view.addSlider(model.getSlider());
        view.addBall(model.getBall());;
        
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
        if(model.isDeath()) { 
            model.newBall(280, 250);
        }
//        if(boost.isMoving()) {
//            view.removeBoost(boost);
//            //activateBoost();
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
        model.stopBall();
        this.model = new ArkanoidModel();
        this.view = new ArkanoidView(this.model);
        this.apPlayField.getChildren().clear();
        this.apPlayField.getChildren().add(this.view);
        
        view.addSlider(model.getSlider());
        view.addBall(model.getBall());;
        
        view.setFocusTraversable(true);
        
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
//    public void newBall(){
//        if(ArkanoidModel.lifes>=0){
//            view.removeBall(ball);
//            this.ball = new BallModel(300,250);
//            view.addBall(ball);
//            ball.startMoving(this, model, slider);
//            update();
//            
//            System.out.println(ArkanoidModel.getLifes());
//            ArkanoidModel.setLifes(ArkanoidModel.getLifes()-1);
//            System.out.println(ArkanoidModel.getLifes());
//        }   
//        else {
//            view.removeBall(ball);
//            update();
//        }
//    }
    
    /**
     * Activates a random boost.
     */
    public void activateBoost(){    
        int Num = ThreadLocalRandom.current().nextInt(1,4);
        switch(Num){
            case 1:
                model.setBallDamage(3);
                update();
                break;
            case 2:
                model.setSliderWidth(80);
                update();
                break;
            case 3:
                model.setSliderSpeed(5);
//                model.setBallSpeed(3);          Dit is ook nog een optie.
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