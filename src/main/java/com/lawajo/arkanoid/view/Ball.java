/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid.view;

import com.lawajo.arkanoid.model.BallModel;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author joeyk
 */
public class Ball extends ViewObject {
    
    private Circle circle;
    private BallModel model;
    
    
    
//    /**
//     * Initializes a new ball.
//     * 
//     * @param x The x coordinate of the ball.
//     * @param y The y coordinate of the ball.
//     */
//    public Ball(int x, int y) {
//        this.x = x;
//        this.y = y;
//        
//        this.prevX = x;
//        this.prevY = y;
//        
//        this.dx = -1;
//        this.dy = -1;
//        
//        this.circle = new Circle();
//        this.circle.setRadius(Ball.RADIUS);
//        this.circle.setFill(Color.WHITE);
//        
//        update();
//        this.getChildren().add(this.circle);
//    }
    
    
    public Ball(BallModel model) {
        this.model = model;
        
        this.circle = new Circle(this.model.RADIUS);
        this.circle.setFill(Color.GRAY);
        
        this.getChildren().add(this.circle);
        update();
    }
    
    
    // Setters
    
    /**
     * Updates the current ball object with the newest parameters.
     */
    @Override
    public void update() {
        this.circle.setCenterX(model.getX());
        this.circle.setCenterY(model.getY());
        this.circle.setRadius(model.RADIUS);
        this.circle.setFill(Color.GRAY);
    }

    
    
//    /**
//     * Moves the ball to the next location.
//     * 
//     * @deprecated Ik denk dat het berekenen van welke kant de bal op moet als hij een blok raakt beter in de controller gebeurt.
//     *             De code werkt wel 100% dus in principe kan je dit zo overnemen. Dit werkt ook direct voor de slider aangezien
//     *             die block extend. Gebruik het voor nu misschien even zo en als we nog eens bellen hebben we het er wel over.
//     * 
//     * @param block The block the ball hits.
//     */
//    @Deprecated
//    public void move(BlockModel block) {
//        if (block == null) {
//            if (this.x == Ball.RADIUS || this.x == 500 - Ball.RADIUS) {
//                hitVertical();
//            } else if (this.y == Ball.RADIUS) {
//                hitHorizontal();
//            } else {
//                addX();
//                addY();
//            }
//        } else {
//            int blockX = block.getX();
//            int blockY = block.getY();
//            
//            if (this.x + Ball.RADIUS == blockX) {
//                hitVertical();
//            } else if (this.x - Ball.RADIUS == blockX + Block.WIDTH) {
//                hitVertical();
//            } else {
//                if (y + Ball.RADIUS == blockY) {
//                    hitHorizontal();
//                } else if (y - Ball.RADIUS == blockY + Block.HEIGHT) {
//                    hitHorizontal();
//                } else {
//                    move();
//                }
//            }
//        }
//        
//        update();
//    }
    
    
//    /**
//     * Moves the ball to the next location.
//     */
//    public void move() {
//        move(null);
//    }
    
    
}
