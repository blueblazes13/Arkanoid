package com.lawajo.arkanoid;

import com.lawajo.arkanoid.model.Difficulty;
import static com.lawajo.arkanoid.model.Difficulty.EASY;
import static com.lawajo.arkanoid.model.Difficulty.EXPERT;
import static com.lawajo.arkanoid.model.Difficulty.HARD;
import static com.lawajo.arkanoid.model.Difficulty.NORMAL;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

/**
 * FXML Controller class
 *
 * @author Ward
 */
public class GameMenuFXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnStartGame;

    @FXML
    private Label lblBestScore;

    @FXML
    private Label lblLatestScore;

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
    
    //data Members
    private Difficulty diff;
    
    /**
     * Initializes the Game Menu controller class.
     */
    @FXML
    void initialize() {
        miEasy.setOnAction((m) -> {
            lblCurrentDifficulty.setText("Easy");
            diff = EASY;
        });
        miNormal.setOnAction((m) -> {
            lblCurrentDifficulty.setText("Normal");
            diff = NORMAL;
        });
        miHard.setOnAction((m) -> {
            lblCurrentDifficulty.setText("Hard");
            diff = HARD;
        });
        miExpert.setOnAction((m) -> {
            lblCurrentDifficulty.setText("EXPERT");
            diff = EXPERT;
        });
        btnStartGame.setOnAction(this::start);
    }

    private void start(ActionEvent s) {
        
        try{
        App.setRoot("ArkanoidFXML");
        }
        catch (IOException exception) {
            System.out.println("Error loading");
            System.exit(0);
        }
    }
}
