package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Department;

import java.util.List;

/**
 * DepartmentDao is an interface that has to be implemented
 * by the DaoSqlImpl that works with the Department table in the database
 */
public interface DepartmentDao extends Dao<Department>{
    public List<Department> getByName(String name);
}
