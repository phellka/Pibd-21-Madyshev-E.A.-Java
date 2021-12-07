public class AirfieldOverflowException extends  Exception{
    public AirfieldOverflowException(){
        super("На аэродроме нет свободных мест");
    }
}
