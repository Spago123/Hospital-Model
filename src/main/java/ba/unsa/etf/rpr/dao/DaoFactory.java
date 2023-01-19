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

    /**
     * Method that return PatientDao object
     * @return PatientDao
     */
    public static PatientDao patientDao(){
        return patientDao;
    }

    /**
     * Method that return DoctorDao object
     * @return DoctorDao
     */
    public static DoctorDao doctorDao(){
        return doctorDao;
    }

    /**
     * Method that return HistoryDao object
     * @return HistoryDao
     */
    public static HistoryDao historyDao(){
        return historyDao;
    }

    /**
     * Method that returns DepartmentDao
     * @return DepartmentDao
     */
    public static DepartmentDao departmentDao(){
        return departmentDao;
    }
}
