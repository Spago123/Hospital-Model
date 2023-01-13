package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.HospitalException;

import java.util.List;

public interface Manager<Type extends Idable> {

    Type getById(int id) throws HospitalException;

    List<Type> getAll() throws HospitalException;

    void delete(int id) throws HospitalException;

    void update(Type item) throws HospitalException;

    void add(Type item) throws HospitalException;

}
