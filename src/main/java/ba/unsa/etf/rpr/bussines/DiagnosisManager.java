package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Doctor;
import ba.unsa.etf.rpr.domain.History;
import ba.unsa.etf.rpr.domain.Patient;
import ba.unsa.etf.rpr.exceptions.HospitalException;

import java.util.List;

public class DiagnosisManager implements Manager<History>{

    public List<History> getByPatient(Patient patient){
        return DaoFactory.historyDao().searchByPatient(patient);
    }

    public List<History> getByDoctor(Doctor doctor){
        return DaoFactory.historyDao().searchByDoctor(doctor);
    }

    public void add(History item) throws HospitalException {
        DaoFactory.historyDao().add(item);
    }

    public History getById(int id) throws HospitalException {
        return DaoFactory.historyDao().getById(id);
    }
    public List<History> getAll() throws HospitalException {
        return DaoFactory.historyDao().getAll();
    }

    @Override
    public void delete(int id) throws HospitalException {
        DaoFactory.historyDao().delete(id);
    }

    @Override
    public void update(History item) throws HospitalException {
        DaoFactory.historyDao().update(item);
    }
}
