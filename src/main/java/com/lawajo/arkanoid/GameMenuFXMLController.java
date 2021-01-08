package com.lawajo.arkanoid;

import com.lawajo.arkanoid.model.Difficulty;
import static com.lawajo.arkanoid.model.Difficulty.EASY;
import static com.lawajo.arkanoid.model.Difficulty.EXPERT;
import static com.lawajo.arkanoid.model.Difficulty.HARD;
import static com.lawajo.arkanoid.model.Difficulty.NORMAL;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

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
    
    @FXML
    void initialize() {
        miEasy.setOnAction((t) -> {
            lblCurrentDifficulty.setText("Easy");
            diff = EASY;
        });
        miNormal.setOnAction((t) -> {
            lblCurrentDifficulty.setText("Normal");
            diff = NORMAL;
        });
        miHard.setOnAction((t) -> {
            lblCurrentDifficulty.setText("Hard");
            diff = HARD;
        });
        miExpert.setOnAction((t) -> {
            lblCurrentDifficulty.setText("EXPERT");
            diff = EXPERT;
        });
    }
}
