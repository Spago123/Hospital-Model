package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.DepartmentDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.exceptions.HospitalException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

    public DepartmentManager getDepartmentManager() {
        return departmentManager;
    }

    public void setDepartmentManager(DepartmentManager departmentManager) {
        this.departmentManager = departmentManager;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public DepartmentDaoSQLImpl getDepartmentDaoSQLMock() {
        return departmentDaoSQLMock;
    }

    public void setDepartmentDaoSQLMock(DepartmentDaoSQLImpl departmentDaoSQLMock) {
        this.departmentDaoSQLMock = departmentDaoSQLMock;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }



    @Test
    public void addDepartment() throws HospitalException {
        Department newDepartment = new Department(40, "Novi odjel");
        Mockito.doCallRealMethod().when(departmentManager).add(newDepartment);
        departmentManager.add(newDepartment);

        Assertions.assertTrue(true);
        Mockito.verify(departmentManager).add(newDepartment);
    }

    @Test
    public void deleteDepartment() throws HospitalException {
        Department deleteDepartment = new Department(40, "Novi odjel");
        Mockito.doCallRealMethod().when(departmentManager).delete(deleteDepartment.getId());
        departmentManager.delete(deleteDepartment.getId());

        Assertions.assertTrue(true);
        Mockito.verify(departmentManager).delete(deleteDepartment.getId());
    }

    @Test
    void addAlreadyExisting() throws HospitalException {
        Department newDepartment = new Department(40, "Novi odjel");
        Mockito.doCallRealMethod().when(departmentManager).add(newDepartment);

        HospitalException exception = Assertions.assertThrows(HospitalException.class, () -> departmentManager.add(newDepartment),
                "Department already exists");

        Assert.assertEquals("Department already exists", exception.getMessage());
    }
}
