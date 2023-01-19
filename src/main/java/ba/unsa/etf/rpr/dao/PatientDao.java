package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Doctor;
import ba.unsa.etf.rpr.domain.Patient;

import java.util.List;

/**
 * PatientDao is an interface that has to be implemented
 * by the DaoSqlImpl that works with the Patient table in the database
 */

public interface PatientDao extends Dao<Patient>{
    /**
     * Method that lists out the patients that belong to the same doctor
     * @param doctor doctor whose patients you are searching
     * @return list of patients
     */
    List<Patient> searchByDoctor(Doctor doctor);

    /**
     * Method that lists out the patients that have the specified
     * name and password
     * @param name of the patient
     * @param pass of the patient
     * @return list of patients that have the same name and password
     */
    List<Patient> searchByNameAndPass(String name, String pass);

    List<Patient> searchByNameAndUin(String name, Long UIN);

}
