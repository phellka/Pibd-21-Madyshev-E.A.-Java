import java.awt.*;
import java.util.Objects;
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
    public PlaneRadar(int maxSpeed, int weight, Color mainColor, Color dopColor, boolean hvRadar, boolean hvEngine){
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
    public PlaneRadar(String info){
        super(info);
        String[] strs = info.split(String.valueOf(separator));
        if (strs.length == 8){
            maxSpeed = Integer.parseInt(strs[0]);
            weight = Integer.parseInt(strs[1]);
            mainColor = Color.decode(strs[2]);
            dopColor = Color.decode(strs[3]);
            hvRadar = Boolean.parseBoolean(strs[4]);
            hvEngine = Boolean.parseBoolean(strs[5]);
            if (Objects.equals(strs[6], "semicircular")){
                radars = new RadarOne();
            }
            if (Objects.equals(strs[6], "circular")){
                radars = new RadarTwo();
            }
            if (Objects.equals(strs[6], "sophisticated")){
                radars = new RadarThree();
            }
            radars.Init(planeWidth, planeHeight);
            radars.setQuantity(Integer.parseInt(strs[7]));
        }
    }
    public void setRadars(IRadars radars){
        this.radars = radars;
        radars.Init(planeWidth, planeHeight);
        Random rnd = new Random();
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
    @Override
    public String toString(){
        return String.valueOf(maxSpeed) + String.valueOf(separator) + String.valueOf(weight)
                + String.valueOf(separator) + String.valueOf(mainColor.hashCode()) + String.valueOf(separator)
                + String.valueOf(dopColor.hashCode()) + String.valueOf(separator) + String.valueOf(hvRadar) +
                String.valueOf(separator) + String.valueOf(hvEngine) + String.valueOf(separator)
                + radars;
    }
}
