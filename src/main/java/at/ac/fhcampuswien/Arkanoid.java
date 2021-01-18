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
    private int totalBricks = 36; //number of bricks, gets added later for auto pause function when no bricks are left

    private Timer speed; //variable for setting speed
    private int speedSet = 1; //set speed

    private int panelDirX = 565; //x position of platform at start

    private int ballPosX = 640; //x position of ball at start
    private int ballPosY = 600; //y position of ball at start
    private int ballDirX = -2; //ball direction, sets the number of pixels the ball moves in the x direction every tick (repaint)
    private int ballDirY = -3; //ball direction, sets the number of pixels the ball moves in the y direction every tick (repaint)
    private Bricks bricks; //object for class Bricks

    public Arkanoid() {
        bricks = new Bricks(4,9); //sets the number of rows and columns
        addKeyListener(this); //adds key listener
        setFocusable(true); //needs to be set true so it can listen to key input
        setFocusTraversalKeysEnabled(false); //ignores input from tab key or combinations
        speed = new Timer(speedSet, this);
        speed.start();
    }

    public void paint (Graphics graphics) {
        //background
        graphics.setColor(Color.lightGray); //sets background color
        graphics.fillRect(1, 1, 1272, 1016); //creates rectangle for background

        //drawing "bricks" (object class Bricks)
        bricks.draw((Graphics2D)graphics);

        //borders
        graphics.setColor(Color.black); //setting color
        graphics.fillRect(0, 0, 3, 1016); //setting border for color
        graphics.fillRect(0, 0, 1262, 3); //setting border for color
        graphics.fillRect(1261, 0, 3, 1016); //setting border for color

        //score
        if (play) {
            graphics.setColor(Color.white);
            graphics.setFont(new Font("sanserif",Font.BOLD,25));
            graphics.drawString("Score: " + score,1100,30);
        }

        //game won
        if (totalBricks <= 0){
            play= false;
            ballDirX = 0;
            ballDirY = 0;
            graphics.setColor(Color.black);
            graphics.setFont(new Font("sanserif",Font.BOLD,30));
            graphics.drawString("* * *G E W O N N E N * * *",190,300);

            graphics.setFont(new Font("sanserif",Font.BOLD,20));
            graphics.drawString("Neustart mit Enter",230,350);
        }
        if (ballPosY > 1040){
            play= false;
            ballDirX = 0;
            ballDirY = 0;
            graphics.setColor(Color.black);
            graphics.setFont(new Font("sanserif",Font.BOLD,30));
            graphics.drawString("Game Over, Punkte: " +score,490,550);

            graphics.setFont(new Font("sanserif",Font.BOLD,20));
            graphics.drawString("Neustart mit Enter",560,600);
        }

        //panel
        graphics.setColor(Color.BLACK); //color of panel
        graphics.fillRect(panelDirX, 950, 150, 10); //creates panel and defines position of panel plus size of panel

        //ball
        graphics.setColor(Color.black); //color of panel
        graphics.fillOval(ballPosX, ballPosY, 30, 30); //creates ball and defines position of ball plus size of ball
        graphics.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //speed.start(); //starts timer
        if(play) { //checks if "play" is true/player started the game
            if(new Rectangle(ballPosX, ballPosY, 20,20).intersects(new Rectangle(panelDirX + 75, 950,75,10))) { //creates 20x20 rectangle around ball, sets the border for the ball on the panel and always directs it to the right side, right half of panel
                if(ballDirX < 0) {
                    ballDirY = -ballDirY; //changes direction on impact on panel
                    ballDirX = -ballDirX;//changes direction on impact on panel
                }else ballDirY = -ballDirY; //changes direction on impact on panel
            } else if(new Rectangle(ballPosX, ballPosY, 20,20).intersects(new Rectangle(panelDirX, 950,75,10))) { //creates 20x20 rectangle around ball, sets the border for the ball on the panel and always directs it to the left side, left half of panel
                if(ballDirX < 0) {
                    ballDirY = -ballDirY; //changes direction on impact on panel
                }else {ballDirY = -ballDirY; //changes direction on impact on panel
                       ballDirX = -ballDirX;}//changes direction on impact on panel
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
            if(ballPosX >= 1235) { //checks if ball is inside windows in the x axis, right side , if x = 1875 then the ball changes direction
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
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            if (!play){
                play = true;
                ballPosX = 640;
                ballPosY = 600;
                ballDirX = -2;
                ballDirY = -3;
                panelDirX = 565;
                score = 0;
                totalBricks = 36;
                bricks = new Bricks(4,9);

                repaint();
            }
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
