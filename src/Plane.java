import java.awt.*;

public class Plane extends Vehicle{
    protected int planeWidth = 170;
    protected int planeHeight = 80;
    public Plane(int maxSpeed, float weight, Color mainColor) {
        super.maxSpeed = maxSpeed;
        super.weight = weight;
        super.mainColor = mainColor;
    }
    protected Plane(int maxSpeed, float weight, Color mainColor, int planeWidth, int planeHeight) {
        super.maxSpeed = maxSpeed;
        super.weight = weight;
        super.mainColor = mainColor;
        this.planeWidth = planeWidth;
        this.planeHeight = planeHeight;
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
    }
}
