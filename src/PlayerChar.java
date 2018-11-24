public class PlayerChar {

    private float xPos, yPos;
    private int playerHeight = 10, playerWidth = 10;

    public PlayerChar(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void move(float move_x, float move_y) {
        xPos += move_x;
        yPos += move_y;
    }

    public float getX() {
        return xPos;
    }

    public float getY() {
        return yPos;
    }
}
