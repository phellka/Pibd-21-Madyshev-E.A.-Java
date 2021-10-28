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
        if (hvRadar) {
            radars.setQuantity(rnd.nextInt(5) + 1);
        }
        else{
            radars.setQuantity(0);
        }
    }
    @Override
    public void DrawTransport(Graphics gr){
        super.DrawTransport(gr);
        //двиигатель
        if (hvEngine){
            gr.setColor(dopColor);
            gr.fillOval(startPosX + planeWidth * 6 / 16, startPosY + planeHeight * 7 / 10, planeHeight * 1 / 5, planeHeight * 1 / 5);
            gr.fillOval(startPosX + planeWidth * 9 / 16, startPosY + planeHeight * 7 / 10, planeHeight * 1 / 5, planeHeight * 1 / 5);
            gr.fillRect(startPosX + planeWidth * 6 / 16 + planeHeight * 1 / 10, startPosY + planeHeight * 7 / 10,
                    planeWidth * 3 / 16, planeHeight * 1 / 5);
        }
        //радар
        if (hvRadar)
        {
            radars.drawRadars(dopColor, gr, startPosX, startPosY);
        }
    }
}
