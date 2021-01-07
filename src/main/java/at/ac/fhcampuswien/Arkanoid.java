package at.ac.fhcampuswien;

/**Imports**/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

/****/

public class Arkanoid extends JPanel implements KeyListener, ActionListener {

    private boolean play = false; //game starts paused

    private int score = 0; //no score at start of game, gets added later
    private int totalBricks = 50; //number of bricks, gets added later for auto pause function when no bricks are left

    private Timer speed; //variable for setting speed
    private int speedSet = 1; //set speed

    private int panelDirX = 920; //x position of platform at start

    private int ballPosX = 960; //x position of ball at start
    private int ballPosY = 750; //y position of ball at start
    private int ballDirX = -2; //ball direction, sets the number of pixels the ball moves in the x direction every tick (repaint)
    private int ballDirY = -3; //ball direction, sets the number of pixels the ball moves in the y direction every tick (repaint)
    private Bricks bricks; //object for class Bricks

    public Arkanoid() {
        bricks = new Bricks(5,10); //sets the number of rows and columns
        addKeyListener(this); //adds key listener
        setFocusable(true); //needs to be set true so it can listen to key input
        setFocusTraversalKeysEnabled(false); //ignores input from tab key or combinations
        speed = new Timer(speedSet, this);
        speed.start();
    }

    public void paint (Graphics graphics) {
        //background
        graphics.setColor(Color.lightGray); //sets background color
        graphics.fillRect(1, 1, 1912, 1072); //creates rectangle for background

        //drawing "bricks" (object class Bricks)
        bricks.draw((Graphics2D)graphics);

        //borders
        graphics.setColor(Color.black); //setting color
        graphics.fillRect(0, 0, 3, 1072); //setting border for color
        graphics.fillRect(0, 0, 1902, 3); //setting border for color
        graphics.fillRect(1902, 0, 3, 1072); //setting border for color

        //panel
        graphics.setColor(Color.BLACK); //color of panel
        graphics.fillRect(panelDirX, 990, 150, 10); //creates panel and defines position of panel plus size of panel

        //ball
        graphics.setColor(Color.black); //color of panel
        graphics.fillOval(ballPosX, ballPosY, 30, 30); //creates ball and defines position of ball plus size of ball
        graphics.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //speed.start(); //starts timer
        if(play) { //checks if "play" is true/player started the game
            if(new Rectangle(ballPosX, ballPosY, 20,20).intersects(new Rectangle(panelDirX+75, 990,75,10))) { //creates 20x20 rectangle around ball, sets the border for the ball on the panel and reflects it at the negative incoming angle, right half of panel
                ballDirY = - ballDirY; //changes direction on impact on panel
            } else if(new Rectangle(ballPosX, ballPosY, 20,20).intersects(new Rectangle(panelDirX, 990,75,10))) { //creates 20x20 rectangle around ball, sets the border for the ball on the panel and reflects it at the negative incoming angle, left half of panel
                ballDirY = -ballDirY; //changes direction on impact on panel
                ballDirX = -ballDirX;//changes direction on impact on panel
            }

            for(int i = 0; i < bricks.map.length; i++) { //sets border for bricks
                for(int j = 0; j < bricks.map[0].length; j++) {
                    if(bricks.map[i][j] > 0) { //checks if brick exists
                        int brickX = j * bricks.brickWidth + 80;
                        int brickY = i * bricks.brickHeight + 50;
                        int brickWidth = bricks.brickWidth;
                        int brickHeight = bricks.brickHeight;

                        Rectangle rectangle = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20,20);
                        Rectangle brickRectangle = rectangle;

                        if(ballRect.intersects(brickRectangle)) {
                            bricks.setBrickValue(0,i,j); //value of intersected brick gets changed to 0 so it won't be displayed anymore
                            totalBricks --; //for later pause function --> number of total bricks gets lowered by 1
                            score += 5; //for later score function --> score increases by 5

                            if(ballPosX + 19 <= brickRectangle.x || ballPosX + 1 >= brickRectangle.x + brickRectangle.width) { //checks ball collision with bricks
                                ballDirX = - ballDirX; //changes direction on impact in negative x direction
                            } else ballDirY = - ballDirY; //changes direction on impact in negative y direction
                            break;
                        }
                    }
                }
            }

            ballPosX += ballDirX; //movement of ball in x direction
            ballPosY += ballDirY; //movement of ball in y direction
            if(ballPosX <= 0) { //checks if ball is inside windows in the x axis, left side , if x = 0 then the ball changes direction
                ballDirX = -ballDirX; //sets the new direction as opposite as old direction
            }
            if(ballPosY <= 0) { //checks if ball is inside windows in the y axis, top , if x = 0 then the ball changes direction
                ballDirY = -ballDirY; //sets the new direction as opposite as old direction
            }
            if(ballPosX >= 1875) { //checks if ball is inside windows in the x axis, right side , if x = 1875 then the ball changes direction
                ballDirX = -ballDirX; //sets the new direction as opposite as old direction
            }
        }
        repaint(); //repaints/redraws everything in the method paint
    }

    @Override
    public void keyTyped(KeyEvent e) { //not used

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) { //if right arrow key is pressed then...
            if (panelDirX >= 1770) { //checks if panel wants to go outside border
                panelDirX = 1770; //fixes panel at border
            } else moveRight(); //if not at border input from key listener gets passed
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) { //if left arrow key is pressed then...
            if (panelDirX <= 1) { //checks if panel wants to go outside border
                panelDirX = 1; //fixes panel at border
            } else moveLeft(); //if not at border input from key listener gets passed
        }
    }

    public void moveRight() {
        play = true; //starts the paused game when right arrow is pressed
        panelDirX += 20; //moves panel 20 pixels in x direction
    }
    public void moveLeft() {
        play = true; //starts the paused game when left arrow is pressed
        panelDirX -= 20; //moves panel 20 pixels in x direction
    }

    @Override
    public void keyReleased(KeyEvent e) { //not used

    }
}
