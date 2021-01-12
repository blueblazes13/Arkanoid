	package com.lawajo.arkanoid;

import com.lawajo.arkanoid.model.ArkanoidModel;
import com.lawajo.arkanoid.model.BallModel;
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
    
    //data members
    private ArkanoidModel model;
    private SliderModel slider;
    private BallModel ball;
    private ArkanoidView view;
    
    /**
     * Initializes the ArkanoidFXML controller class.
     */
    @FXML
    void initialize() {
        model = new ArkanoidModel();
        ball = new BallModel(350,290);
        slider = new SliderModel(350,270);
        view = new ArkanoidView(model);
        view.addSlider(slider);
        view.addBall(ball);
        view.setFocusTraversable(true);
        
        apPlayField.getChildren().add(view);
        btnMenu.setOnAction(this::toMenu);
        btnReset.setOnAction(this::reset);
        apPlayField.setOnKeyPressed(this::keyPressed);
        ball.startMoving(this);
        slider.setSpeed(10);
        
        //MoveSliderTask task = new MoveSliderTask(, slider, this);
        //task.run();
        //Timer t = new Timer(true);
        //t.scheduleAtFixedRate(task, 0, 25);
    }
    
    /**
     * Checks for input of the Left and Right arrow keys.
     * @param k Keyevent k
     */
    private void keyPressed(KeyEvent k) {
        switch(k.getCode()){
            case LEFT:
                slider.moveLeft();
                //dir = Direction.LEFT;
                update();
                break;
            case RIGHT:
                slider.moveRight();
                //dir = Direction.RIGHT;
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
            System.out.println("Error while loading");
            System.exit(0);
        }
    }
    
    public void reset(ActionEvent t) {
        //view.reset();
        lblScore.setText("10");
    }
    
    /**
     * Check if the ball has passed the slider.
     */
    public void dead() {
        if(ball.getY()>slider.getY()){
            System.out.println("ok");
            try{
            App.setRoot("GameMenuFXML");
            }
            catch (IOException exception) {
                System.out.println("Error while loading");
                System.exit(0);
            }
        }
    }
    
    public void move() {
        if (slider == null) {
            if (ball.getX()== BallModel.RADIUS || ball.getX() == 500 - BallModel.RADIUS) {
                ball.hitVertical();
            } else if (ball.getY() == BallModel.RADIUS) {
                ball.hitHorizontal();
            } else {
                ball.addX();
                ball.addY();
            }            
        } else {
            int blockX = slider.getX();
            int blockY = slider.getY();
            
            if (ball.getX() + BallModel.RADIUS == blockX) {
                ball.hitVertical();
            } else if (ball.getX() - BallModel.RADIUS == blockX + slider.WIDTH) {
                ball.hitVertical();
            } else {
                if (ball.getY() + BallModel.RADIUS == blockY) {
                    ball.hitHorizontal();
                } else if (ball.getY() - BallModel.RADIUS == blockY + slider.HEIGHT) {
                    ball.hitHorizontal();
                } else {
                    move();
                }
            }
        }
        update();
    }
    //public Direction setDirection() {
    //   return Direction.LEFT; 
    //}
}