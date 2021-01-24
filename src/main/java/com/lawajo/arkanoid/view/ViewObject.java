
package com.lawajo.arkanoid.view;

import javafx.scene.layout.Region;

/**
 *@author JoeyKoster
 */
public abstract class ViewObject extends Region {
    
    public abstract void update();
    public abstract Object getModel();
    
}
