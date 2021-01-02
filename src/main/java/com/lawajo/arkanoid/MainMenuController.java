/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
/**
 * FXML Controller class
 *
 * @author Ward
 */
public class MainMenuController /*implements Initializable*/ {
    
    @FXML
    private AnchorPane apnAchtergrond;
    
    @FXML
    private Button btnStart;

    @FXML
    private Button btnInstellingen;

    @FXML
    private void btnStart() throws IOException {
        App.setRoot("ArkanoidFXML");
    }
    
    @FXML
    private void btnInstellingen() throws IOException {
        App.setRoot("InstellingenFXML");
    }
   
    
    /*
    /**
     * Initializes the controller class.
     * /
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }*/    
    
}