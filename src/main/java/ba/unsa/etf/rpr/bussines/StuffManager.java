package ba.unsa.etf.rpr.bussines;

/**
 * Stuff manger is a manager class which task is to verify different things
 */
public class StuffManager {
    /**
     * Method that verifies if the password is valid
     * @param pass
     * @return boolean
     */
    public static boolean verifyPassword(String pass){
        if(pass == null || pass.length()<7 || pass.length()>15){
            return false;
        }
        return true;
    }

}
