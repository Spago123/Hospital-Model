package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Doctor;
import ba.unsa.etf.rpr.domain.History;
import ba.unsa.etf.rpr.domain.Patient;
import ba.unsa.etf.rpr.exceptions.HospitalException;

import javax.swing.event.HyperlinkEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * HistoryDaoSqlImpl is the class that contains all the methods that are needed
 * to work with the Histories table in the database
 */

public class HistoryDaoSQLImpl extends AbstractDao<History> implements HistoryDao{

    private static HistoryDaoSQLImpl instance = null;
    private  HistoryDaoSQLImpl() {
        super("Histories");
    }

    /**
     * Factory method for singleton design pattern
     * @return HistoryDaoSqlImpl
     */
    public static HistoryDaoSQLImpl getInstance(){
        if(instance == null)
            instance = new HistoryDaoSQLImpl();
        return instance;
    }

    /**
     * Method that converts a record from the Histories table
     * to a History JavaBean
     * @param rs - result set from database
     * @return History JavaBean
     * @throws HospitalException
     */
    @Override
    public History row2object(ResultSet rs) throws HospitalException {
        try{
            return new History(rs.getInt("id"), DaoFactory.patientDao().getById(rs.getInt("idPatient")),
                    DaoFactory.doctorDao().getById(rs.getInt("idDoctor")), rs.getString("diagnosis"));
        } catch (SQLException e) {
            throw new HospitalException(e.getMessage(), e);
        }
    }

    /**
     * Method that converts a History JavaBean to a record in the
     * Histories table in the database
     * @param object - a bean object for specific table
     * @return the item to updated to the Histories Table
     */
    @Override
    public Map<String, Object> object2row(History object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("idPatient", object.getPatient().getId());
        item.put("idDoctor", object.getDoctor().getId());
        item.put("diagnosis", object.getDiagnosis());
        return item;
    }

    /**
     * Method that lets you search the Histories table
     * for the given patient
     * @param patient the patient whose history you are searching
     * @return List of Histories for the given patient
     */
    @Override
    public List<History> searchByPatient(Patient patient) {
        try {
            return super.executeQuery("SELECT * FROM Histories WHERE idPatient = ?", new Object[]{patient.getId()});
        } catch (HospitalException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<History> searchByDoctor(Doctor doctor) {
        try {
            return super.executeQuery("SELECT * FROM Histories WHERE idDoctor = ?", new Object[]{doctor.getId()});
        } catch (HospitalException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<History> searchByDoctorAndPatient(Doctor doctor, Patient patient) {
        try {
            return super.executeQuery("SELECT * FROM Histories WHERE idDoctor = ? AND idPatient = ?",
                    new Object[]{doctor.getId(), patient.getId()});
        } catch (HospitalException e) {
            throw new RuntimeException(e);
        }
    }
}
