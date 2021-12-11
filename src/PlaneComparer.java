import java.util.Comparator;

public class PlaneComparer implements Comparator<Vehicle> {
    @Override
    public int compare(Vehicle x, Vehicle y) {
        if (x.getClass() == y.getClass()){
            if(x.getClass() == Plane.class){
                return ComparerPlane((Plane)x, (Plane)y);
            }
            else{
                return ComparerPlaneRadar((PlaneRadar)x, (PlaneRadar)y);
            }
        }
        else{
            if (x.getClass() == Plane.class){
                return 1;
            }
            else{
                return -1;
            }
        }
    }
    private int ComparerPlane(Plane x, Plane y) {
        if (x.maxSpeed != y.maxSpeed) {
            return Integer.compare(x.maxSpeed, y.maxSpeed);
        }
        if (x.weight != y.weight) {
            return Integer.compare(x.weight, y.weight);
        }
        if (x.mainColor != y.mainColor) {
            return Integer.compare(x.mainColor.hashCode(), y.mainColor.hashCode());
        }
        return 0;
    }
    private int ComparerPlaneRadar(PlaneRadar x, PlaneRadar y) {
        var res = ComparerPlane(x, y);
        if (res != 0) {
            return res;
        }
        if (x.dopColor != y.dopColor) {
            return Integer.compare(x.dopColor.hashCode(), y.dopColor.hashCode());
        }
        if (x.hvEngine != y.hvEngine) {
            return Boolean.compare(x.hvEngine, y.hvEngine);
        }
        if (x.hvRadar != y.hvRadar) {
            return Boolean.compare(x.hvRadar, y.hvRadar);
        }
        return 0;
    }
}
