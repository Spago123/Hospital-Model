package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Doctor;
import ba.unsa.etf.rpr.domain.History;
import ba.unsa.etf.rpr.domain.Patient;
import ba.unsa.etf.rpr.exceptions.HospitalException;

import java.util.List;

/**
 * PatientManager is a class that models our work with Patients table in the database
 */
public class PatientManager implements Manager<Patient>{
    /**
     * Method that lets us search the database for
     * a patients with the given doctor
     * @param doctor
     * @return list of patients
     * @throws HospitalException
     */
    public List<Patient> getByDoctor(Doctor doctor) throws HospitalException {
        return DaoFactory.patientDao().searchByDoctor(doctor);
    }

    /**
     * Method that lets us get all patients from the database
     * @return list of patients
     * @throws HospitalException
     */
    public List<Patient> getAll() throws HospitalException {
        return DaoFactory.patientDao().getAll();
    }

    /**
     * Method that deletes a patient from the database
     * @param id of the element we want to delete
     * @throws HospitalException
     */
    @Override
    public void delete(int id) throws HospitalException {
       for(History history : DaoFactory.historyDao().searchByPatient(DaoFactory.patientDao().getById(id))) {
           DaoFactory.historyDao().delete(history.getId());
       }
       DaoFactory.patientDao().delete(id);
    }

    /**
     * Method that adds a patient to the database
     * @param item we want to add
     * @throws HospitalException
     */
    @Override
    public void add(Patient item) throws HospitalException {

        if (!StuffManager.checkUIN(String.valueOf(item.getUIN())))
            throw new HospitalException("UIN is not valid");

        if(!StuffManager.verifyPassword(item.getPassword()))
            throw new HospitalException("Password should be between 7 and 15 letters long!");

        if (getByNameAndUIN(item.getName(), item.getUIN()).size() != 0)
            throw new HospitalException("Patient with name: " + item.getName() + " and UIN: " + item.getUIN() + "already exists");

        DaoFactory.patientDao().add(item);
    }

    /**
     * Method that lets us update a patient in the database
     * @param item we want to update
     * @throws HospitalException
     */
    public void update(Patient item) throws HospitalException {

        if(!StuffManager.verifyPassword(item.getPassword()))
            throw new HospitalException("Password should be between 7 and 15 letters long!");

        DaoFactory.patientDao().update(item);
    }

    /**
     * Method that lets us get a patient from the database by the id
     * @param id we search
     * @return
     * @throws HospitalException
     */
    public Patient getById(int id) throws HospitalException {
        return DaoFactory.patientDao().getById(id);
    }

    /**
     * Method that returns a patient that has the following parameters
     * name and password
     * @param name
     * @param pass
     * @return list of patients, should actually return only one patient
     */
    public List<Patient> getByNameAndPass(String name, String pass){
        return DaoFactory.patientDao().searchByNameAndPass(name, pass);
    }

    /**
     * Method that lets us get a patient by name and UIN
     * @param name
     * @param UIN
     * @return list of patients, should actually return one patient
     */
    public List<Patient> getByNameAndUIN(String name, Long UIN){
        return DaoFactory.patientDao().searchByNameAndUin(name, UIN);
    }
}
