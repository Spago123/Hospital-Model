package ba.unsa.etf.rpr.domain;


import java.util.Date;
import java.util.Objects;

/**
 * Patient is a Java Bean that represents an entity that exists in the Data Base
 */
public class Patient implements Idable, Passwordabel {
    private int id;
    private String name;
    private String password;
    private long UIN;
    private Doctor doctor;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public Patient(int id, String name, String password,long UIN, Doctor doctor) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.UIN = UIN;
        this.doctor = doctor;
    }

    public Patient() {

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

    public long getUIN() {
        return UIN;
    }

    public void setUIN(long UIN) {
        this.UIN = UIN;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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
        Patient patient = (Patient) o;
        return id == patient.id && UIN == patient.UIN && Objects.equals(name, patient.name) && Objects.equals(doctor, patient.doctor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, UIN, doctor);
    }
}
