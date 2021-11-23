import java.io.*;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class AirfieldCollection {
    final Map<String, Airfield<Vehicle, RadarOne>> airfieldStages  = new HashMap<String, Airfield<Vehicle, RadarOne>>();
    public ArrayList<String> Keys(){
        return new ArrayList<>(airfieldStages.keySet());
    }
    private final int pictureWidth;
    private final int pictureHeight;
    private final char separator = ':';
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
    public boolean saveData(File saveFile){
        if (saveFile.exists()){
            saveFile.delete();
        }
        try(FileWriter writer = new FileWriter(saveFile)){
            writer.write("AirfieldCollection" + System.lineSeparator());
            for (int i = 0; i < airfieldStages.size(); ++i){
                Airfield<Vehicle, RadarOne> airfield = airfieldStages.get(Keys().get(i));
                writer.write("Airfield" + separator + Keys().get(i) + System.lineSeparator());
                ITransport plane = null;
                for (int j = 0; (plane = airfield.getPlane(j)) != null; ++j){
                    if (plane != null){
                        if (plane.getClass().getSimpleName().equals("Plane")){
                            writer.write("Plane" + separator);
                        }
                        if (plane.getClass().getSimpleName().equals("PlaneRadar")){
                            writer.write("PlaneRadar" + separator);
                        }
                        writer.write(plane + System.lineSeparator());
                    }
                }
            }
            return true;
        }
        catch (IOException ex){
            //System.out.println(ex.getMessage());
            return false;
        }
    }
    public boolean loadData(File loadFile){
        if (!loadFile.exists()){
            return false;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(loadFile))){
            String line = reader.readLine();
            if (line.contains("AirfieldCollection")){
                airfieldStages.clear();
            }
            else{
                return false;
            }
            Vehicle plane = null;
            String key = "";
            while ((line = reader.readLine()) != null){
                if (line.contains("Airfield")) {
                    key = line.split(String.valueOf(separator))[1];
                    airfieldStages.put(key, new Airfield<Vehicle, RadarOne>(pictureWidth, pictureHeight));
                    continue;
                }
                if (line.isEmpty()) {
                    continue;
                }
                if (Objects.equals(line.split(String.valueOf(separator))[0], "Plane")) {
                    plane = new Plane(line.split(String.valueOf(separator))[1]);
                }
                else {
                    if (Objects.equals(line.split(String.valueOf(separator))[0], "PlaneRadar")) {
                        plane = new PlaneRadar(line.split(String.valueOf(separator))[1]);
                    }
                }
                var result = airfieldStages.get(key).plus(plane);
                if (result == -1) {
                    return false;
                }
            }
            return true;
        }
        catch(IOException ex){
            return false;
        }
    }

    public boolean saveAirfield(File saveFile, String key) {
        if (!airfieldStages.containsKey(key)){
            return false;
        }
        if (saveFile.exists()){
            saveFile.delete();
        }
        try(FileWriter writer = new FileWriter(saveFile)){
            Airfield<Vehicle, RadarOne> airfield = airfieldStages.get(key);
            writer.write("Airfield" + separator + key + System.lineSeparator());
            ITransport plane = null;
            for (int j = 0; (plane = airfield.getPlane(j)) != null; ++j){
                if (plane != null){
                    if (plane.getClass().getSimpleName().equals("Plane")){
                        writer.write("Plane" + separator);
                    }
                    if (plane.getClass().getSimpleName().equals("PlaneRadar")){
                        writer.write("PlaneRadar" + separator);
                    }
                    writer.write(plane + System.lineSeparator());
                }
            }
            return true;
        }
        catch (IOException ex){
            //System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean loadAirfield(File loadFile) {
        if (!loadFile.exists()){
            return false;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(loadFile))){
            String line = reader.readLine();
            if (!line.contains("Airfield" + String.valueOf(separator))){
                return false;
            }
            else{
                String key = line.split(String.valueOf(separator))[1];
                if (airfieldStages.containsKey(key)){
                    airfieldStages.get(key).clear();
                }
                airfieldStages.put(key, new Airfield<Vehicle, RadarOne>(pictureWidth, pictureHeight));
                Vehicle plane = null;
                while ((line = reader.readLine()) != null){
                    if (Objects.equals(line.split(String.valueOf(separator))[0], "Plane")) {
                        plane = new Plane(line.split(String.valueOf(separator))[1]);
                    }
                    else {
                        if (Objects.equals(line.split(String.valueOf(separator))[0], "PlaneRadar")) {
                            plane = new PlaneRadar(line.split(String.valueOf(separator))[1]);
                        }
                    }
                    var result = airfieldStages.get(key).plus(plane);
                    if (result == -1) {
                        return false;
                    }
                }
            }
            return true;
        }
        catch(IOException ex){
            return false;
        }
    }
}
