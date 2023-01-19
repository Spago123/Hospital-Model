package ba.unsa.etf.rpr.exceptions;

/**
 * Custom exception made for this Application
 * @author Harun Špago
 */
public class HospitalException extends Exception{

    public HospitalException(String msg){
        super(msg);
    }
    public HospitalException(String msg, Exception reason){
        super(msg, reason);
    }
}
