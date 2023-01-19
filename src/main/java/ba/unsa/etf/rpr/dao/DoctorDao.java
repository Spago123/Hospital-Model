package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Doctor;

import java.util.List;

/**
 * DoctorDao is an interface that has to be implemented
 * by the DaoSqlImpl that works with the Doctors table in the database
 */

public interface DoctorDao extends Dao<Doctor>{
    /**
     * Method that lets you search the Doctors table by their department id
     * @param department department name
     * @return  list of doctors that belong to that department
     */
    List<Doctor> searchByDepartment(Department department);

    /**
     * Method that lets you search the Doctors table by doctors name and pasword
     * @param name of the Doctor
     * @param password of the Doctor
     * @return List of Doctors
     */
    List<Doctor> searchByNameAndPassword(String name, String password);
}
