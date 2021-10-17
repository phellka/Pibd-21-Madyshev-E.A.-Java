import java.awt.*;

public class Airfield<T extends ITransport, M extends IRadars> {
    private final Object[] places;
    private final int pictureWidth;
    private final int pictureHeight;
    private final int placeSizeWidth = 330;
    private final int placeSizeHeight = 90;
    private final int airfieldWidth;
    private final int airfieldHeight;
    public Airfield(int picWidth, int picHeight) {
        airfieldWidth = picWidth / placeSizeWidth;
        airfieldHeight = picHeight / placeSizeHeight;
        places = new Object[airfieldWidth * airfieldHeight];
        pictureWidth = picWidth;
        pictureHeight = picHeight;
    }
    public int plus(T plane) {
        int i = 0;
        while (i < places.length && places[i] != null) {
            i++;
        }
        if (i < places.length && places[i] == null) {
            plane.SetPosition(i % airfieldWidth * placeSizeWidth + 10, i / airfieldWidth * placeSizeHeight + 5, pictureWidth, pictureHeight);
            places[i] = plane;
            return i;
        }
        else {
            return -1;
        }
    }
    public T minus(int index) {
        if (index > -1 && index < places.length && places[index] != null) {
            T bufPlane = (T) places[index];
            places[index] = null;
            return bufPlane;
        }
        else {
            return null;
        }
    }
    public boolean more(int count){
        int fullness = 0;
        for (int i = 0; i < places.length; ++i){
            if (places[i] != null){
                fullness += 1;
            }
        }
        return fullness > count;
    }
    public boolean less(int count){
        int fullness = 0;
        for (int i = 0; i < places.length; ++i){
            if (places[i] != null){
                fullness += 1;
            }
        }
        return fullness < count;
    }
    public void Draw(Graphics gr) {
        gr.setColor(Color.black);
        DrawMarking(gr);
        for (int i = 0; i < places.length; i++) {
            if (places[i] != null) {
                ((T) places[i]).DrawTransport(gr);
            }
        }
    }
    public void DrawMarking(Graphics gr) {
        gr.setColor(Color.black);
        for (int i = 0; i < airfieldWidth; i++) {
            for (int j = 0; j < airfieldHeight + 1; ++j) {
                gr.drawLine(i * placeSizeWidth, j * placeSizeHeight, i *
                        placeSizeWidth + placeSizeWidth / 2, j * placeSizeHeight);
            }
            gr.drawLine(i * placeSizeWidth, 0, i * placeSizeWidth,
                    (airfieldHeight) * placeSizeHeight);
        }
    }
}
