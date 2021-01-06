package at.ac.fhcampuswien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

public class Arkanoid extends JPanel implements KeyListener, ActionListener {

    private boolean play = false; //game starts paused
    private int score = 0; //no score at start of game

    private int totalBricks = 21; //number of bricks

    private Timer speed; //variable for setting speed
    private int speedSet = 8; //set speed

    private int panelDirX = 310; //x position of platform at start

    private int ballDirX = 120; //x position of ball at start
    private int ballDirY = 350; //y position of ball at start
    private int ballDirXSub = -1; //ball direction
    private int ballDirYSub = -2; //ball direction

    private Bricks bricks;

    public Arkanoid() {
        bricks = new Bricks(3,7); //sets number of bricks
        addKeyListener(this); //adds key listener
        setFocusable(true); //needs to be set true so it can listen to key input
        setFocusTraversalKeysEnabled(false); //ignores input from tab key or combinations
        speed = new Timer(speedSet, this);
        speed.start();
    }

    public void paint (Graphics graphics) {
        //background
        graphics.setColor(Color.BLACK); //sets background color
        graphics.fillRect(1, 1, 692, 592); //creates rectangle

        //drawing bricks
        bricks.draw((Graphics2D)graphics);

        //borders
        graphics.setColor(Color.yellow); //setting color
        graphics.fillRect(0, 0, 3, 592); //setting border
        graphics.fillRect(0, 0, 692, 3); //setting border
        graphics.fillRect(692, 0, 3, 592); //setting border

        //panel
        graphics.setColor(Color.CYAN); //color of panel
        graphics.fillRect(panelDirX, 550, 100, 8); //creates panel and defines position of panel plus size of panel

        //ball
        graphics.setColor(Color.MAGENTA); //color of panel
        graphics.fillOval(ballDirX, ballDirY, 20, 20); //creates ball and defines position of ball plus size of ball
        graphics.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        speed.start(); //starts timer
        if(play) { //checks if player startet the game
            if(new Rectangle(ballDirX, ballDirY, 20,20).intersects(new Rectangle(panelDirX, 550,100,8))) { //sets the border for the ball on the panel and reflects it at the negative incoming angle
                ballDirYSub = - ballDirYSub;
            }

            for(int i = 0; i < bricks.map.length; i++) { //sets border for bricks
                for(int j = 0; j < bricks.map[0].length; j++) {
                    if(bricks.map[i][j] > 0) {
                        int brickX = j * bricks.brickWidth + 80;
                        int brickY = i * bricks.brickHeight + 50;
                        int brickWidth = bricks.brickWidth;
                        int brickHeight = bricks.brickHeight;

                        Rectangle rectangle = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballDirX, ballDirY, 20,20);
                        Rectangle brickRectangle = rectangle;

                        if(ballRect.intersects(brickRectangle)) {
                            bricks.setBrickValue(0,i,j);
                            totalBricks --;
                            score += 5;

                            if(ballDirX + 19 <= brickRectangle.x || ballDirX + 1 >= brickRectangle.x + brickRectangle.width) {
                                ballDirXSub = - ballDirXSub;
                            } else ballDirYSub = - ballDirYSub;
                            break;
                        }
                    }
                }
            }

            ballDirX += ballDirXSub;
            ballDirY += ballDirYSub;
            if(ballDirX < 0) {
                ballDirXSub = -ballDirXSub;
            }
            if(ballDirY < 0) {
                ballDirYSub = -ballDirYSub;
            }
            if(ballDirX > 670) {
                ballDirXSub = -ballDirXSub;
            }
        }
        repaint(); //repaints/redraws everything in the method paint
    }

    @Override
    public void keyTyped(KeyEvent e) { //not needed

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) { //if right arrow key is pressed then...
            if (panelDirX >= 600) { //checks if panel wants to go outside border
                panelDirX = 600; //fixes panel at border
            } else moveRight(); //if not at border input from key listener gets passed
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) { //if left arrow key is pressed then...
            if (panelDirX < 10) { //checks if panel wants to go outside border
                panelDirX = 10; //fixes panel at border
            } else moveLeft(); //if not at border input from key listener gets passed
        }
    }

    public void moveRight() {
        play = true;
        panelDirX += 10; //moves panel 20 pixels in x direction
    }
    public void moveLeft() {
        play = true;
        panelDirX -= 10; //moves panel 20 pixels in y direction
    }

    @Override
    public void keyReleased(KeyEvent e) { //not needed

    }
}
