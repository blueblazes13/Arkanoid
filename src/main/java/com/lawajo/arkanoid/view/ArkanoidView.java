
package com.lawajo.arkanoid.view;

import com.lawajo.arkanoid.Deletable;
import com.lawajo.arkanoid.model.ArkanoidModel;
import com.lawajo.arkanoid.model.BallModel;
import com.lawajo.arkanoid.model.BlockModel;
import com.lawajo.arkanoid.model.BoostModel;
import com.lawajo.arkanoid.model.SliderModel;
import java.util.ArrayList;
import javafx.scene.Node;


/**
 *
 * @authors Lander Ketelbuters, Joey Koster, Ward Vanmuysen
 */
public class ArkanoidView extends ViewObject {
    
    //Datamembers
    private final ArkanoidModel model;
    
    
    /**
     * Initializes a new ArkanoidView object to control the game views.
     * 
     * @param model The model to control the view.
     */
    public ArkanoidView(ArkanoidModel model) {
        this.model = model;
        
        for (int i = 0; i < ArkanoidModel.WIDTH; i++) {
            for (int j = 0; j < ArkanoidModel.HEIGHT; j++) {
                BlockModel blockModel = model.getBlock(i, j);
                
                if (blockModel == null) continue;
                
                BlockView blockView = new BlockView(blockModel);
                this.getChildren().add(blockView);
            }
        }
    }
    
    
    /**
     * Updates all the ViewObjects with their newest parameters from the model.
     */
    @Override
    public void update() {
        ArrayList<ViewObject> toRemove = new ArrayList<>();
        
        this.getChildren().forEach(obj -> {
            ViewObject vObject = (ViewObject) obj;
            vObject.update();
            
            if (vObject.getModel() instanceof Deletable) {
                Deletable deletable = (Deletable) vObject.getModel();
                if (deletable.isDeleted()) {
                    toRemove.add(vObject);
                }
            }
        });
        
        toRemove.forEach(this::removeViewObjects);
    }
    
    
    /**
     * Removes the view of the block objects and the view of the boost objects. 
     *
     * @param obj is an blockview or boostview.
     */
    private void removeViewObjects(ViewObject obj) {
        if (obj instanceof BlockView) {
            removeBlock((BlockView) obj);
        } else {
            this.getChildren().remove(obj);
        }
    }
    
    
    /**
     * Removes a block from the playfield.
     * 
     * @param block The block that has to be removed.
     */
    private void removeBlock(BlockView block) {
        BlockModel blockModel = block.getModel();
        this.getChildren().remove(block);
        
        if (blockModel.hasBoost()) {
            addBoost(blockModel.getBoost());
        }
    }
    
    
    /**
     * Adds an extra ball object to the playfield.
     * 
     * @param ball The controller of the ball.
     */
    public void addBall(BallModel ball) {
        BallView ballView = new BallView(ball);
        this.getChildren().add(ballView);
    }
    
    
    /**
     * Removes a random ball from the playfield.
     *
     * @param ball BallModel ball.
     */
    public void removeBall(BallModel ball) {
        ViewObject ballObject = null;
        
        for (Node obj: this.getChildren()) {
            ViewObject viewObj = (ViewObject) obj;
            if (viewObj.getModel() == ball) {
                ballObject = viewObj;
                break;
            }
        }
        
        this.getChildren().remove(ballObject);
    }
    
    
    /**
     * Adds a slider to the field.
     * 
     * @param slider is a new slider. 
     */
    public void addSlider(SliderModel slider) {
        SliderView sliderView = new SliderView(slider);
        this.getChildren().add(sliderView);
    }
    
    
    /**
     * Adds a boost to the field.
     *
     * @param boost is a new boost.
     */
    public void addBoost(BoostModel boost){
        BoostView boostView = new BoostView(boost);
        this.getChildren().add(boostView);
        boost.startMoving(this.model);
    }
    
    
    /**
     * Gets the arkanoidmodel that plays the game.
     *
     * @return The arkanoidmodel that controls the arkanoidview.
     */
    @Override
    public ArkanoidModel getModel() {
        return this.model;
    }
    
}
