import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Airfield<T extends ITransport, M extends IRadars> implements Iterable<Vehicle>, Iterator<Vehicle> {
    private ArrayList<T> places;
    private final int maxCount;
    private final int pictureWidth;
    private final int pictureHeight;
    private final int placeSizeWidth = 330;
    private final int placeSizeHeight = 90;
    private final int airfieldWidth;
    private final int airfieldHeight;
    public T getPlane(int index){
        if (index > -1 && index < places.size()){
            return places.get(index);
        }
        else{
            return null;
        }
    }
    //ArrayList вместо массива. массив нельзя проинициализировать, потому что компилятору не известен тип T. ... = new T[size];
    //можно передать ссылку, но его тоже надо инициализировать, но размер не известен заранее,
    //так как в нем используеются размеры ячеек ???
    //private T[] elements;
    //public MyCollection(T[] elements){
    //  this.elements = elements;
    //}
    public Airfield(int picWidth, int picHeight) {
        airfieldWidth = picWidth / placeSizeWidth;
        airfieldHeight = picHeight / placeSizeHeight;
        maxCount = airfieldWidth * airfieldHeight;
        places = new ArrayList<T>();
        pictureWidth = picWidth;
        pictureHeight = picHeight;
    }
    public int plus(T plane) throws AirfieldOverflowException, AirfieldAlreadyHaveException {
        if (places.size() >= maxCount) {
            throw new AirfieldOverflowException();
        }
        if (places.contains(plane)){
            throw new AirfieldAlreadyHaveException();
        }
        places.add(plane);
        return places.size() - 1;
    }
    public T minus(int index) throws AirfieldVehicleNotFoundException{
        if (index <= -1 || index >= places.size()) {
            throw new AirfieldVehicleNotFoundException(index);
        }
        T bufPlane = places.get(index);
        places.remove(index);
        return bufPlane;
    }
    public boolean more(int count){
        return places.size() > count;
    }
    public boolean less(int count){
        return places.size() < count;
    }
    public void Draw(Graphics gr) {
        gr.setColor(Color.black);
        DrawMarking(gr);
        for (int i = 0; i < places.size(); i++) {
            places.get(i).SetPosition(i % airfieldWidth * placeSizeWidth + 10, i / airfieldWidth * placeSizeHeight + 5, pictureWidth, pictureHeight);
            places.get(i).DrawTransport(gr);
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
    public void sort(){
        places.sort((Comparator<T>) new PlaneComparer());
    }
    private int count = 0;
    public boolean hasNext(){
        if (count < places.size()){
            return true;
        }
        return false;
    }
    public Vehicle next(){
        count += 1;
        return (Vehicle) getPlane(count - 1);
    }
    public void remove(){
        throw new UnsupportedOperationException();
    }
    public Iterator<Vehicle> iterator(){
        count = 0;
        return this;
    }
    public void clear(){
        places.clear();
        places = new ArrayList<T>();
    }
}
