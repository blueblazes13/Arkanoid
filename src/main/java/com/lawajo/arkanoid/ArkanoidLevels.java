
package com.lawajo.arkanoid;

import com.lawajo.arkanoid.model.ArkanoidModel;
import com.lawajo.arkanoid.model.BlockModel;
import com.lawajo.arkanoid.model.BoostModel;


/**
 *
 * @author Lander Ketelbuters, JoeyKoster, Ward Vanmuysen
 */
public class ArkanoidLevels {
    
    private ArkanoidModel model;
    
    /**
     * Initializes a arkanoid level. 
     * This can be easy, normal, hard or expert.
     */
    public ArkanoidLevels(ArkanoidModel model) {
        this.model = model;
    }
    
    
    /**
     * First easy level
     * 
     * @return setup of the level in BlockModels 
     */
    public BlockModel[][] getEasy1() {
        BlockModel[][] playField = new BlockModel[11][11];
        
        
        // First (Highest) two layers
        for(int i = 0; i < 11 ; i++ ) {
            for(int j = 0; j < 2 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(5);
                if(i == 8 && j == 0 || i == 5 && j == 1){
                    block.setBoost(new BoostModel(30 + block.WIDTH*i + 1*i +block.WIDTH/2, 30 + block.HEIGHT*j + 1*j + block.HEIGHT/2, this.model));
                }
                playField[i][j] = block;    
            }    
        }
        
        
        // 3rd + 4th layer
        for(int i = 0; i < 11 ; i++ ) {
            for(int j = 2; j < 4 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(3);
                if(i == 7 && j == 2 || i == 2 && j == 3){
                    block.setBoost(new BoostModel(30 + block.WIDTH*i + 1*i +block.WIDTH/2, 30 + block.HEIGHT*j + 1*j + block.HEIGHT/2, this.model));
                }
                playField[i][j] = block;    
            }    
        }
        
        
        // 5th + 6th layer
        for(int i = 0; i < 11 ; i++ ) {
            for(int j = 4; j < 6 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(1);
                if(i == 3 && j == 4){
                    block.setBoost(new BoostModel(30 + block.WIDTH*i + 1*i +block.WIDTH/2, 30 + block.HEIGHT*j + 1*j + block.HEIGHT/2, this.model));
                }
                playField[i][j] = block;    
            }    
        }
        
        return playField;
    }

    
    /**
     * First Normal level
     * 
     * @return setup of the level in BlockModels
     */
    public BlockModel[][] getNormal1() {
        BlockModel[][] playField = new BlockModel[11][11];
        
        
        // First (Highest) layer
        for(int i = 0; i < 11 ; i++ ) {
            for(int j = 0; j <= 0 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(7);
                playField[i][j] = block;    
            }    
        }    
        
        
        // 2nd layer, 3rd layer and 4th layer
        for(int i = 0; i < 11 ; i = i+2 ) {
            for(int j = 1; j <= 3 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(5);
                if(i == 9 && j == 6){
                    block.setBoost(new BoostModel(30 + block.WIDTH*i + 1*i +block.WIDTH/2, 30 + block.HEIGHT*j + 1*j + block.HEIGHT/2, this.model));
                }
                playField[i][j] = block;    
            }    
        }
        
        
        // 5th layer, 6st layer and 7nd layer
        for(int i = 1; i < 11 ; i = i+2 ) {
            for(int j = 4; j <= 6 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(3);
                if(i == 3 && j == 5){
                    block.setBoost(new BoostModel(30 + block.WIDTH*i + 1*i +block.WIDTH/2, 30 + block.HEIGHT*j + 1*j + block.HEIGHT/2, this.model));
                }
                playField[i][j] = block;    
            }    
        }
        
        
        // 8th layer
        for(int i = 0; i < 11 ; i++ ) {
            for(int j = 7; j <= 7 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(7);
                if(i == 7){
                    block.setBoost(new BoostModel(30 + block.WIDTH*i + 1*i +block.WIDTH/2, 30 + block.HEIGHT*j + 1*j + block.HEIGHT/2, this.model));
                }
                playField[i][j] = block;    
            }    
        }
        
        
        // filling finals
        for(int i = 0; i < 11 ; i++ ) {
            for(int j = 8; j < 10 ; j++) {
                playField[i][j] = null;    
            }    
        }
        
        
        
        
        return playField;
    }

    
 /**
     * First Hard level
     * 
     * @return setup of the level in BlockModels
     */
    public BlockModel[][] getHard1() {
        BlockModel[][] playField = new BlockModel[11][11];
        
        
        // First layer
        for(int i = 0; i < 11 ; i= i+2) {
            for(int j = 0; j < 1 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(2);
                playField[i][j] = block;    
            }    
        }
        
        
        // 2nd layer
        for(int i = 1; i < 10 ; i = i + 2 ) {
            for(int j = 1; j < 2 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(4);
                playField[i][j] = block;    
            }    
        }
        
        
        // 3rd layer
        for(int i = 2; i < 9 ; i = i + 2 ) {
            for(int j = 2; j < 3 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(6);
                if(i==5){
                    block.setBoost(new BoostModel(30 + block.WIDTH*i + 1*i +block.WIDTH/2, 30 + block.HEIGHT*j + 1*j + block.HEIGHT/2, this.model));
                }
                playField[i][j] = block;    
            }    
        }
        
        
        // 4th layer
        for(int i = 3; i < 8 ; i = i + 2 ) {
            for(int j = 3; j < 4 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(8);
                if(i==7){
                    block.setBoost(new BoostModel(30 + block.WIDTH*i + 1*i +block.WIDTH/2, 30 + block.HEIGHT*j + 1*j + block.HEIGHT/2, this.model));
                }
                playField[i][j] = block;    
            }    
        }
        
        
        // 5th layer
        for(int i = 4; i < 7 ; i = i + 2 ) {
            for(int j = 4; j < 5 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(10);
                playField[i][j] = block;    
            }    
        }
        
        
        // 6th layer
        for(int i = 5; i < 6 ; i++ ) {
            for(int j = 5; j < 6 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(12);
                if(i==5){
                    block.setBoost(new BoostModel(30 + block.WIDTH*i + 1*i +block.WIDTH/2, 30 + block.HEIGHT*j + 1*j + block.HEIGHT/2, this.model));
                }
                playField[i][j] = block;    
            }    
        }
        
        
        // 7nd layer
        for(int i = 4; i < 7 ; i++ ) {
            for(int j = 6; j < 7 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(10);
                playField[i][j] = block;    
            }    
        }
        
        
        // 8th layer
        for(int i = 3; i < 8 ; i++ ) {
            for(int j = 7; j < 8 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(8);
                playField[i][j] = block;    
            }    
        }
        
        
        // 9nd layer
        for(int i = 2; i < 9 ; i++ ) {
            for(int j = 8; j < 9 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(6);
                if(i==4){
                    block.setBoost(new BoostModel(30 + block.WIDTH*i + 1*i +block.WIDTH/2, 30 + block.HEIGHT*j + 1*j + block.HEIGHT/2, this.model));
                }
                playField[i][j] = block;    
            }    
        }
        
        
        // 10th layer
        for(int i = 1; i < 10 ; i++ ) {
            for(int j = 9; j < 10 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(4);
                playField[i][j] = block;    
            }    
        }
        
        
        // 11th layer
        for(int i = 0; i < 11 ; i++ ) {
            for(int j = 10; j < 11 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(2);   
                if(i==8){
                    block.setBoost(new BoostModel(30 + block.WIDTH*i + 1*i +block.WIDTH/2, 30 + block.HEIGHT*j + 1*j + block.HEIGHT/2, this.model));
                }
                playField[i][j] = block;    
            }    
        } 
        
        return playField;
    }
    
   /**
     * First Expert level
     * 
     * @return setup of the level in BlockModels
     */
    public BlockModel[][] getExpert1() {
        BlockModel[][] playField = new BlockModel[11][11];
        
        
        // First layer
        for(int i = 0; i < 11 ; i++ ) {
            for(int j = 0; j < 1 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(5);
                playField[i][j] = block;    
            }    
        }
        
        
        // 3rd layer
        for(int i = 2; i < 9 ; i++ ) {
            for(int j = 2; j < 3 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(10);
                if(i == 7){
                    block.setBoost(new BoostModel(30 + block.WIDTH*i + 1*i +block.WIDTH/2, 30 + block.HEIGHT*j + 1*j + block.HEIGHT/2, this.model));
                }
                playField[i][j] = block;    
            }    
        }
        
        
        // 5th layer 
        for(int i = 4; i < 7 ; i++ ) {
            for(int j = 4; j < 5 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(15);
                playField[i][j] = block;    
            }    
        }
        
        
        // 7nd layer
        for(int i = 4; i < 7 ; i++ ) {
            for(int j = 6; j < 7 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(15);
                playField[i][j] = block;    
            }    
        }
        
        
        // 9th layer
        for(int i = 2; i < 9 ; i++ ) {
            for(int j = 8; j < 9 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(10);
                if(i == 2){
                    block.setBoost(new BoostModel(30 + block.WIDTH*i + 1*i +block.WIDTH/2, 30 + block.HEIGHT*j + 1*j + block.HEIGHT/2, this.model));
                }
                playField[i][j] = block;    
            }    
        }
        
        
        // 11th layer 
        for(int i = 0; i < 11 ; i++ ) {
            for(int j = 10; j < 11 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(5);
                if(i == 6){
                    block.setBoost(new BoostModel(30 + block.WIDTH*i + 1*i +block.WIDTH/2, 30 + block.HEIGHT*j + 1*j + block.HEIGHT/2, this.model));
                }
                playField[i][j] = block;    
            }    
        }
        
        
        // colum 1 and 11 
        for(int i = 0; i < 11 ; i = i + 10) {
            for(int j = 1; j < 10 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(5);
                if(i == 10 && j == 3){
                    block.setBoost(new BoostModel(30 + block.WIDTH*i + 1*i +block.WIDTH/2, 30 + block.HEIGHT*j + 1*j + block.HEIGHT/2, this.model));
                }
                playField[i][j] = block;    
            }    
        }
        
        
        // colum 3 and 9 
        for(int i = 2; i < 9 ; i = i + 6) {
            for(int j = 3; j < 8 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(10);
                playField[i][j] = block;    
            }    
        }
        
        
        // colum 5 and 7 
        for(int i = 4; i < 7 ; i = i + 2) {
            for(int j = 5; j < 6 ; j++) {
                BlockModel block = new BlockModel(0, 0);
                block.setX(30 + block.WIDTH*i + 1*i);
                block.setY(30 + block.HEIGHT*j + 1*j);
                block.setlifes(15);
                if(i == 4 && j == 5){
                    block.setBoost(new BoostModel(30 + block.WIDTH*i + 1*i +block.WIDTH/2, 30 + block.HEIGHT*j + 1*j + block.HEIGHT/2, this.model));
                }
                playField[i][j] = block;    
            }    
        }
        
        return playField;
    } 
    
}
