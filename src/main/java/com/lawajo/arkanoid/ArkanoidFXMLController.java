	package com.lawajo.arkanoid;

import com.lawajo.arkanoid.model.ArkanoidModel;
import com.lawajo.arkanoid.model.BallModel;
import com.lawajo.arkanoid.model.SliderModel;
import com.lawajo.arkanoid.view.ArkanoidView;
import com.lawajo.arkanoid.view.Ball;
import com.lawajo.arkanoid.view.Slider;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

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
    private Slider sliderView;
    
    @FXML
    void initialize() {
        model = new ArkanoidModel();
        ball = new BallModel(350,250);
        slider = new SliderModel(350,270);
        view = new ArkanoidView(model);
        view.addBall(ball);
        sliderView = new Slider(slider);
        view.setFocusTraversable(true);
        
        apPlayField.getChildren().addAll(view, sliderView);
        apPlayField.setOnKeyPressed(this::keyPressed);
        update();
    }
    
    private void keyPressed(KeyEvent k) {
        switch(k.getCode()){
            case LEFT:
                slider.moveLeft();
                update();
                break;
            case RIGHT:
                slider.moveRight();
                update();
                break;
        }
    }
    
    public void update() {
        view.update();
    }
}
