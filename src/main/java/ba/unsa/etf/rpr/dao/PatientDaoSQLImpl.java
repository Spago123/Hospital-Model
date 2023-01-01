package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Doctor;
import ba.unsa.etf.rpr.domain.Patient;
import ba.unsa.etf.rpr.exceptions.HospitalException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PatientDaoSQLImpl extends AbstractDao<Patient> implements PatientDao {

    public PatientDaoSQLImpl() {
        super("Patients");
    }

    @Override
    public Patient row2object(ResultSet rs) throws HospitalException {
        try{
            return new Patient(rs.getInt("id"), rs.getString("name"),
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
        row.put("UIN", object.getUIN());
        row.put("idDoctor", object.getDoctor().getId());
        return row;
    }

    @Override
    public List<Patient> searchByDoctor(Doctor doctor) throws HospitalException {
        return super.executeQuery("SELECT * FROM Patients WHERE idDoctor = ?", new Object[]{doctor.getId()});
    }
}
