public class PlayerChar {

    private int xPos, yPos;
    private int playerHeight = 10, playerWidth = 10;

    public PlayerChar(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void move(int move_x, int move_y) {
        xPos += move_x * playerWidth;
        yPos += move_y * playerHeight;
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }
}
