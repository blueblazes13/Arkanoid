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
    private Difficulty diff = Difficulty.EASY;
    private  int bestScore;

    /**
     * Initializes the Game Menu controller class.
     */
    @FXML
    void initialize() {
        miEasy.setOnAction((m) -> {
            lblCurrentDifficulty.setText("Easy");
            diff = Difficulty.EASY;
        });
        miNormal.setOnAction((m) -> {
            lblCurrentDifficulty.setText("Normal");
            diff = Difficulty.NORMAL;
        });
        miHard.setOnAction((m) -> {
            lblCurrentDifficulty.setText("Hard");
            diff = Difficulty.HARD;
        });
        miExpert.setOnAction((m) -> {
            lblCurrentDifficulty.setText("EXPERT");
            diff = Difficulty.EXPERT;
        });
        btnStartGame.setOnAction(this::start);
        setDifficulty(diff);
    }

    public void start(ActionEvent s) {
        try{
        App.setRoot("ArkanoidFXML");
        }
        catch (IOException exception) {
            System.out.println("Error while loading");
            System.exit(0);
        }
    }
    
    /**
     * Sets the difficulty for the game.
     * @param diff
     */
    public void setDifficulty(Difficulty diff){
        if(diff == Difficulty.EASY){
            System.out.println("Dikke Boktor");
        }
        else if(diff == Difficulty.NORMAL){
            
        }
        else if(diff == Difficulty.HARD){
            
        }
        else if(diff == Difficulty.EXPERT){
            
        }
    }
    
    /**
     * Eventueel model aanmaken die de scores bijhoud om deze aan de controller door te voeren.
     * Of deze bij het ArkanoidModel aanmaken
     * Sets the text labels for the latest and best score
     * @param score The score of the last game
     */
    //public void setLabels(score){
        //
        //if dead and score > latestscore
        //lblBestScore.set(score);
        //else
        //lblLatestScore.set(score)
    //}
}