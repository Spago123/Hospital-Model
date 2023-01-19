package ba.unsa.etf.rpr.dao;

/**
 * DaoFactory represents the implementation of Factory design pattern
 */
public class DaoFactory {
    private static final PatientDao patientDao = PatientDaoSQLImpl.getInstance();
    private static final DoctorDao doctorDao = DoctorDaoSQLImpl.getInstance();
    private static final HistoryDao historyDao = HistoryDaoSQLImpl.getInstance();
    private static final DepartmentDao departmentDao = DepartmentDaoSQLImpl.getInstance();

    public DaoFactory(){

    }

    public static PatientDao patientDao(){
        return patientDao;
    }

    public static DoctorDao doctorDao(){
        return doctorDao;
    }

    public static HistoryDao historyDao(){
        return historyDao;
    }

    public static DepartmentDao departmentDao(){
        return departmentDao;
    }
}
