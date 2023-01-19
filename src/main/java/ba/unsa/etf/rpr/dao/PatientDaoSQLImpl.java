package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Doctor;
import ba.unsa.etf.rpr.domain.Patient;
import ba.unsa.etf.rpr.exceptions.HospitalException;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * PatientDaoSqlImpl is the class that contains all the methods that are needed
 * to work with the Patients table in the database
 */
public class PatientDaoSQLImpl extends AbstractDao<Patient> implements PatientDao {

    private static PatientDaoSQLImpl instance = null;

    private PatientDaoSQLImpl() {
        super("Patients");
    }

    /**
     * Factory method for singleton design pattern
     * @return PatientDaoSqlImpl
     */
    public static PatientDaoSQLImpl getInstance(){
        if(instance == null)
            instance = new PatientDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance = null;
    }

    /**
     * Method that converts a record from the Patients table
     * to a Patient JavaBean
     * @param rs - result set from database
     * @return Patient JavaBean
     * @throws HospitalException
     */
    @Override
    public Patient row2object(ResultSet rs) throws HospitalException {
        try{
            return new Patient(rs.getInt("id"), rs.getString("name"), rs.getString("password"),
                    rs.getLong("UIN"), DaoFactory.doctorDao().getById(rs.getInt("idDoctor")));
        } catch (SQLException e) {
            throw new HospitalException(e.getMessage(), e);
        }
    }

    /**
     * Method that converts a Patient JavaBean
     * to a record in the Patients table
     * @param object - a bean object for specific table
     * @return created record
     */
    @Override
    public Map<String, Object> object2row(Patient object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("name", object.getName());
        row.put("password", object.getPassword());
        row.put("UIN", object.getUIN());
        row.put("idDoctor", object.getDoctor().getId());
        return row;
    }

    /**
     * Method that lets you search the Patients table
     * for patients who have the specified doctor
     * @param doctor doctor whose patients you are searching
     * @return list od patients who have the given doctor as doctor
     */
    @Override
    public List<Patient> searchByDoctor(Doctor doctor) {
        try {
            return super.executeQuery("SELECT * FROM Patients WHERE idDoctor = ?", new Object[]{doctor.getId()});
        } catch (HospitalException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method that lists out the patients that have the specified
     * name and password
     * @param name of the patient
     * @param pass of the patient
     * @return list of patients who have the same name and password
     */
    @Override
    public List<Patient> searchByNameAndPass(String name, String pass) {
        try {
            return super.executeQuery("SELECT * FROM Patients WHERE name = ? AND password = ?", new Object[] {name, pass});
        } catch (HospitalException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method that lists put the patients that have the specified
     * name and unique personal identifier
     * @param name of the patent
     * @param UIN of the patient
     * @return list of patients who have the same name and UIN
     */
    @Override
    public List<Patient> searchByNameAndUin(String name, Long UIN) {
        try {
            return super.executeQuery("SELECT * FROM Patients WHERE name = ? AND UIN = ?", new Object[]{name, UIN});
        } catch (HospitalException e) {
            throw new RuntimeException(e);
        }
    }
}
