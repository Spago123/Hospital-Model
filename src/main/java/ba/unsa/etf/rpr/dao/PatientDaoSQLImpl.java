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

    public static PatientDaoSQLImpl getInstance(){
        if(instance == null)
            instance = new PatientDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance = null;
    }
    @Override
    public Patient row2object(ResultSet rs) throws HospitalException {
        try{
            return new Patient(rs.getInt("id"), rs.getString("name"), rs.getString("password"),
                    rs.getLong("UIN"), DaoFactory.doctorDao().getById(rs.getInt("idDoctor")));
        } catch (SQLException e) {
            throw new HospitalException(e.getMessage(), e);
        }
    }

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

    @Override
    public List<Patient> searchByDoctor(Doctor doctor) {
        try {
            return super.executeQuery("SELECT * FROM Patients WHERE idDoctor = ?", new Object[]{doctor.getId()});
        } catch (HospitalException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Patient> searchByNameAndPass(String name, String pass) {
        try {
            return super.executeQuery("SELECT * FROM Patients WHERE name = ? AND password = ?", new Object[] {name, pass});
        } catch (HospitalException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Patient> searchByNameAndUin(String name, Long UIN) {
        try {
            return super.executeQuery("SELECT * FROM Patients WHERE name = ? AND UIN = ?", new Object[]{name, UIN});
        } catch (HospitalException e) {
            throw new RuntimeException(e);
        }
    }
}
