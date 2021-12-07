import java.awt.*;

public abstract class Vehicle implements ITransport{
    protected int startPosX;
    protected int startPosY;
    protected int pictureWidth;
    protected int pictureHeight;

    public int getMaxSpeed() {
        return maxSpeed;
    }
    protected void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
    public float getWeight() {
        return weight;
    }
    protected void setWeight(int weight) {
        this.weight = weight;
    }
    public Color getMainColor() {
        return mainColor;
    }
    protected void setMainColor(Color mainColor) {
        this.mainColor = mainColor;
    }
    public int maxSpeed;
    public int weight;
    public Color mainColor;
    public void SetPosition(int x, int y, int width, int height) {
        startPosX = x;
        startPosY = y;
        pictureWidth = width;
        pictureHeight = height;
    }
    public abstract void MoveTransport(Direction direction);
    public abstract void DrawTransport(Graphics gr);
}
