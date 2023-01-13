package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Doctor;
import ba.unsa.etf.rpr.exceptions.HospitalException;

import java.util.List;

public class DoctorManager implements Manager<Doctor>{
    @Override
    public void update(Doctor item) throws HospitalException {
        DaoFactory.doctorDao().update(item);
    }

    @Override
    public void add(Doctor item) throws HospitalException {
        DaoFactory.doctorDao().add(item);
    }

    public List<Doctor> getByNameAndPass(String name, String pass){
        return DaoFactory.doctorDao().searchByNameAndPassword(name, pass);
    }

    public Doctor getById(int id) throws HospitalException {
        return DaoFactory.doctorDao().getById(id);
    }

    @Override
    public List<Doctor> getAll() throws HospitalException {
        return DaoFactory.doctorDao().getAll();
    }

    @Override
    public void delete(int id) throws HospitalException {
        DaoFactory.doctorDao().delete(id);
    }
}
