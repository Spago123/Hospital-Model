package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Doctor;
import ba.unsa.etf.rpr.exceptions.HospitalException;

import javax.print.Doc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * DoctorDaoSqlImpl is the class that contains all the methods that are needed
 * to work with the Doctors table in the database
 */
public class DoctorDaoSQLImpl extends AbstractDao<Doctor> implements DoctorDao{

    private static DoctorDaoSQLImpl instance = null;
    private DoctorDaoSQLImpl() {
        super("Doctors");
    }

    /**
     * Factory method for singleton design pattern
     * @return DoctorDaoSqlImpl
     */
    public static DoctorDaoSQLImpl getInstance(){
        if(instance == null)
            instance = new DoctorDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance!=null)
            instance = null;
    }

    /**
     * Method that converts an entity from Doctor table
     * to Doctor JavaBean
     * @param rs - result set from database
     * @return Doctor JavaBean
     * @throws HospitalException
     */
    @Override
    public Doctor row2object(ResultSet rs) throws HospitalException {
        try{
            return new Doctor(rs.getInt("id"), rs.getString("name"),
                    rs.getString("password"), DaoFactory.departmentDao().getById(rs.getInt("idDepartment")));
        } catch (SQLException e) {
            throw new HospitalException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Doctor object) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", object.getId());
        item.put("name", object.getName());
        item.put("password", object.getPassword());
        item.put("idDepartment", object.getDepartment().getId());
        return item;
    }

    @Override
    public List<Doctor> searchByDepartment(Department department) {
        try {
            return super.executeQuery("SELECT * FROM Doctors WHERE idDepartment = ?", new Object[]{department.getId()});
        } catch (HospitalException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Doctor> searchByNameAndPassword(String name, String password) {
        try {
            return super.executeQuery("SELECT * FROM Doctors WHERE name = ? AND password = ?",
                    new Object[]{name, password});
        } catch (HospitalException e) {
            throw new RuntimeException(e);
        }
    }
}
