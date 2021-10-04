import java.awt.*;
import java.util.Random;

public class PlaneRadar {
    private Radars radars;
    private int startPosX;
    private int startPosY;
    private int pictureWidth;
    private int pictureHeight;
    private static final int planeWidth = 170;
    private static final int planeHeight = 80;
    public int getMaxSpeed() {
        return maxSpeed;
    }
    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
    public float getWeight() {
        return weight;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }
    public Color getMainColor() {
        return mainColor;
    }
    public void setMainColor(Color mainColor) {
        this.mainColor = mainColor;
    }
    public Color getDopColor() {
        return dopColor;
    }
    public void setDopColor(Color dopColor) {
        this.dopColor = dopColor;
    }
    public boolean isHvRadar() {
        return hvRadar;
    }
    public void setHvRadar(boolean hvRadar) {
        this.hvRadar = hvRadar;
    }
    public boolean isHvEngine() {
        return hvEngine;
    }
    public void setHvEngine(boolean hvEngine) {
        this.hvEngine = hvEngine;
    }
    public int maxSpeed;
    public float weight;
    public Color mainColor;
    public Color dopColor;
    public boolean hvRadar;
    public boolean hvEngine;
    public void Init(int maxSpeed, float weight, Color mainColor, Color dopColor,
                     boolean hvRadar, boolean hvEngine)
    {
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
        this.dopColor = dopColor;
        this.hvRadar = hvRadar;
        this.hvEngine = hvEngine;
        radars = new Radars();
        radars.Init(planeWidth, planeHeight);
        if (hvEngine) {
            Random rnd = new Random();
            radars.setQuantity(rnd.nextInt(5) + 1);
        }
        else{
            radars.setQuantity(0);
        }
    }
    public void SetPosition(int x, int y, int width, int height)
    {
        startPosX = x;
        startPosY = y;
        pictureWidth = width;
        pictureHeight = height;
    }
    public void MoveTransport(Direction direction)
    {
        float step = maxSpeed * 100 / weight;
        switch (direction) {
            case Right:
                if (startPosX + step <= pictureWidth - planeWidth) {
                    startPosX += step;
                }
                break;
            case Left:
                if (startPosX - step >= 0) {
                    startPosX -= step;
                }
                break;
            case Up:
                if (startPosY - step >= 0) {
                    startPosY -= step;
                }
                break;
            case Down:
                if (startPosY + step <= pictureHeight - planeHeight) {
                    startPosY += step;
                }
                break;
        }
    }
    public void DrawTransport(Graphics gr){
        gr.clearRect(0, 0, 900, 500); //??
        Graphics2D g2d = (Graphics2D)gr;
        g2d.setPaint(Color.black);
        //корпус
        g2d.setColor(mainColor);
        g2d.fillRect(startPosX + planeHeight * 1 / 5, startPosY + planeHeight * 2 / 5, planeWidth - planeHeight * 2 / 5, planeHeight * 2 / 5);
        g2d.fillOval(startPosX, startPosY + planeHeight * 2 / 5, planeHeight * 2 / 5, planeHeight * 2 / 5);
        g2d.fillPolygon(new int[]{startPosX + planeWidth - planeHeight / 5, startPosX + planeWidth - planeHeight / 5, startPosX + planeWidth},
                new int[]{startPosY + planeHeight * 2 / 5, startPosY + planeHeight * 4 / 5, startPosY + planeHeight * 3 / 5}, 3);
        g2d.setColor(Color.black);
        g2d.drawPolygon(new int[]{startPosX + planeWidth - planeHeight / 5, startPosX + planeWidth - planeHeight / 5, startPosX + planeWidth},
                new int[]{startPosY + planeHeight * 2 / 5, startPosY + planeHeight * 4 / 5, startPosY + planeHeight * 3 / 5}, 3);
        g2d.drawLine(startPosX + planeWidth - planeHeight / 5, startPosY + planeHeight * 3 / 5, startPosX + planeWidth, startPosY + planeHeight * 3 / 5);
        g2d.setColor(mainColor);
        //крылья
        g2d.fillPolygon(new int[]{ startPosX + planeHeight / 5, startPosX + planeHeight / 5, startPosX + planeHeight * 3 / 5 },
                        new int[]{startPosY, startPosY + planeHeight * 2 / 5, startPosY + planeHeight * 2 / 5}, 3);
        g2d.setColor(Color.black);
        g2d.fillOval(startPosX + planeHeight / 10, startPosY + planeHeight * 7 / 20, planeHeight * 2 / 5, planeHeight / 10);
        g2d.fillOval(startPosX + planeWidth / 4, startPosY + planeHeight / 2, planeWidth / 2, planeHeight / 5);
        //шасси
        g2d.drawLine(startPosX + planeWidth / 4, startPosY + planeHeight * 4 / 5, startPosX + planeWidth / 4, startPosY + planeHeight * 19 / 20);
        g2d.drawOval(startPosX + planeWidth / 4 - planeHeight / 10, startPosY + planeHeight * 9 / 10, planeHeight / 10, planeHeight / 10);
        g2d.drawOval(startPosX + planeWidth / 4, startPosY + planeHeight * 9 / 10, planeHeight / 10, planeHeight / 10);
        g2d.drawLine(startPosX + planeWidth * 3 / 4, startPosY + planeHeight * 4 / 5, startPosX + planeWidth * 3 / 4, startPosY + planeHeight * 9 / 10);
        g2d.drawOval(startPosX + planeWidth * 3 / 4 - planeHeight / 20, startPosY + planeHeight * 9 / 10, planeHeight / 10, planeHeight / 10);
        //двиигатель
        if (hvEngine){
            g2d.setColor(dopColor);
            g2d.fillOval(startPosX + planeWidth * 6 / 16, startPosY + planeHeight * 7 / 10, planeHeight * 1 / 5, planeHeight * 1 / 5);
            g2d.fillOval(startPosX + planeWidth * 9 / 16, startPosY + planeHeight * 7 / 10, planeHeight * 1 / 5, planeHeight * 1 / 5);
            g2d.fillRect(startPosX + planeWidth * 6 / 16 + planeHeight * 1 / 10, startPosY + planeHeight * 7 / 10,
                    planeWidth * 3 / 16, planeHeight * 1 / 5);
        }
        //радар
        if (hvRadar)
        {
            radars.drawRadars(dopColor, g2d, startPosX, startPosY);
        }
    }
}
