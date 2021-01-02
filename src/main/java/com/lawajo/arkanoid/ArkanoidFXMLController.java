package com.lawajo.arkanoid;

import com.lawajo.arkanoid.view.Block;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class ArkanoidFXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane apPlayField;

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
        
        Block greenBlock = new Block(300, 50);
        
        Block redBlock = new Block(300 + Block.WIDTH, 20);
        redBlock.setY(50);
        redBlock.setColor(Color.RED);
        
        apPlayField.getChildren().addAll(greenBlock, redBlock);
    }
}
