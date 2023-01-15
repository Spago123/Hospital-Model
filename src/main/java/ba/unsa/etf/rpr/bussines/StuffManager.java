package ba.unsa.etf.rpr.bussines;

public class StuffManager {

    public static boolean verifyPassword(String pass){
        if(pass == null || pass.length()<7 || pass.length()>15){
            return false;
        }
        return true;
    }

}
