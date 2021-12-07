import java.awt.*;
import java.util.Iterator;

public class Plane extends Vehicle implements Comparable<Object>, Iterable<String>, Iterator<String> {
    protected int planeWidth = 170;
    protected int planeHeight = 80;
    protected char separator = ';';
    public Plane(int maxSpeed, int weight, Color mainColor) {
        super.maxSpeed = maxSpeed;
        super.weight = weight;
        super.mainColor = mainColor;
    }
    public Plane(int maxSpeed, int weight, Color mainColor, int planeWidth, int planeHeight) {
        super.maxSpeed = maxSpeed;
        super.weight = weight;
        super.mainColor = mainColor;
        this.planeWidth = planeWidth;
        this.planeHeight = planeHeight;
    }
    public Plane(String info){
        String[] strs = info.split(String.valueOf(separator));
        if (strs.length == 3){
            maxSpeed = Integer.parseInt(strs[0]);
            weight = Integer.parseInt(strs[1]);
            mainColor = Color.decode(strs[2]);
        }
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
        gr.setColor(Color.black);
        //корпус
        gr.setColor(mainColor);
        gr.fillRect(startPosX + planeHeight / 5, startPosY + planeHeight * 2 / 5, planeWidth - planeHeight * 2 / 5, planeHeight * 2 / 5);
        gr.fillOval(startPosX, startPosY + planeHeight * 2 / 5, planeHeight * 2 / 5, planeHeight * 2 / 5);
        gr.fillPolygon(new int[]{startPosX + planeWidth - planeHeight / 5, startPosX + planeWidth - planeHeight / 5, startPosX + planeWidth},
                new int[]{startPosY + planeHeight * 2 / 5, startPosY + planeHeight * 4 / 5, startPosY + planeHeight * 3 / 5}, 3);
        gr.setColor(Color.black);
        gr.drawPolygon(new int[]{startPosX + planeWidth - planeHeight / 5, startPosX + planeWidth - planeHeight / 5, startPosX + planeWidth},
                new int[]{startPosY + planeHeight * 2 / 5, startPosY + planeHeight * 4 / 5, startPosY + planeHeight * 3 / 5}, 3);
        gr.drawLine(startPosX + planeWidth - planeHeight / 5, startPosY + planeHeight * 3 / 5, startPosX + planeWidth, startPosY + planeHeight * 3 / 5);
        gr.setColor(mainColor);
        //крылья
        gr.fillPolygon(new int[]{ startPosX + planeHeight / 5, startPosX + planeHeight / 5, startPosX + planeHeight * 3 / 5 },
                new int[]{startPosY, startPosY + planeHeight * 2 / 5, startPosY + planeHeight * 2 / 5}, 3);
        gr.setColor(Color.black);
        gr.fillOval(startPosX + planeHeight / 10, startPosY + planeHeight * 7 / 20, planeHeight * 2 / 5, planeHeight / 10);
        gr.fillOval(startPosX + planeWidth / 4, startPosY + planeHeight / 2, planeWidth / 2, planeHeight / 5);
        //шасси
        gr.drawLine(startPosX + planeWidth / 4, startPosY + planeHeight * 4 / 5, startPosX + planeWidth / 4, startPosY + planeHeight * 19 / 20);
        gr.drawOval(startPosX + planeWidth / 4 - planeHeight / 10, startPosY + planeHeight * 9 / 10, planeHeight / 10, planeHeight / 10);
        gr.drawOval(startPosX + planeWidth / 4, startPosY + planeHeight * 9 / 10, planeHeight / 10, planeHeight / 10);
        gr.drawLine(startPosX + planeWidth * 3 / 4, startPosY + planeHeight * 4 / 5, startPosX + planeWidth * 3 / 4, startPosY + planeHeight * 9 / 10);
        gr.drawOval(startPosX + planeWidth * 3 / 4 - planeHeight / 20, startPosY + planeHeight * 9 / 10, planeHeight / 10, planeHeight / 10);
    }
    @Override
    public String toString(){
        return String.valueOf(maxSpeed) + String.valueOf(separator) + String.valueOf(weight)
                + String.valueOf(separator) + String.valueOf(mainColor.hashCode());
    }
    public boolean equals(Plane other) {
        if (other == null) {
            return false;
        }
        if (other.getClass() != Plane.class) {
            return false;
        }
        if (maxSpeed != other.maxSpeed) {
            return false;
        }
        if (weight != other.weight) {
            return false;
        }
        if (mainColor != other.mainColor) {
            return false;
        }
        return true;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != Plane.class) {
            return false;
        }
        else {
            return equals((Plane)obj);
        }
    }
    @Override
    public int compareTo(Object obj) {
        if (obj == null) {
            return -1;
        }
        if (obj.getClass() != Plane.class) {
            return -1;
        }
        Plane other = (Plane) obj;
        if (maxSpeed != other.maxSpeed) {
            return Integer.compare(maxSpeed, other.maxSpeed);
        }
        if (weight != other.weight) {
            return Integer.compare(weight, other.weight);
        }
        if (mainColor != other.mainColor) {
            return Integer.compare(mainColor.hashCode(), other.mainColor.hashCode());
        }
        return 0;
    }
    private int count = 0;
    public boolean hasNext(){
        if (count < toString().split(String.valueOf(separator)).length){
            return true;
        }
        return false;
    }
    public String next(){
        count += 1;
        return toString().split(String.valueOf(separator))[count - 1];
    }
    public void remove(){}
    public Iterator<String> iterator(){
        count = 0;
        return this;
    }
}
