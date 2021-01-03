/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawajo.arkanoid.view;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author joeyk
 */
public class Ball extends Region {
    
    public final static int RADIUS = 10;
    
    private Circle circle;
    private int x;
    private int y;
    
    private int prevX;
    private int prevY;
    
    private int dx;
    private int dy;
    
    
    /**
     * Initializes a new ball.
     * 
     * @param x The x coordinate of the ball.
     * @param y The y coordinate of the ball.
     */
    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        
        this.prevX = x;
        this.prevY = y;
        
        this.dx = -1;
        this.dy = -1;
        
        this.circle = new Circle();
        this.circle.setRadius(Ball.RADIUS);
        this.circle.setFill(Color.WHITE);
        
        update();
        this.getChildren().add(this.circle);
    }
    
    
    // Setters
    
    /**
     * Updates the current ball to the newest coordinates.
     */
    private void update() {
        this.circle.setLayoutX(this.x);
        this.circle.setLayoutY(this.y);
    }
    
    
    private void addY() {
        this.prevY = this.y;
        this.y += this.dy;
    }
    
    
    private void addX() {
        this.prevX = this.x;
        this.x += this.dx;
    }
    
    
    public void hitVertical() {
        int tempX = this.x;
        this.x = this.prevX;
        this.prevX = tempX;
        this.dx *= -1;
        addY();
    }
    
    
    public void hitHorizontal() {
        int tempY = this.y;
        this.y = this.prevY;
        this.prevY = tempY;
        this.dy *= -1;
        addX();
    }
    
    
    /**
     * Moves the ball to the next location.
     * 
     * @deprecated Ik denk dat het berekenen van welke kant de bal op moet als hij een blok raakt beter in de controller gebeurt.
     *             De code werkt wel 100% dus in principe kan je dit zo overnemen. Dit werkt ook direct voor de slider aangezien
     *             die block extend. Gebruik het voor nu misschien even zo en als we nog eens bellen hebben we het er wel over.
     * 
     * @param block The block the ball hits.
     */
    @Deprecated
    public void move(Block block) {
        if (block == null) {
            if (this.x == Ball.RADIUS || this.x == 500 - Ball.RADIUS) {
                hitVertical();
            } else if (this.y == Ball.RADIUS) {
                hitHorizontal();
            } else {
                addX();
                addY();
            }
        } else {
            int blockX = block.getX();
            int blockY = block.getY();
            
            if (this.x + Ball.RADIUS == blockX) {
                hitVertical();
            } else if (this.x - Ball.RADIUS == blockX + Block.WIDTH) {
                hitVertical();
            } else {
                if (y + Ball.RADIUS == blockY) {
                    hitHorizontal();
                } else if (y - Ball.RADIUS == blockY + Block.HEIGHT) {
                    hitHorizontal();
                } else {
                    move();
                }
            }
        }
        
        update();
    }
    
    
    /**
     * Moves the ball to the next location.
     */
    public void move() {
        move(null);
    }
    
    
    /**
     * Sets the position of the ball.
     * 
     * @param x The x coordinate of the center of the ball.
     * @param y The y coordinate of the center of the ball.
     */
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    // Getters
    
    
    
    /**
     * Gets the x coordinate of the center of the ball.
     * 
     * @return The x coordinate.
     */
    public int getX() {
        return this.x;
    }
    
    
    /**
     * Gets the y coordinate of the center of the ball.
     * 
     * @return The y coordinate.
     */
    public int getY() {
        return this.y;
    }
}
