package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Doctor;
import ba.unsa.etf.rpr.domain.Patient;
import ba.unsa.etf.rpr.exceptions.HospitalException;

import java.util.List;

public class PatientManager implements Manager<Patient>{

    public List<Patient> getByDoctor(Doctor doctor) throws HospitalException {
        return DaoFactory.patientDao().searchByDoctor(doctor);
    }

    public List<Patient> getAll() throws HospitalException {
        return DaoFactory.patientDao().getAll();
    }

    @Override
    public void delete(int id) throws HospitalException {
     DaoFactory.patientDao().delete(id);
    }

    @Override
    public void add(Patient item) throws HospitalException {
        DaoFactory.patientDao().add(item);
    }

    public void update(Patient item) throws HospitalException {
        DaoFactory.patientDao().update(item);
    }

    public Patient getById(int id) throws HospitalException {
        return DaoFactory.patientDao().getById(id);
    }

    public List<Patient> getByNameAndPass(String name, String pass){
        return DaoFactory.patientDao().searchByNameAndPass(name, pass);
    }

    public List<Patient> getByNameAndUIN(String name, Long UIN){
        return DaoFactory.patientDao().searchByNameAndUin(name, UIN);
    }
}
