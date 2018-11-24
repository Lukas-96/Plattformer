import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements KeyListener, ActionListener {

    private PlayerChar player;

    //represent playing field as 2D-Array to allow less ressource intensive calculations
    private float[][] playingField;


    //the following variables all relate to player movement and may need to be set up in a seperate class at some point
    private float moveX, moveY, velocityX, velocityY, gravity = 0.01f, timeElapsed = 0;

    public Game() {
        JFrame frame = new JFrame("This is my platform game");

        //sets frame to fullscreen
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        //TODO:set this to true
        // frame.setUndecorated(true);

        //close Process when closing game
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //adds JPanel in which we draw the game to frame
        frame.add(this);

        //addKeyListener
        frame.addKeyListener(this);

        //initialize array representing playing field
        int width = frame.getWidth() / 10;
        int height = frame.getHeight() / 10;

        playingField = new float[width][height];

        for (float[] rows : playingField) {
            for (float j : rows) {
                j = 0;
            }
        }

        //TODO: remove this at some point
        //print baseline for testing
        int baseline = height / 2;
        for (int i = 0; i < playingField.length; i++) {
            playingField[i][baseline] = 1;
        }

        //sets frame to visible
        frame.setVisible(true);

        //creates playerCharacter
        player = new PlayerChar(10, 10);

        //initializes Swing Timer for actions and refresh
        Timer gameTimer = new Timer(1, this);
        gameTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        //paint player character, for now as a rectangle
        g.drawRect((int) player.getX(), (int) player.getY(), 10, 10);

        //draw platforms
        for (int i = 0; i < playingField.length; i++) {
            for (int j = 0; j < playingField[i].length; j++) {
                if (playingField[i][j] == 1) {
                    int xPos = i * 10, yPos = j * 10 + 10;
                    g.drawLine(xPos, yPos, xPos + 10, yPos);
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                velocityY = -12.0f;
                break;
            case KeyEvent.VK_D:
                moveX = 1;
                break;
            case KeyEvent.VK_A:
                moveX = -1;
                break;
            case KeyEvent.VK_S:
                moveY = 1;
                break;
        }

        //if (move_y > 0 && playingField[xPos][yPos] == 1) {
        //    move_y = 0;
        //}
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //int xPos = (int) player.getX() / 10, yPos = (int) player.getY() / 10;

        int xPos = (int) (player.getX() / 10), yPos = (int) (player.getY() / 10);

        if(playingField[xPos][yPos] != 1){
            timeElapsed += 0.001f;
            moveY += velocityY * timeElapsed;
            velocityY += gravity * timeElapsed;
        } else{
            timeElapsed = 0;
            velocityY = 0;
            moveY = 0;
        }

        player.move(moveX, moveY);
        moveX = 0;
        moveY = 0;

        repaint();
    }
}
