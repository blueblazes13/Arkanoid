package com.lawajo.arkanoid;

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

    @FXML
    void initialize() {
        
    }
}
