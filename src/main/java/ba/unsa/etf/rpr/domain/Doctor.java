package ba.unsa.etf.rpr.domain;

import java.sql.Date;
import java.util.Objects;

/**
 * Doctor is a Java Bean that represent an entity tht exists in the Data Base
 */

public class Doctor implements Idable, Passwordabel{
    private int id;
    private String name;
    private String password;

    private Department department;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Doctor(){

    }
    public Doctor(int id, String name, String password, Department department) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Doctor doctor = (Doctor) o;
        return id == doctor.id && name.equals(doctor.name)  && department.equals(doctor.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, department);
    }
}
