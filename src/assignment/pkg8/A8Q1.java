package assignment.pkg8;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import static javafx.scene.paint.Color.color;

/**
 *
 * @author nate
 */
public class A8Q1 extends JComponent {

    // Height and Width of our game
    static final int WIDTH = 1000;
    static final int HEIGHT = 600;

    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;

    // GAME VARIABLES WOULD GO HERE
    Color b = new Color(87, 71, 23);
    Color skin = new Color(255, 222, 173);
    int yspeed = 2;
    int irisy = 248;
    int eyey = 240;
    int pupily = 252;
    int lidy = 230;
    int lidl = 20;
    int lidy2 = 220;
    int mouthl = 200;
    int mouthy = 252;
    int tungy = 427;
    int lidy3 = 287;
    int speed = 2;
    int i = 0;
    BufferedImage bob = imageHelper.loadImage("download.jpg");

    // GAME VARIABLES END HERE   
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE
        //imported picture
        g.drawImage(bob, 650, 200, this);
        //stand for the painting
        g.setColor(b);
        g.fillRect(620, 100, 30, 600);
        g.fillRect(900, 100, 30, 600);
        

        //ears
        g.setColor(skin);
        g.fillOval(92, 200, 50, 100);

        g.fillOval(500, 200, 50, 100);

        //hair
        g.setColor(b);
        g.fillOval(70, 40, 500, 300);

        //head
        g.setColor(skin);
        g.fillOval(120, 120, 400, 400);

        //drawing the eyes
        g.setColor(Color.white);
        g.fillOval(370, eyey, 50, 35);
        g.fillOval(220, eyey, 50, 35);

        //iris
        g.setColor(Color.blue);
        g.fillOval(382, irisy, 25, 20);
        g.fillOval(232, irisy, 25, 20);

        //puiples
        g.setColor(Color.black);
        g.fillOval(385, pupily, 15, 10);
        g.fillOval(235, pupily, 15, 10);

        //eyelid
        g.setColor(skin);
        g.fillRect(370, lidy2, 50, lidl);

        g.fillRect(370, lidy3, 50, 20);

        //beard 
        g.setColor(b);
        g.fillArc(120, 120, 400, 400, 180, 180);

        //mouth
        g.setColor(Color.black);
        g.fillArc(205, mouthy, 170, mouthl, 180, 180);
        g.setColor(Color.red);
        g.fillArc(260, tungy, 50, 50, 10, 160);

        //eybrows
        g.setColor(b);
        int[] xPoints = {220, 200, 300};
        int[] yPoints = {200, 230, 220};
        g.fillPolygon(xPoints, yPoints, 3);
        //second
        int[] xPoints3 = {350, 430, 450};
        int[] yPoints3 = {200, 230, 220};
        g.fillPolygon(xPoints3, yPoints3, 3);

        //nose
        g.setColor(Color.black);
        int[] xPoints2 = {270, 320, 340};
        int[] yPoints2 = {310, 230, 310};
        g.drawPolygon(xPoints2, yPoints2, 3);
        // GAME DRAWING ENDS HERE
    }

    // This method is used to do any pre-setup you might need to do
    // This is run before the game loop begins!
    public void preSetup() {
        // Any of your pre setup before the loop starts should go here

    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;

        preSetup();

        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            //stops mouth movement tho wink
            if (i > 5 && i < 100) {
                speed = 0;
                lidl += yspeed;
                lidy3 -= yspeed;
                if (lidl == 0) {
                    yspeed = 1;
                }
                if (lidl == 50) {
                    yspeed = -1;
                }
                i++;
            }
            tungy -= speed;
            mouthl -= speed * 2;
            mouthy += speed ;
            if (tungy < 417) {
                speed = -2;
            }
            if (tungy > 440) {
                speed = 1;
                i++;
            }
            if (i > 140) {
                i = 0;
            }
            // GAME LOGIC ENDS HERE 
            // update the drawing (calls paintComponent)
            repaint();

            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            try {
                if (deltaTime > desiredTime) {
                    //took too much time, don't wait
                    Thread.sleep(1);
                } else {
                    // sleep to make up the extra time
                    Thread.sleep(desiredTime - deltaTime);
                }
            } catch (Exception e) {
            };
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates a windows to show my game
        JFrame frame = new JFrame("Bob Ross");

        // creates an instance of my game
        A8Q1 game = new A8Q1();
        // sets the size of my game
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // adds the game to the window
        frame.add(game);

        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);

        // add listeners for keyboard and mouse
        frame.addKeyListener(new Keyboard());
        game.addMouseListener(new Mouse());

        // starts the game loop
        game.run();
    }

    // Used to implement any of the Mouse Actions
    private static class Mouse extends MouseAdapter {

        // if a mouse button has been pressed down
        @Override
        public void mousePressed(MouseEvent e) {

        }

        // if a mouse button has been released
        @Override
        public void mouseReleased(MouseEvent e) {

        }

        // if the mouse has moved positions
        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

    // Used to implements any of the Keyboard Actions
    private static class Keyboard extends KeyAdapter {

        // if a key has been pressed down
        @Override
        public void keyPressed(KeyEvent e) {

        }

        // if a key has been released
        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
