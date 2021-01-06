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
                map[i][j] = 1;
            }
        }
        brickWidth = 540/columns; //sets width
        brickHeight = 150/rows; //sets height
    }
    public void draw(Graphics2D graphics2D) {
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                if(map[i][j] > 0) {
                    graphics2D.setColor(Color.CYAN); //set color of bricks
                    graphics2D.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight); //creates the bricks, sets their size

                    graphics2D.setStroke(new BasicStroke(3));
                    graphics2D.setColor(Color.BLACK);
                    graphics2D.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }
    public void setBrickValue(int value, int rows, int columns) {
        map[rows][columns] = value;
    }
}
