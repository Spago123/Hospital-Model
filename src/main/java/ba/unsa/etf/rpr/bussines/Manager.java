package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.HospitalException;

import java.util.List;

/**
 * Interface that defines main attributes for every manager class
 * @param <Type>
 */
public interface Manager<Type extends Idable> {
    /**
     * Method that return an item from the database that has the
     * same id
     * @param id we search
     * @return item with provided id
     * @throws HospitalException if something goes wrong
     */
    Type getById(int id) throws HospitalException;

    List<Type> getAll() throws HospitalException;

    void delete(int id) throws HospitalException;

    void update(Type item) throws HospitalException;

    void add(Type item) throws HospitalException;

}
