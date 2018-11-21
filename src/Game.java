import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements KeyListener {

    private PlayerChar player;

    public Game(){
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


        //sets frame to visible
        frame.setVisible(true);

        //creates playerCharacter
        player = new PlayerChar(10, 10);
    }

    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        //paint player character, for now as a rectangle
        g.drawRect(player.getX(), player.getY(), 10, 10);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()){
            case KeyEvent.VK_W:
                player.move(0, -1);
                System.out.println("moving up");
                break;
            case KeyEvent.VK_D:
                player.move(1, 0);
                System.out.println("moving right");
                break;
            case KeyEvent.VK_A:
                player.move(-1, 0);
                System.out.println("moving left");
                break;
        }

        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
