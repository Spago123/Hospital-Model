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

public class HistoryDaoSQLImpl extends AbstractDao<History> implements HistoryDao{

    private static HistoryDaoSQLImpl instance = null;
    private  HistoryDaoSQLImpl() {
        super("Histories");
    }

    public static HistoryDaoSQLImpl getInstance(){
        if(instance == null)
            instance = new HistoryDaoSQLImpl();
        return instance;
    }
    @Override
    public History row2object(ResultSet rs) throws HospitalException {
        try{
            return new History(rs.getInt("id"), DaoFactory.patientDao().getById(rs.getInt("idPatient")),
                    DaoFactory.doctorDao().getById(rs.getInt("idDoctor")), rs.getString("diagnosis"));
        } catch (SQLException e) {
            throw new HospitalException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(History object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("idPatient", object.getPatient().getId());
        item.put("idDoctor", object.getDoctor().getId());
        item.put("diagnosis", object.getDiagnosis());
        return item;
    }

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
