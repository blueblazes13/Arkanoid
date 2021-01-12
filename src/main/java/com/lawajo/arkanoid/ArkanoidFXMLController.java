	package com.lawajo.arkanoid;

import com.lawajo.arkanoid.model.ArkanoidModel;
import com.lawajo.arkanoid.model.BallModel;
import com.lawajo.arkanoid.model.Direction;
import com.lawajo.arkanoid.model.SliderModel;
import com.lawajo.arkanoid.view.ArkanoidView;
import com.lawajo.arkanoid.view.Ball;
import com.lawajo.arkanoid.view.Slider;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
        
/**
 * FXML Controller class
 *
 * @author Ward
 */
public class ArkanoidFXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane apPlayField;

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
        ball = new BallModel(350,250);
        slider = new SliderModel(350,270);
        view = new ArkanoidView(model);
        view.addSlider(slider);
        view.addBall(ball);
        view.setFocusTraversable(true);
        
        apPlayField.getChildren().add(view);
        apPlayField.setOnKeyPressed(this::keyPressed);
        
        //MoveSliderTask task = new MoveSliderTask(, slider, this);
        //task.run();
        //Timer t = new Timer(true);
        //t.scheduleAtFixedRate(task, 0, 25);
    }
    
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
    
    public void update() {
        view.update();
    }
    /*
    public void move(BlockModel block) {
        if (block == null) {
            if (this.x == BallModel.RADIUS || this.x == 500 - BallModel.RADIUS) {
                hitVertical();
            } else if (this.y == BallModel.RADIUS) {
                hitHorizontal();
            } else {
                addX();
                addY();
            }
        } else {
            int blockX = block.getX();
            int blockY = block.getY();
            
            if (this.x + BallModel.RADIUS == blockX) {
                hitVertical();
            } else if (this.x - BallModel.RADIUS == blockX + BlockModel.WIDTH) {
                hitVertical();
            } else {
                if (y + BallModel.RADIUS == blockY) {
                    hitHorizontal();
                } else if (y - BallModel.RADIUS == blockY + BlockModel.HEIGHT) {
                    hitHorizontal();
                } else {
                    move();
                }
            }
        }
    }*/
    //public Direction setDirection() {
    //   return Direction.LEFT; 
    //}
    /*
    public void dead() {
        try{
        App.setRoot("GameMenuFXML");
        }
        catch (IOException exception) {
            System.out.println("Error while loading");
            System.exit(0);
        }
    }*/
}
