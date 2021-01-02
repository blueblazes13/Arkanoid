/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid;

import com.lawajo.arkanoid.model.SpelerPlatform;
import com.lawajo.arkanoid.model.Balletje;
import com.lawajo.arkanoid.model.Blokken;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Ward
 */
public class ArkanoidFXMLController implements Initializable {

    //@FXML
    
    //data members
    private SpelerPlatform balk;
    private Balletje bal;
    private Blokken blok;
    
    
    
    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setDataMembers(SpelerPlatform balk, Balletje bal, Blokken blok){
        this.balk = balk;
        this.bal = bal;
        this.blok = blok;
    }
    
    
}
