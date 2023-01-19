package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.DepartmentDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.exceptions.HospitalException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

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
        departmentList.addAll(Arrays.asList(new Department(40, "Novi odjel"), new Department(41, "Kardiologija")
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
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::departmentDao).thenReturn(departmentDaoSQLMock);
        when(DaoFactory.departmentDao().getAll()).thenReturn(departmentList);
        when(departmentManager.getAll()).thenReturn(departmentList);

        //when(DaoFactory.departmentDao().add(department)).thenAnswer(() ->{departmentList.add(department);});


        department = new Department(50, "Stari odjel");
       // Mockito.doCallRealMethod().when(daoFactoryMockedStatic).add(department);
        Mockito.doCallRealMethod().when(departmentManager).add(department);
        departmentManager.add(department);

        Assertions.assertTrue(true);
      //  daoFactoryMockedStatic.verify(DaoFactory::departmentDao);
        Mockito.verify(departmentManager).add(department);
        daoFactoryMockedStatic.close();

    }

    @Disabled
    public void deleteDepartment() throws HospitalException {
        when(departmentManager.getAll()).thenReturn(departmentList);
        Department deleteDepartment = new Department(40, "Novi odjel");
        Mockito.doCallRealMethod().when(departmentManager).delete(deleteDepartment.getId());
        departmentManager.delete(deleteDepartment.getId());

        Assertions.assertTrue(true);
        Mockito.verify(departmentManager).delete(deleteDepartment.getId());
    }

    @Test
    void addAlreadyExisting() throws HospitalException {
        when(departmentManager.getAll()).thenReturn(departmentList);

        department = new Department(40, "Novi odjel");
        Mockito.doCallRealMethod().when(departmentManager).add(department);

        HospitalException exception = Assertions.assertThrows(HospitalException.class, () -> {departmentManager.add(department);});

        Assertions.assertEquals(department.getName() + " department already exists!", exception.getMessage());

        Mockito.verify(departmentManager).add(department);
    }
}
