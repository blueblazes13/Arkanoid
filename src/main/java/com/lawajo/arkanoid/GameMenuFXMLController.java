package com.lawajo.arkanoid;

import com.lawajo.arkanoid.model.ArkanoidModel;
import com.lawajo.arkanoid.model.Difficulty;
import static com.lawajo.arkanoid.model.Difficulty.EASY;
import static com.lawajo.arkanoid.model.Difficulty.EXPERT;
import static com.lawajo.arkanoid.model.Difficulty.HARD;
import static com.lawajo.arkanoid.model.Difficulty.NORMAL;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

/**
 * FXML Controller class
 *
 * @author Vanmuysen Ward
 */
public class GameMenuFXMLController {
    
    @FXML
    private Button btnStartGame;

    @FXML
    private Label lblBestScore;

    @FXML
    private Label lblLatestScore;
    
    @FXML
    private Label lblWinMessage;

    @FXML
    private MenuItem miEasy;

    @FXML
    private MenuItem miNormal;

    @FXML
    private MenuItem miHard;

    @FXML
    private MenuItem miExpert;

    @FXML
    private Label lblCurrentDifficulty;
    
    @FXML
    private Button btnLoad;

    //Datamembers
    private Difficulty diff = EASY;
    private  int bestScore;

    
    /**
     * Initializes the Game Menu controller class.
     */
    @FXML
    void initialize() {
        setWinMessage();
        
        miEasy.setOnAction((m) -> {
            lblCurrentDifficulty.setText("Easy");
            diff = EASY;
            ArkanoidModel.setDifficulty(diff);
        });
        
        miNormal.setOnAction((m) -> {
            lblCurrentDifficulty.setText("Normal");
            diff = NORMAL;
            ArkanoidModel.setDifficulty(diff);
        });
        
        miHard.setOnAction((m) -> {
            lblCurrentDifficulty.setText("Hard");
            diff = HARD;
            ArkanoidModel.setDifficulty(diff);
        });
        
        miExpert.setOnAction((m) -> {
            lblCurrentDifficulty.setText("EXPERT");
            diff = EXPERT;
            ArkanoidModel.setDifficulty(diff);
        });
        
        btnStartGame.setOnAction(this::start);
        btnLoad.setOnAction(this::load);
        setScores();
    }

    
    /**
     * Starts the game by going to the game screen.
     * 
     * @param s ActionEvent of the button.
     */
    public void start(ActionEvent s) {
        try{
            App.setRoot("ArkanoidFXML");
        }
        catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    
    /**
     * Loads the game from data file.
     * 
     * @param l ActionEvent from the button.
     */
    public void load(ActionEvent l) {
        try {
            ArkanoidModel.controllingModel = ArkanoidModel.load();
            App.setRoot("ArkanoidFXML");
        } catch (IOException ex){
            ex.printStackTrace(System.out);
        }    
    }   
    
    
    /**
     * Sets the latest and best scores.
     */
    public void setScores() {
        lblLatestScore.setText(Integer.toString(ArkanoidModel.getScore()));
        lblBestScore.setText(Integer.toString(ArkanoidModel.getMaxScore()));
    }
    
    
    /**
     * Sets a win message if you've won last game.
     */
    public void setWinMessage() {
        if (ArkanoidModel.controllingModel != null){
            lblWinMessage.setText("Je hebt " + ArkanoidModel.getDifficulty() + " uitgespeeld!");
            ArkanoidModel.controllingModel = null;
        } else {
            lblWinMessage.setText("");
        }
    }
}