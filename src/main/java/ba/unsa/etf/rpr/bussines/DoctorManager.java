package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Doctor;
import ba.unsa.etf.rpr.exceptions.HospitalException;

import java.util.List;

/**
 * DoctorManager is a class that models our work with the Doctor table in the database
 */
public class DoctorManager implements Manager<Doctor>{
    /**
     * Method that lets us update an doctor in the database
     * @param item we want to update
     * @throws HospitalException
     */
    @Override
    public void update(Doctor item) throws HospitalException {
        DaoFactory.doctorDao().update(item);
    }

    /**
     * Method that lets us add a doctor to the Doctors table
     * @param item we want to add
     * @throws HospitalException
     */
    @Override
    public void add(Doctor item) throws HospitalException {
        for(Doctor doctor : getAll()){
            if(doctor.getName().equals(item.getName()))
                throw new HospitalException(item + " is already in database!");
        }
        DaoFactory.doctorDao().add(item);
    }

    /**
     * Method that lets us search a doctor by name and password
     * @param name of the doctor
     * @param pass of the doctor
     * @return doctor
     */
    public List<Doctor> getByNameAndPass(String name, String pass){
        return DaoFactory.doctorDao().searchByNameAndPassword(name, pass);
    }

    /**
     * Method that lets us get a doctor from the database
     * that has the provided id
     * @param id we search
     * @return doctor with the given id
     * @throws HospitalException
     */
    public Doctor getById(int id) throws HospitalException {
        return DaoFactory.doctorDao().getById(id);
    }

    /**
     * Method that lets us get all doctors from the doctor table
     * @return list of doctors
     * @throws HospitalException
     */
    @Override
    public List<Doctor> getAll() throws HospitalException {
        return DaoFactory.doctorDao().getAll();
    }

    /**
     * Method that allows us to delete a doctor from the database
     * @param id of the element we want to delete
     * @throws HospitalException
     */
    @Override
    public void delete(int id) throws HospitalException {
        DaoFactory.doctorDao().delete(id);
    }
}
