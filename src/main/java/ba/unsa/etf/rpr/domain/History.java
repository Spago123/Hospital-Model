package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;

/**
 * History is a Java Bean that represents an entity that exists in the Data Base
 */

public class History implements Idable{
    private int id;
    private Patient patient;
    private Doctor doctor;
    private String diagnosis;

    public History(int id, Patient patient, Doctor doctor, String diagnosis) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.diagnosis = diagnosis;
    }

    public History() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", diagnosis='" + diagnosis + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        History history = (History) o;
        return id == history.id && patient.equals(history.patient) && doctor.equals(history.doctor) && diagnosis.equals(history.diagnosis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patient, doctor, diagnosis);
    }
}
