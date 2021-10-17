import java.awt.*;

public interface IRadars {
    public void setQuantity(int quantity);
    public void drawRadars(Color dopColor, Graphics gr, int startPosX, int startPosY);
    public void Init(int planeWidth, int planeHeight);
}
