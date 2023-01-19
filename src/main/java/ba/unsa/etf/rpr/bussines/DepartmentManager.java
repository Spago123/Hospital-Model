package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.exceptions.HospitalException;

import java.util.List;

public class DepartmentManager implements Manager<Department> {
    @Override
    public Department getById(int id) throws HospitalException {
        return DaoFactory.departmentDao().getById(id);
    }

    @Override
    public List<Department> getAll() throws HospitalException {
        return DaoFactory.departmentDao().getAll();
    }

    @Override
    public void delete(int id) throws HospitalException {
        DaoFactory.departmentDao().delete(id);
    }

    @Override
    public void update(Department item) throws HospitalException {
        DaoFactory.departmentDao().update(item);
    }

    @Override
    public void add(Department item) throws HospitalException {
        for(Department department : getAll()){
            System.out.println(department);
            if(item.getName().equals(department.getName())) {
                System.out.println("get in");
                throw new HospitalException(item.getName() + " department already exists!");
            }
        }
        DaoFactory.departmentDao().add(item);
    }

    public List<Department> getByName(String name){
        return DaoFactory.departmentDao().getByName(name);
    }
}
