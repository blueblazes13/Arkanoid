
package com.lawajo.arkanoid.view;

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
    
    //datamembers
    private ArkanoidModel model;
    
    
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
     * Updates all the ViewObjects with their newest parameters.
     */
    @Override
    public void update() {
        ArrayList<ViewObject> toRemove = new ArrayList<>();
        
        this.getChildren().forEach(obj -> {
            ViewObject vObject = (ViewObject) obj;
            vObject.update();
            
            if (vObject instanceof BlockView) {
                BlockModel block = (BlockModel) vObject.getModel();
                if (block.isDeleted()) {
                    toRemove.add(vObject);
                }
            } else if (vObject instanceof BoostView) {
                BoostModel boostModel = (BoostModel) vObject.getModel();
                if (!boostModel.isMoving()) {
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
    public void removeViewObjects(ViewObject obj) {
        if (obj instanceof BlockView) {
            removeBlock((BlockView) obj);
        } else {
            BoostModel boost = (BoostModel) obj.getModel();
            boost = null;
            this.getChildren().remove(obj);
        }
    }
    
    
    /**
     * Removes a block from the playfield.
     */
    public void removeBlock(BlockView block) {
        BlockModel blockModel = block.getModel();
        this.getChildren().remove(block);
        
        if (blockModel.hasBoost()) {
            System.out.println("Spawned new one!");
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
        for (Node obj: this.getChildren()) {
            ViewObject viewObj = (ViewObject) obj;
            if (viewObj.getModel() == ball) {
                this.getChildren().remove(obj);
            }
        }
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
