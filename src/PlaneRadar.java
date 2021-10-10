import java.awt.*;
import java.util.Random;

public class PlaneRadar extends Plane {
    private IRadars radars;
    public Color getDopColor() {
        return dopColor;
    }
    public void setDopColor(Color dopColor) {
        this.dopColor = dopColor;
    }
    public boolean getHvRadar() {
        return hvRadar;
    }
    public void setHvRadar(boolean hvRadar) {
        this.hvRadar = hvRadar;
    }
    public boolean getHvEngine() {
        return hvEngine;
    }
    public void setHvEngine(boolean hvEngine) {
        this.hvEngine = hvEngine;
    }
    public Color dopColor;
    public boolean hvRadar;
    public boolean hvEngine;
    protected PlaneRadar(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean hvRadar, boolean hvEngine){
        super(maxSpeed, weight, mainColor);
        this.dopColor = dopColor;
        this.hvEngine = hvEngine;
        this.hvRadar = hvRadar;
        Random rnd = new Random();
        switch(rnd.nextInt(3)){
            case 0:
                radars = new RadarOne();
                break;
            case 1:
                radars = new RadarTwo();
                break;
            case 2:
                radars = new RadarThree();
                break;
        }
        radars.Init(planeWidth, planeHeight);
        if (hvEngine) {
            radars.setQuantity(rnd.nextInt(5) + 1);
        }
        else{
            radars.setQuantity(0);
        }
    }
    @Override
    public void DrawTransport(Graphics gr){
        gr.clearRect(0, 0, 900, 500); //??
        Graphics2D g2d = (Graphics2D)gr;
        g2d.setPaint(Color.black);
        super.DrawTransport(gr);
        //двиигатель
        if (hvEngine){
            g2d.setColor(dopColor);
            g2d.fillOval(startPosX + planeWidth * 6 / 16, startPosY + planeHeight * 7 / 10, planeHeight/ 5, planeHeight / 5);
            g2d.fillOval(startPosX + planeWidth * 9 / 16, startPosY + planeHeight * 7 / 10, planeHeight / 5, planeHeight / 5);
            g2d.fillRect(startPosX + planeWidth * 6 / 16 + planeHeight / 10, startPosY + planeHeight * 7 / 10,
                    planeWidth * 3 / 16, planeHeight / 5);
        }
        //радар
        if (hvRadar)
        {
            radars.drawRadars(dopColor, g2d, startPosX, startPosY);
        }
    }
}
