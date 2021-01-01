package at.ac.fhcampuswien;

import javafx.event.EventHandler;

import java.awt.*;
import java.awt.event.KeyEvent;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle; // Import für die autom. Skalierung des Paddle

/*Contains data common to all Objects active in Gameplay*/
/*Coordinates, Bounding Box, Active state, Display Representation*/
/*function: intersects with other gameobject*/
public abstract class GameObject {

    //Dummy Paddle
    public Rectangle Paddle (){
        Rectangle paddle = new Rectangle(500, 100, Color.BLUE);

        return paddle;
    }

}
