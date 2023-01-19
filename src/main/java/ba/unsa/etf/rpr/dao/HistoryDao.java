package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Doctor;
import ba.unsa.etf.rpr.domain.History;
import ba.unsa.etf.rpr.domain.Patient;

import java.util.List;

/**
 * HistoryDao is an interface that has to be implemented
 * by the DaoSqlImpl that works with the Histories table in the database
 */

public interface HistoryDao extends Dao<History> {
    /**
     * Method that lists out the history of a patient
     * @param patient the patient whose history you are searching
     * @return list of histories
     */
    List<History> searchByPatient(Patient patient);

    /**
     * Method that lists out the every history that was given by the same doctor
     * @param doctor doctor whose histories you are searching
     * @return list of histories
     */
    List<History> searchByDoctor(Doctor doctor);

    /**
     * Metod that lists out every history that was given to the specific patient by the same doctor
     * @param doctor doctor whose history you are searching
     * @param patient patient whose history you are searching
     * @return list of histories
     */
    List<History> searchByDoctorAndPatient(Doctor doctor, Patient patient);
}
