package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.exceptions.HospitalException;
import org.apache.commons.cli.Option;

/**
 * CLI interface for Application that models a hospital
 * @author Harun Špago
 * @version 1.0.0
 */
public class App {
    private static final Option addDepartment = new Option("addD", "addDepartment", false,
            "Adding new department to database");
    private static final Option deleteDepartment = new Option("delD", "deleteDepartment", false,
            "Deleting department from database");

    private static final Option addDoctor = new Option("addDoc", "addDoctor", false,
            "Add new doctor to database");
    private static final Option deleteDoctor = new Option("deleteDoc", "deleteDoctor", false,
            "Deleting doctor from database");

    private static final Option addPatient = new Option("addP", "addPatient", false,
            "Adding new patient to database");
    private static final Option deletePatient = new Option("delP", "deletePatient", false,
            "Deleting patient from database");

    private static final Option addHistory = new Option("addH", "addHistory", false,
            "Adding new history to database");
    private static final Option deleteHistory = new Option("delH", "deleteHistory", false,
            "Deleting history from database");

    public static void main(String[] args ) throws HospitalException {
    }
}
