public class AirfieldVehicleNotFoundException extends Exception{
    public AirfieldVehicleNotFoundException(int i){
        super("Не найден самолет по месту: " + i);
    }
}

