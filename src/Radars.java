import java.awt.*;

public class Radars {
    private RadarQuantity quantity;
    private static int planeWidth;
    private static int planeHeight;
    public void setQuantity(int quantity) {
        this.quantity = RadarQuantity.values()[quantity - (quantity - 1) / 3 * 3];
    }
    public void drawRadars(Color dopColor, Graphics2D g2d, int startPosX, int startPosY){
            for (int i = 0; i < quantity.ordinal(); ++i){
                g2d.setColor(dopColor);
                g2d.drawLine(startPosX + planeWidth / 2 + i * planeHeight / 5, startPosY + planeHeight * 1 / 5,
                             startPosX + planeWidth / 2 + i * planeHeight / 5, startPosY + planeHeight * 2 / 5);
                g2d.fillArc(startPosX + planeWidth / 2 - planeHeight / 10 + i * planeHeight / 5, startPosY + planeHeight * 1 / 5, planeHeight / 5, planeHeight / 10, 0, 180);
            }
    }
    public void Init(int planeWidth, int planeHeight){
        this.planeWidth = planeWidth;
        this.planeHeight = planeHeight;
    }
}
