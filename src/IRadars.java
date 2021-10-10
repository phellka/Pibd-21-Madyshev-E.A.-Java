import java.awt.*;

public interface IRadars {
    public void setQuantity(int quantity);
    public void drawRadars(Color dopColor, Graphics2D g2d, int startPosX, int startPosY);
    public void Init(int planeWidth, int planeHeight);
}
