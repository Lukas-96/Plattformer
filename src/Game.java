import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements KeyListener {

    private PlayerChar player;

    //represent playing field as 2D-Array to allow less ressource intensive calculations
    private int[][] playingField;

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

        playingField = new int[width][height];

        for (int[] rows : playingField) {
            for (int j : rows) {
                j = 0;
            }
        }

        //TODO: remove this at some point
        //print baseline for testing
        int baseline = height / 2;
        for(int i = 0; i < playingField.length; i++){
            playingField[i][baseline] = 1;
        }

        //sets frame to visible
        frame.setVisible(true);

        //creates playerCharacter
        player = new PlayerChar(10, 10);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        //paint player character, for now as a rectangle
        g.drawRect(player.getX(), player.getY(), 10, 10);

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

        int move_x = 0, move_y = 0;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                move_y = -1;
                break;
            case KeyEvent.VK_D:
                move_x = 1;
                break;
            case KeyEvent.VK_A:
                move_x = -1;
                break;
            case KeyEvent.VK_S:
                move_y = 1;
                break;
        }
        int xPos = player.getX() / 10, yPos = player.getY() / 10;
        if(move_y > 0 && playingField[xPos][yPos] == 1){
            move_y = 0;
        }

        player.move(move_x, move_y);
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
