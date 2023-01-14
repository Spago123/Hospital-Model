package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.dao.DepartmentDaoSQLImpl;
import ba.unsa.etf.rpr.dao.DoctorDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Doctor;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.List;

public class DoctorManagerTest {

    private DoctorManager doctorManager;
    private Doctor doctor;
    private DoctorDaoSQLImpl doctorDaoSQLMock;
    private List<Doctor> doctors;

    private DepartmentManager departmentManager;
    private Department department;
    private DepartmentDaoSQLImpl departmentDaoSQLMock;
    private List<Department> departments;

    @BeforeEach
    public void initializeObjects(){
        doctorManager = Mockito.mock(DoctorManager.class);
        doctorDaoSQLMock = Mockito.mock(DoctorDaoSQLImpl.class);
        departmentManager = Mockito.mock(DepartmentManager.class);
        departmentDaoSQLMock = Mockito.mock(DepartmentDaoSQLImpl.class);



    }

}
