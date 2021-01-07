package at.ac.fhcampuswien;

import java.awt.*;

public class Bricks {
    public int map [][];
    public int brickWidth; //width of bricks
    public int brickHeight; //height of bricks
    public Bricks (int rows, int columns) {
        map = new int[rows][columns];
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                map[i][j] = 1; //makes sure only bricks with value of 1 get displayed
            }
        }
        brickWidth = 1750/columns; //sets width of bricks
        brickHeight = 500/rows; //sets height of bricks
    }
    public void draw(Graphics2D graphics2D) {
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                if(map[i][j] > 0) { //checks if brick should be displayed
                    graphics2D.setColor(Color.darkGray); //set color of bricks
                    graphics2D.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight); //creates a big rectangle

                    graphics2D.setStroke(new BasicStroke(3)); //creates grid
                    graphics2D.setColor(Color.lightGray); //manually sets background the same color as main background
                    graphics2D.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight); //draws a grid over the rectangle to create the bricks
                }
            }
        }
    }
    public void setBrickValue(int value, int rows, int columns) {
        map[rows][columns] = value; //sets number of bricks
    }
}
