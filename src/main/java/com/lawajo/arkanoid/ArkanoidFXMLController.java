package com.lawajo.arkanoid;

import com.lawajo.arkanoid.model.ArkanoidModel;
import com.lawajo.arkanoid.model.BallModel;
import com.lawajo.arkanoid.model.SliderModel;
import com.lawajo.arkanoid.view.ArkanoidView;
import com.lawajo.arkanoid.view.Ball;
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
    private Ball ballView;
    
    @FXML
    void initialize() {
        /**
         * Dit stukje heb ik geschreven om te laten zien wat je kan met het
         * block object dat ik geschreven heb. Je kan dit al runnen zodat je kan
         * zien wat er precies gebeurt. De javadoc is volledig ingevuld
         * dus die kan je altijd raadplegen om te kijken wat ik nog heb toegevoegd.
         * Mocht er toch iets zijn wat je nodig hebt en wat ik er nog niet heb
         * geïmplementeerd, aarzel niet om me te sturen en dan voeg ik dat voor je toe.
         * 
         * Gr. Joey
         */
        
        model = new ArkanoidModel();
        ball = new BallModel();
        view = new ArkanoidView(model);
        ballView = new Ball(ball);

        apPlayField.getChildren().addAll(view, ball);
        apPlayField.setOnKeyPressed(this::keyPressed);
        update();
        /*Block greenBlock = new Block(300, 50);
        Block redBlock = new Block(300 + Block.WIDTH, 20);
        redBlock.setY(50);
        redBlock.setColor(Color.RED);
        
        apPlayField.getChildren().addAll(greenBlock, redBlock);
        */
    }
    
    private void keyPressed(KeyEvent k) {
        switch(k.getCode()){
            case LEFT:
                slider.moveLeft();
                break;
            case RIGHT:
                slider.moveRight();
                break;
        }
    }
    
    public void update() {
        view.update();
    }
}
