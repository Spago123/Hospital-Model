package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.DepartmentDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.exceptions.HospitalException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DepartmentManagerTest {
    private DepartmentManager departmentManager;
    private Department department;
    private DepartmentDaoSQLImpl departmentDaoSQLMock;
    private List<Department> departmentList;

    @BeforeEach
    public void initializeObjects(){
        departmentManager = Mockito.mock(DepartmentManager.class);
        departmentDaoSQLMock = Mockito.mock(DepartmentDaoSQLImpl.class);

        department = new Department(40, "Lobotomija");

        departmentList = new ArrayList<>();
        departmentList.addAll(Arrays.asList(new Department(40, "Lobotomija"), new Department(41, "Kardiologija")
        , new Department(42, "Ortopedija")));

    }


    @Test
    public void addDepartment() throws HospitalException {
        Department newDepartment = new Department(40, "Novi odjel");
        departmentManager.add(newDepartment);

        Assertions.assertTrue(true);
        Mockito.verify(departmentManager).add(newDepartment);
    }
}
