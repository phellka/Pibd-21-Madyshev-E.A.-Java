public class AirfieldAlreadyHaveException extends  Exception{
    public AirfieldAlreadyHaveException(){
        super("На аэродроме уже есть такой самолет");
    }
}
