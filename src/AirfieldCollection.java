import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class AirfieldCollection {
    final Map<String, Airfield<Vehicle, RadarOne>> airfieldStages  = new HashMap<String, Airfield<Vehicle, RadarOne>>();
    //переделать
    //переделать
    //переделать
    //переделать
    //переделать
    public ArrayList<String> Keys(){
        return new ArrayList<>(airfieldStages.keySet());
    }
    private final int pictureWidth;
    private final int pictureHeight;
    public AirfieldCollection(int pictureWidth, int pictureHeight) {
        //airfieldStages = new HashMap<String, Airfield<Vehicle, RadarOne>>();
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;
    }
    public void AddAirfield(String name) {
        if (!airfieldStages.containsKey(name)) {
            airfieldStages.put(name, new Airfield<Vehicle, RadarOne>(pictureWidth, pictureHeight));
        }
    }
    public void DelAirfield(String name) {
        if (airfieldStages.containsKey(name)){
            airfieldStages.remove(name);
        }
    }
    //в java нет перегрузки индексаторов
    public Airfield<Vehicle, RadarOne> getAirfield(String index){
        if (airfieldStages.containsKey(index)){
            return airfieldStages.get(index);
        }
        else{
            return null;
        }
    }
    public Vehicle getPlaneAirfield(String indexAirfield, int indexPlane){
        if (airfieldStages.containsKey(indexAirfield) && airfieldStages.get(indexAirfield) != null){
            return airfieldStages.get(indexAirfield).getPlane(indexPlane);
        }
        else{
            return null;
        }
    }
}
