package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Doctor;
import ba.unsa.etf.rpr.domain.History;
import ba.unsa.etf.rpr.domain.Patient;
import ba.unsa.etf.rpr.exceptions.HospitalException;

import java.util.List;

/**
 * Diagnosis manager is a class that models our work with the Histories table in the database
 */
public class DiagnosisManager implements Manager<History>{
    /**
     * Method that lets us return all histories that belong to the same patient
     * @param patient whose histories we are searching
     * @return list of histories
     */
    public List<History> getByPatient(Patient patient){
        return DaoFactory.historyDao().searchByPatient(patient);
    }

    /**
     * Method that lets us return all histories that were given by the same doctor
     * @param doctor whose histories we are searching
     * @return list of histories
     */
    public List<History> getByDoctor(Doctor doctor){
        return DaoFactory.historyDao().searchByDoctor(doctor);
    }

    /**
     * Method that lets us add a history to the database
     * @param item we want to add
     * @throws HospitalException
     */
    public void add(History item) throws HospitalException {
        DaoFactory.historyDao().add(item);
    }

    /**
     * Method that return the history from the database that has the provided id
     * @param id we search
     * @return history with the given id
     * @throws HospitalException
     */
    public History getById(int id) throws HospitalException {
        return DaoFactory.historyDao().getById(id);
    }

    /**
     * Method that return all histories from the database
     * @return list of histories
     * @throws HospitalException
     */
    public List<History> getAll() throws HospitalException {
        return DaoFactory.historyDao().getAll();
    }

    /**
     * Method that lets us delete a history from the database that has the
     * provided id
     * @param id of the element we want to delete
     * @throws HospitalException
     */
    @Override
    public void delete(int id) throws HospitalException {
        DaoFactory.historyDao().delete(id);
    }

    /**
     * Method that lets us update a history in the database
     * @param item we want to update
     * @throws HospitalException
     */
    @Override
    public void update(History item) throws HospitalException {
        DaoFactory.historyDao().update(item);
    }
}
