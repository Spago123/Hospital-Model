package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.exceptions.HospitalException;

import java.util.List;

/**
 * DepartmentManager is a class that models our work with
 * the Departments table in the database
 */
public class DepartmentManager implements Manager<Department> {
    /**
     * Method that lets us search a department by id
     * @param id we search
     * @return
     * @throws HospitalException
     */
    @Override
    public Department getById(int id) throws HospitalException {
        return DaoFactory.departmentDao().getById(id);
    }

    /**
     * Method that lets us get all the departments from the database
     * @return
     * @throws HospitalException
     */
    @Override
    public List<Department> getAll() throws HospitalException {
        return DaoFactory.departmentDao().getAll();
    }

    /**
     * Method which allows us to delete a department from
     * the database that has the provided id
     * @param id of the element we want to delete
     * @throws HospitalException
     */
    @Override
    public void delete(int id) throws HospitalException {
        DaoFactory.departmentDao().delete(id);
    }

    /**
     * Method which allows us to update a department
     * in the Departments table
     * @param item we want to update
     * @throws HospitalException
     */
    @Override
    public void update(Department item) throws HospitalException {
        DaoFactory.departmentDao().update(item);
    }

    /**
     * Method that allows us to add a department to the
     * Departments table
     * @param item we want to add
     * @throws HospitalException
     */
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

    /**
     * Method that allows us to get a department from
     * the database that has the provided name
     * @param name of the Department we want
     * @return List of department, actually it should be of size one
     */
    public List<Department> getByName(String name){
        return DaoFactory.departmentDao().getByName(name);
    }
}
